package com.mockapi.mockapi.service;

import com.mockapi.mockapi.model.Employee;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.EmployeeRequest;
import com.mockapi.mockapi.web.dto.request.LoginRequest;
import com.mockapi.mockapi.web.dto.request.SearchEmployeeRequest;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.GetSingleDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.auth.LoginResponse;
import com.mockapi.mockapi.web.dto.response.resp.SearchRequestResponse;

public interface ISEmployeeService {
//    LoginResponse generateToken(LoginRequest loginRequest);

    GetSingleDataResponseDTO<EmployeeDTO> add(EmployeeRequest employeeRequest);

    boolean findByUsername(String  username);

    GetSingleDataResponseDTO<EmployeeDTO> findById(Long id);

    GetSingleDataResponseDTO<EmployeeDTO> deleteById(Long id);

    GetListDataResponseDTO<EmployeeDTO> getAll();

    boolean resetPW();

    void activateAccount(String token);


    GetSingleDataResponseDTO<EmployeeDTO> delete(Long id);

    GetListDataResponseDTO<SearchRequestResponse> All(SearchEmployeeRequest request);

    GetListDataResponseDTO<SearchRequestResponse> AllByParams(SearchEmployeeRequest request);

    GetSingleDataResponseDTO<EmployeeDTO> update(EmployeeDTO dto);
}
