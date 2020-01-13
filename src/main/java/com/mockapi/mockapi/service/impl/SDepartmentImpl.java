package com.mockapi.mockapi.service.impl;

import com.mockapi.mockapi.model.Department;
import com.mockapi.mockapi.repository.DepartmentRepo;
import com.mockapi.mockapi.service.ISDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class SDepartmentImpl implements ISDepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }
}
