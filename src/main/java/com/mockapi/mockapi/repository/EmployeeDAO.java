package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.Employee;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.SearchEmployeeRequest;
import com.mockapi.mockapi.web.dto.response.resp.SearchRequestResponse;
import org.springframework.data.domain.Page;

public interface EmployeeDAO {
    Page<SearchRequestResponse> getListByParams(SearchEmployeeRequest searchEmployeeRequest);

    EmployeeDTO findByUsername(String username);

    Employee resetPW();

    void deleteRoleEmp(Long id);


    Employee findByUsername1(String username);

    void setActive(Employee employee);
}
