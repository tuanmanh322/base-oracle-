package com.mockapi.mockapi.service.impl;

import com.mockapi.mockapi.config.jwt.UserPrincipal;
import com.mockapi.mockapi.config.jwt1.TokenUtils;
import com.mockapi.mockapi.exception.ApiRequestException;
import com.mockapi.mockapi.model.Employee;
import com.mockapi.mockapi.model.EmployeeToken;
import com.mockapi.mockapi.repository.EmployeeDAO;
import com.mockapi.mockapi.repository.EmployeeRepo;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
public class UserCustomDetail implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(UserCustomDetail.class);

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeDAO.findByUsername1(username);
        if(employee == null){
            throw new UsernameNotFoundException(username);
        }
        return employee;
    }
    @Transactional
    public UserDetails loadUserById(Long id){
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id: %s", id)));

        return employee;
    }

    public void ChangePassword(String oldPassword,String newPassword){
        Authentication currentUser  = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        if (authenticationManager != null) {
            log.debug("Re-authenticating user '" + username + "' for password change request.");
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
            } catch (BadCredentialsException e) {
                throw new ApiRequestException("Credentials are not valid");
            }
        } else {
            log.debug("No authentication manager set. can't change Password!");
            return;
        }

        log.debug("Changing password for user '" + username + "'");

        Employee employee = (Employee) loadUserByUsername(username);
        employee.setPassword(passwordEncoder.encode(newPassword));
        employeeRepo.save(employee);
    }

    public EmployeeDTO login(LoginRequest loginRequest) throws ApiRequestException{
        Authentication auth;
        try {
            auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ApiRequestException("Credentials are not valid!");
        }

        // Insert username and password into context
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Create token
        Employee emp = (Employee) auth.getPrincipal();
        String jwt = tokenUtils.generateToken(emp.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        EmployeeDTO employeeDTO = new EmployeeDTO(emp);
        employeeDTO.setEmployeeToken(new EmployeeToken(jwt, expiresIn));

        return employeeDTO;
    }

    public EmployeeToken refreshAuthenticationToken(HttpServletRequest request) throws ApiRequestException {
        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);
        Employee emp = (Employee) loadUserByUsername(username);

        if (tokenUtils.canTokenBeRefreshed(token, emp.getLast_access())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();
            return new EmployeeToken(refreshedToken, expiresIn);
        } else {
            throw new ApiRequestException("Token can not be refreshed.");
        }
    }
}
