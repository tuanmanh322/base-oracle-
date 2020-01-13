package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "ISSUE")
//@Table
public class Issues implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ISSUE_ID_SEQ")
    @SequenceGenerator(name = "ISSUE_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_ISSUE",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    @JsonBackReference
    private Project project;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    @JsonBackReference
    private Status status;

    @OneToMany(mappedBy = "issues",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee_Issue> employee_issues;

    @OneToMany(mappedBy = "issues",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Issues_History> issuesHistories;

    @Column(name = "NAME")
    private String name;

    @Column(name = "START_DATE")
    private Date start_Date;


    @Column(name = "DUE_DATE")
    private Date due_Date;
    // đã hoàn thành
    @Column(name = "DONE_PERSENT")
    private int done_Persent;

    // sự ưu tiên
    @Column(name = "PRIORITY")
    private String priority;

    // lý do
    @Column(name = "REASON")
    private String reason;

    // mô tả
    @Column(name = "DESCRIPTION")
    private String description;

    // kiểu
    @Column(name = "TYPE")
    private String type;
}
