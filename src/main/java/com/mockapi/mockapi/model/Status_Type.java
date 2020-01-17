package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "STATUS_TYPE")
//@Table
public class Status_Type {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "STATUS_TYPE_ID_SEQ")
    @SequenceGenerator(name = "STATUS_TYPE_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_STATUS_TYPE",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "status_type",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   // @JsonManagedReference(value = "status-statusty")
    private List<Status> statuses;
}
