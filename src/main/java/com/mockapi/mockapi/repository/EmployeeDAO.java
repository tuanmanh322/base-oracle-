package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.Employee;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.SearchEmployeeRequest;
import org.springframework.data.domain.Page;

public interface EmployeeDAO {
    Page<EmployeeDTO> getListByParams(SearchEmployeeRequest searchEmployeeRequest);

    EmployeeDTO findByUsername(String username);

    Employee resetPW();
}
