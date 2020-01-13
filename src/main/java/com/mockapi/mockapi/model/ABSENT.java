package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
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
    @JsonBackReference
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "PERSON_APPROVE_ID")
    @JsonBackReference
    private Employee employees;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    @JsonBackReference
    private Status status;

    @Column(name = "NUMBER_DAY")
    private int numberDay;

    @Column(name = "REASON")
    private String reason;
}
