package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Employee findByUsername(String username);


}
