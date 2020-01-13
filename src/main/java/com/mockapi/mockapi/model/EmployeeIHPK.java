package com.mockapi.mockapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Embeddable
public class EmployeeIHPK implements Serializable {
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_id")
    private Long issueId;


}
