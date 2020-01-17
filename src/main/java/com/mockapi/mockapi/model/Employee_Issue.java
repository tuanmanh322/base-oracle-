package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "EMPLOYEE_ISSUE")
//@IdClass(Employee_Issue.Employee_IssueId.class)
//@Table
public class Employee_Issue implements Serializable {
//    @Id
//    @SequenceGenerator(name = "TIME_ID_SEQ",sequenceName = "TIME_SEQ" )
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TIME_ID_SEQ")
//    @Column(name = "EMPLOYEE_ID")
//    private long employee_id;
//
//    @Id
//    @SequenceGenerator(name = "ISSUESS_ID_SEQ",sequenceName = "ISSUESS_SEQ" ,initialValue = 1,allocationSize = 1 )
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ISSUESS_ID_SEQ")
//    @Column(name = "ISSUES_ID")
//    private long issue_id;
    @EmbeddedId
    EmployeeIHPK employeeIHPK;

    // thời gian dự tính
    @Column(name = "SPENT_TIME")
    private float spent_time;

    // ghi chú
    @Column(name = "NOTE")
    private String note;


    @ManyToOne
    @MapsId(value = "issueId")
    @JsonBackReference(value = "eissue-issues")
    private Issues issues;

    @ManyToOne
    @MapsId(value = "employeeId")
    @JsonBackReference(value = "ei-employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    @JsonBackReference(value = "ie-status")
    private Status status;
}
