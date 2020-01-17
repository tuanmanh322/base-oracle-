package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "STATUS")
//@Table
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "STATUS_ID_SEQ")
    @SequenceGenerator(name = "STATUS_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_STATUS",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID",nullable = true)
    @JsonBackReference(value = "status-statusty")
    private Status_Type status_type;


    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "status-project")
    private List<Project> projects;

    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "issues-status")
    private List<Issues> issues;


    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "absent-status")
    private List<ABSENT> absents;


    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "ie-status")
    private List<Employee_Issue> employee_issues;


    public Status(long id ) {
        this.id = id;
    }
}
