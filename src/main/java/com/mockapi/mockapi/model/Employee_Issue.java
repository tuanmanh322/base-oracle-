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
    @JsonBackReference
    private Issues issues;

    @ManyToOne
    @MapsId(value = "employeeId")
    @JsonBackReference
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    @JsonBackReference
    private Status status;

//    public static class Employee_IssueId implements Serializable{
//        @Column(insertable = false,updatable = false)
//        private Employee employee_id;
//
//        @Column(insertable = false,updatable = false)
//        private Issues issue_id;
//
//        public Employee_IssueId() {
//        }
//
//        public Employee_IssueId(Employee employee_id, Issues issue_id) {
//            this.employee_id = employee_id;
//            this.issue_id = issue_id;
//        }
//
//        public Employee getEmployee_id() {
//            return employee_id;
//        }
//
//        public void setEmployee_id(Employee employee_id) {
//            this.employee_id = employee_id;
//        }
//
//        public Issues getIssue_id() {
//            return issue_id;
//        }
//
//        public void setIssue_id(Issues issue_id) {
//            this.issue_id = issue_id;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (!(o instanceof Employee_IssueId)) return false;
//            Employee_IssueId that = (Employee_IssueId) o;
//            return Objects.equals(employee_id, that.employee_id) &&
//                    Objects.equals(issue_id, that.issue_id);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(employee_id, issue_id);
//        }
//    }
}
