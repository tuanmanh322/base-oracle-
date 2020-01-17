package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "TEAM")
//@Table
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TEAM_ID_SEQ")
    @SequenceGenerator(name = "TEAM_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_TEAM",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "LEADER_ID")
    private int leader_id;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    //@JsonManagedReference(value = "teamp-team")
    private List<TeamProject> teamProjects;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "employee-team")
    private List<Employee> employees;
}
