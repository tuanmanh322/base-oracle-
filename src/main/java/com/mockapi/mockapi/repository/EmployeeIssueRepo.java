package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.Employee_Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeIssueRepo extends JpaRepository<Employee_Issue,Long> {
}
