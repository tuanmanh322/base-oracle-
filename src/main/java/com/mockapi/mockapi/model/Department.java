package com.mockapi.mockapi.model;

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
@Entity(name = "DEPARTMENT")
//@Table
public class Department {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DEPARTMENT_ID_SEQ")
        @SequenceGenerator(name = "DEPARTMENT_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_DEPARTMENT",initialValue = 1,allocationSize = 1)
        @Column(name = "ID")
        private long id;

        @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        @JsonManagedReference
        private List<Employee> employees;

        @Column(name = "NAME")
        private String name;

        @Column(name = "LOCATION")
        private String location;

        @Column(name = "MANAGER_ID")
        private long manager;
}
