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
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "ISSUES_ID")
    private Long issueId;


}
