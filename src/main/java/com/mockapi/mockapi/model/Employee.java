package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "EMPLOYEE")
//@Table
public class Employee implements Serializable, UserDetails {
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
    private String fullname;

    @Column(name = "CREATED_DATE")
    private Date create_date;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private int phone;

    @Column(name = "SKYPE_ACCOUNT")
    private String skypeAcc;

    @Column(name = "FACEBOOK")
    private String fbLink;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "EMPLOYEE_ROLE",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID",referencedColumnName = "ID",nullable = false),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID",referencedColumnName = "ID",nullable = false
            )
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Issues_History> issues_histories;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ABSENT> absents;

    @OneToMany(mappedBy = "employees",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ABSENT> absents1;

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<News> news;


    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    @JsonBackReference
    private Team team;


    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    @JsonBackReference
    private Department department;


    @ManyToOne
    @JoinColumn(name = "POSITION_ID")
    @JsonBackReference
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
    private String user_type;

    @Column(name = "IS_ACTIVED")
    private boolean isActive;

    @Column(name = "IS_LEADER")
    private boolean isLeader;

    @Column(name = "IS_MANAGER")
    private boolean isManager;


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
        return this.isActive;
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
