package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TEAM_PROJECT")
//@Table
public class TeamProject implements Serializable {
    @EmbeddedId
    private TeamProjectPK teamProjectPK;

    @ManyToOne
    @MapsId(value = "idProject")
    @JsonBackReference(value = "teamp-project")
    private Project project;

    @Column(name = "START_DATE")
    private Date start_date;

    @Column(name = "HANDOVER_DATE")
    private Date handover_date;

    @ManyToOne
    @MapsId(value = "idTeam")
    @JsonBackReference(value = "teamp-team")
    private Team team;

}
