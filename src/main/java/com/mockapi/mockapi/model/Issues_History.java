package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "ISSUES_HISTORY")
//@Table
public class Issues_History implements Serializable {
    @Id
    @SequenceGenerator(name = "ISSUESH_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_ISSUE_HIS",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ISSUESH_ID_SEQ")


    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "ISSUE_ID")
    @JsonBackReference
    private Issues issues;

    @ManyToOne
    @JoinColumn(name = "UPDATE_PERSON_ID")
    @JsonBackReference
    private Employee employee;

    @Column(name = "UPDATE_DATE")
    private Date update_date;

    @Column(name = "COMMENTS")
    private String comment;

    @Column(name = "ISSUE_CHANGE")
    private String change;
}
