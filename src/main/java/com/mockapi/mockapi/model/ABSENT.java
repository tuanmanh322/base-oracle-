package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "ABSENT")
//@Table
public class ABSENT implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ABSENT_ID_SEQ")
    @SequenceGenerator(name = "ABSENT_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_ABSENT",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;


    @ManyToOne
    @JoinColumn(name = "PERSON_ABSENT_ID")
    @JsonBackReference(value = "absent-employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "PERSON_APPROVE_ID")
    @JsonBackReference(value = "absent1-employee")
    private Employee employees;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    @JsonBackReference(value = "absent-status")
    private Status status;

    @Column(name = "NUMBER_DAY")
    private int numberDay;

    @Column(name = "REASON")
    private String reason;
}
