package com.mockapi.mockapi.service.impl;

import com.mockapi.mockapi.common.TimeProvider;
import com.mockapi.mockapi.exception.ApiRequestException;
import com.mockapi.mockapi.exception.ResourceNotFoundException;
import com.mockapi.mockapi.model.*;
import com.mockapi.mockapi.repository.*;
import com.mockapi.mockapi.service.ISEmployeeService;
import com.mockapi.mockapi.util.Constants;
import com.mockapi.mockapi.util.RandomPassword;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.EmployeeRequest;
import com.mockapi.mockapi.web.dto.request.LoginRequest;
import com.mockapi.mockapi.web.dto.request.SearchEmployeeRequest;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.GetSingleDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.resp.SearchRequestResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private EmployeeIssueRepo employeeIssueRepo;

    @Autowired
    private SMailSenderImpl sMailSender;
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
                String pw = RandomPassword.pwGenerate();

                emp.set_actived(false);
                emp.setPassword(passwordEncoder.encode(pw));
                emp.getRoles().add(roleRepo.findByName(Constants.ROLE_PUBLIC));
                emp.set_actived(true);
                emp = employeeRepo.save(emp);
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(employeeRequest.getEmail());
                message.setSubject("Verified account & Password sender!");
                ConfirmationToken token = new ConfirmationToken(emp);
                confirmationTokenRepo.save(token);
                message.setText("Password: "+pw +"\n Go to this page to activate your account http://localhost:8888/api/employee/public/verify-account/" + token.getToken());
                javaMailSender.send(message);
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
            if (emp != null) {
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
            Employee emp = employeeRepo.getOne(id);
            result.setResult(modelMapper.map(emp, EmployeeDTO.class));
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
        try {
            data.stream().map(res -> {
                EmployeeDTO em = modelMapper.map(res, EmployeeDTO.class);
//                em.setNews(news);
//                em.setAbsents(absents);
//                em.setIssues_histories(issues_histories);
                dto.add(em);
                result.setValue(dto);
                return result;
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setValue(null);
        }

        log.info("---response result ---- " + result.getMessage());
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
            emp.getId();
            employeeRepo.save(emp);
            confirmationToken.setUsed(true);
            confirmationTokenRepo.save(confirmationToken);
        } else {
            confirmationTokenRepo.delete(confirmationToken);
            employeeRepo.delete(emp);
            throw new ApiRequestException("Confirmation token timed out.");
        }
    }

    @Override
    public GetSingleDataResponseDTO<EmployeeDTO> delete(Long id) {
        log.info("--- request delete obj Employee Employee----");
        GetSingleDataResponseDTO<EmployeeDTO> result = new GetSingleDataResponseDTO<>();
        Employee emp = employeeRepo.findById(id).get();
        try {
            if(emp!=null) {
               if(findAbsent(emp.getId())!=null){
                   absentRepo.delete(findAbsent(emp.getId()));
               }
               if(findNews(emp.getId())!= null){
                   newsRepo.delete(findNews(emp.getId()));
               }
               if(findConfigToken(emp.getId())!=null){
                   confirmationTokenRepo.delete(findConfigToken(emp.getId()));
               }
               if(findEmpIssue(emp.getId())!=null){
                   employeeIssueRepo.delete(findEmpIssue(emp.getId()));
               }
               if(findIH(emp.getId())!=null){
                   issueHistoryRepo.delete(findIH(emp.getId()));
               }
//                emp.getEmployee_issues().forEach(ei -> {
//                    Employee_Issue employeeIssue = employeeIssueRepo.findByEmployeeId(emp.getId());
//                    employeeIssueRepo.delete(employeeIssue);
//                });
//                emp.getIssues_histories().forEach(ih -> {
//                    Issues_History issuesHistory = issueHistoryRepo.findByUpdatePerson(emp.getId());
//                    issueHistoryRepo.updateIH();
//                });
//                emp.getNews().forEach(ne -> {
//                    News news = newsRepo.findByEmployeeId(emp.getId());
//                    newsRepo.delete(news);
//                });
//                    ConfirmationToken token  = confirmationTokenRepo.findByEmployeeId(emp.getId());
//                    confirmationTokenRepo.delete(token);
                employeeDAO.deleteRoleEmp(emp.getId());
            }
            employeeRepo.delete(emp);
            result.setResult(modelMapper.map(emp,EmployeeDTO.class));
            log.info("-- Response of delete Employee "+ result.getMessage());
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
            result.setResult(null);
        }
        log.info("---- deleted Employee service with id" + emp.getId());
        return result;
    }
    public ABSENT findAbsent(Long id){
        ABSENT absent = absentRepo.findByEmployeeId(id);
        return absent;
    }
    public Issues_History findIH(Long id){
        Issues_History issuesHistory = issueHistoryRepo.findByUpdatePerson(id);
        return issuesHistory;
    }
    public Employee_Issue findEmpIssue(Long id){
        Employee_Issue employeeIssue = employeeIssueRepo.findByEmployeeId(id);
        return employeeIssue;
    }
    public News findNews(Long id){
        News news = newsRepo.findByEmployeeId(id);
        return news;
    }
    public ConfirmationToken findConfigToken(Long id){
        ConfirmationToken confirmationToken = confirmationTokenRepo.findByEmployeeId(id);
        return confirmationToken;
    }
    @Override
    public GetListDataResponseDTO<SearchRequestResponse> All(SearchEmployeeRequest request) {
        log.info("--request to get all employee--sssss");
        GetListDataResponseDTO<SearchRequestResponse> result = new GetListDataResponseDTO<>();
        List<SearchRequestResponse> list = new ArrayList<>();
        try {
            Page<Employee> rawDatas = employeeRepo.findAll(PageRequest.of(request.getPage(), request.getPageSize()));
            if (rawDatas != null) {
                if (rawDatas.getContent().size() > 0) {
                    rawDatas.getContent().forEach(emp -> {
                        SearchRequestResponse requestResponse = modelMapper.map(emp, SearchRequestResponse.class);
                        list.add(requestResponse);
                    });
                }
                result.setResult(list, rawDatas.getTotalElements(), rawDatas.getTotalPages());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setResult(null, null, null);
        }
        log.info("Response to get all Employee " + result.getMessage());
        return result;
    }
    @Override
    public GetListDataResponseDTO<SearchRequestResponse> AllByParams(SearchEmployeeRequest request) {
        log.info("--request to getAllByParmas is -----");
        GetListDataResponseDTO<SearchRequestResponse> result = new GetListDataResponseDTO<>();
        Page<SearchRequestResponse> rawDatas = employeeDAO.getListByParams(request);
        System.out.println("content!!!!!!"+rawDatas.getContent() +"---- size"+rawDatas.getSize());
        result.setResult(rawDatas.getContent(),rawDatas.getTotalElements(),rawDatas.getTotalPages());
        log.info("--response to get list employee by params: " + result.getMessage());
        return result;
    }

    @Override
    public GetSingleDataResponseDTO<EmployeeDTO> update(EmployeeDTO dto) {
        log.info("--request update employee service ----");
        GetSingleDataResponseDTO<EmployeeDTO> result = new GetSingleDataResponseDTO<>();
        try {
            Employee employee  = employeeRepo.findById(dto.getId()).get();
            if(employee != null){
                employee.set_actived(dto.is_actived());
                employee.setBirthday(dto.getBirthday());
                employee.setAddress(dto.getAddress());
                employee.setEducation(dto.getEducation());
                employee.setUserType(dto.getUser_type());
                employee.setAbsents(dto.getAbsents());
                employee.setAbsents1(dto.getAbsents());
                employee.setCreated_date(dto.getCreated_date());
                employee.setDepartment(dto.getDepartment());
                employee.setEmail(dto.getEmail());
                employee.setSkypeAcc(dto.getSkypeAcc());
                employee.setPhone_number(dto.getPhone_number());
                employee.setPosition(dto.getPosition());
                employee.setImage(dto.getImage());
                employee.setNews(dto.getNews());
                employee.setFbLink(dto.getFbLink());
                employee.setGraduationYear(dto.getGraduationYear());
                employee.setFaculty(dto.getFaculty());
                employee.setFullName(dto.getFullname());
                employee.setUniversity(dto.getUniversity());
                employeeRepo.save(employee);
                result.setResult(modelMapper.map(employee,EmployeeDTO.class));
            }
            log.info("--response Employee update service ---" + result.getMessage());
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
            result.setResult(null);
        }
        return result;
    }


}
