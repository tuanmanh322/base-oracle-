package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Entity(name = "TEAM_PROJECT")
//@Table
public class TeamProject implements Serializable {
    @EmbeddedId
    private TeamProjectPK teamProjectPK;

    @ManyToOne
    @MapsId(value = "idProject")
    @JsonBackReference
    private Project project;

    @Column(name = "START_DATE")
    private Date start_date;

    @Column(name = "HANDOVER_DATE")
    private Date handover_date;

    @ManyToOne
    @MapsId(value = "idTeam")
    @JsonBackReference
    private Team team;

}
