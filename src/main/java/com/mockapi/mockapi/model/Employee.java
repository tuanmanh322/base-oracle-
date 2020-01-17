package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "EMPLOYEE")
//@JsonIgnoreProperties("inspection")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Employee implements UserDetails,Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "EMPLOYEE_ID_SEQ")
    @SequenceGenerator(name = "EMPLOYEE_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_EMPLOYEE",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IMAGE_URL")
    private String image;

    @Column(name = "LAST_ACCESS")
    private Date last_access;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "CREATED_DATE")
    private Date created_date;

    @Column(name = "EMAIL")
    private String Email;

    @Column(name = "PHONE_NUMBER")
    private int phone_number;

    @Column(name = "SKYPE_ACCOUNT")
    private String skypeAcc;

    @Column(name = "FACEBOOK")
    private String fbLink;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "EMPLOYEE_ROLE",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID",referencedColumnName = "ID",nullable = false),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID",referencedColumnName = "ID",nullable = false
            )
    )
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference (value = "issuesh-employee")
    private List<Issues_History> issues_histories;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference (value = "absent-employee")
    private List<ABSENT> absents;

    @OneToMany(mappedBy = "employees",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference (value = "absent1-employee")
    private List<ABSENT> absents1;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "news-employee")
    private List<News> news;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "ei-employee")
    private List<Employee_Issue> employee_issues;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    @JsonBackReference(value = "employee-team")
    private Team team;


    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    @JsonBackReference(value = "employee-department")
    private Department department;


    @ManyToOne
    @JoinColumn(name = "POSITION_ID")
    @JsonBackReference(value = "employee-position")
    private Position position;


    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EDUCATION")
    private String education;

    @Column(name = "UNIVERSITY")
    private String university;

    @Column(name = "FACULTY")
    private String faculty;

    @Column(name = "GRADUATED_YEAR")
    private int graduationYear;

    @Column(name = "USER_TYPE")
    private String userType;

    @Column(name = "IS_ACTIVED")
    private boolean is_actived;

    @Column(name = "IS_LEADER")
    private boolean is_leader;

    @Column(name = "IS_MANAGER")
    private boolean is_manager;


    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.is_actived;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
