package com.mockapi.mockapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class TeamProjectPK implements Serializable {
    @Column(name = "PROJECT_ID")
    private Long idProject;

    @Column(name = "TEAM_ID")
    private Long idTeam;
}
