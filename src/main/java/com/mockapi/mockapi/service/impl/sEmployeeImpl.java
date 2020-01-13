package com.mockapi.mockapi.service.impl;

import com.mockapi.mockapi.common.TimeProvider;
import com.mockapi.mockapi.exception.ApiRequestException;
import com.mockapi.mockapi.exception.ResourceNotFoundException;
import com.mockapi.mockapi.model.*;
import com.mockapi.mockapi.repository.*;
import com.mockapi.mockapi.service.ISEmployeeService;
//import com.mockapi.mockapi.util.Constants;
//import com.mockapi.mockapi.util.TokenUtils;
import com.mockapi.mockapi.util.Constants;
import com.mockapi.mockapi.util.RandomPassword;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.EmployeeRequest;
import com.mockapi.mockapi.web.dto.request.LoginRequest;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.GetSingleDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.auth.LoginResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class sEmployeeImpl implements ISEmployeeService {
    private static final Logger log = LoggerFactory.getLogger(sEmployeeImpl.class);

    @Autowired
    private ConfirmationTokenRepo confirmationTokenRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserCustomDetail userDetailService;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private AbsentRepo absentRepo;

    @Autowired
    private IssueHistoryRepo issueHistoryRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    private RoleRepo roleRepo;

    public boolean isRequestDataValid(LoginRequest loginRequest) {
        return loginRequest != null &&
                loginRequest.getUsername() != null &&
                loginRequest.getPassword() != null &&
                !loginRequest.getUsername().isEmpty() &&
                !loginRequest.getPassword().isEmpty();
    }


    @Override
    public GetSingleDataResponseDTO<EmployeeDTO> add(EmployeeRequest employeeRequest) {
        log.info("--request to add new employee: {}" + employeeRequest.toString());
        GetSingleDataResponseDTO<EmployeeDTO> result = new GetSingleDataResponseDTO<>();
        try {
            Employee emp = modelMapper.map(employeeRequest, Employee.class);
            if (findByUsername(employeeRequest.getUsername())) {
                log.info(" username already have!!!");

            } else {
                SimpleMailMessage message = new SimpleMailMessage();
                String pw = RandomPassword.pwGenerate();
                message.setTo(employeeRequest.getEmail());
                message.setSubject("Password sender!");
                message.setText(pw);
                javaMailSender.send(message);
                emp.setActive(false);
                emp.setPassword(passwordEncoder.encode(pw));
                emp.getRoles().add(roleRepo.findByName(Constants.ROLE_PUBLIC));
                emp = employeeRepo.save(emp);
                result.setResult(modelMapper.map(emp, EmployeeDTO.class));
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setResult(null);
        }
        log.info("Response of add new employee : {}" + employeeRequest.toString());
        return result;
    }

    @Override
    public boolean findByUsername(String username) {
        log.info("----request finD username----");
        try {
            Employee emp = employeeRepo.findByUsername(username);
             if(emp!=null){
                 return true;
             }

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        log.info("---response-------------");
        return false;
    }

    @Override
    public GetSingleDataResponseDTO<EmployeeDTO> findById(Long id) {
        log.info("----request finD ID----");
        GetSingleDataResponseDTO<EmployeeDTO> result = new GetSingleDataResponseDTO<>();
        try {
            Optional<Employee> emp = employeeRepo.findById(id);
            emp.map(resp -> {
                EmployeeDTO dto = new EmployeeDTO(resp);
                result.setResult(dto);
                return result;
            }).orElseGet(null);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setResult(null);
        }
        log.info("---response-------------" + result.toString());
        return result;
    }

    @Override
    public GetSingleDataResponseDTO<EmployeeDTO> deleteById(Long id) {
        log.info("----request delete ID----");
        GetSingleDataResponseDTO<EmployeeDTO> result = new GetSingleDataResponseDTO<>();
        try {
            if (findById(id) != null) {
                employeeRepo.deleteById(id);
            }
            log.info("---Response of delete Employee : " + result.getMessage());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public GetListDataResponseDTO<EmployeeDTO> getAll() {
        log.info("--- request getAll Employee----");
        GetListDataResponseDTO<EmployeeDTO> result = new GetListDataResponseDTO<>();
        List<Employee> data = employeeRepo.findAll();
        List<EmployeeDTO> dto = new ArrayList<>();
        List<News> news = newsRepo.findAll();
        List<ABSENT> absents = absentRepo.findAll();
        List<Issues_History> issues_histories = issueHistoryRepo.findAll();
        try{
            data.stream().map(res->{
                EmployeeDTO em = modelMapper.map(res,EmployeeDTO.class);
//                em.setNews(news);
//                em.setAbsents(absents);
//                em.setIssues_histories(issues_histories);
                dto.add(em);
                result.setValue(dto);
                return result;
            }).collect(Collectors.toList());
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
            result.setValue(null);
        }

        log.info("---response result ---- " +result.getMessage());
        return result;
    }

    @Override
    public boolean resetPW() {
        return false;
    }

    @Override
    public void activateAccount(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepo.findByToken(token);

        if (confirmationToken == null) {
            throw new ResourceNotFoundException("Confirmation token doesn't exist.");
        }

        if (confirmationToken.isUsed()) {
            throw new ApiRequestException("This token has been already used.");
        }

        Employee emp = confirmationToken.getEmployee();
        long timeDifference = timeProvider.timeDifferenceInMinutes(timeProvider.now(), confirmationToken.getDatetimeCreated());

        if (timeDifference < 30) {
            emp.setActive(true);
            employeeRepo.save(emp);
            confirmationToken.setUsed(true);
            confirmationTokenRepo.save(confirmationToken);
        } else {
            confirmationTokenRepo.delete(confirmationToken);
            employeeRepo.delete(emp);
            throw new ApiRequestException("Confirmation token timed out.");
        }
    }


}
