package com.mockapi.mockapi.web.dto.request;

import com.mockapi.mockapi.model.Employee;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;


    private Boolean rememberMe;

    public LoginRequest(Employee employee){
        this.username = employee.getUsername();
        this.password = employee.getPassword();
    }
}
