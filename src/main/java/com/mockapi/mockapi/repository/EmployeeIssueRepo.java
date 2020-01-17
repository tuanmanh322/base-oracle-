package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.Employee_Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeIssueRepo extends JpaRepository<Employee_Issue,Long> {
    @Query(nativeQuery = true,value = "select * from EMPLOYEE_ISSUE E WHERE E.EMPLOYEE_ID =?1")
    Employee_Issue findByEmployeeId(Long id);
}
