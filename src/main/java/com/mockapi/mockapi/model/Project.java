package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PROJECT")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PROJECT_ID_SEQ")
    @SequenceGenerator(name = "PROJECT_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_PROJECT",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    @JsonBackReference(value = "status-project")
    private Status status;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   // @JsonManagedReference(value = "teamp-project")
    private List<TeamProject> teamProjects;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   // @JsonManagedReference
    private List<Issues> issues;
}
