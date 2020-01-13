package com.mockapi.mockapi.web.rest;

import com.mockapi.mockapi.model.Employee;
import com.mockapi.mockapi.repository.EmployeeRepo;
import com.mockapi.mockapi.service.ISEmployeeService;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.EmployeeRequest;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.GetSingleDataResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeRest {
    @Autowired
    private ISEmployeeService employeeService;

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping("")
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> addEmp(@Valid @RequestBody EmployeeRequest employeeRequest){
        log.info("--request to add new Employee: {} " + employeeRequest.toString());
        GetSingleDataResponseDTO<EmployeeDTO> emp  = employeeService.add(employeeRequest);
        if (emp == null){
            log.error("Faile to add employee :{}",emp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("---Rest response of add new employee: {} " + emp.getMessage());

        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> getById(@PathVariable("id")Long id){
        log.info("fetch Employee with id : {}",id);
        GetSingleDataResponseDTO<EmployeeDTO> resp = employeeService.findById(id);
        if(resp == null){
            log.error("can't find Employee with id :{}",id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> deleteById(@PathVariable("id")Long id){
        log.info("--- Rest request to delete employee: " + id);
        GetSingleDataResponseDTO<EmployeeDTO> result = employeeService.deleteById(id);
        log.info("Rest response of update employee {}: " + result.getMessage());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/verify-account/{token}")
    public ResponseEntity verifyUserAccount(@PathVariable String token) {
        employeeService.activateAccount(token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<GetListDataResponseDTO<EmployeeDTO>> getAll(){
        log.info("--request to GET All Employee: {} ");
        GetListDataResponseDTO<EmployeeDTO> resp  = employeeService.getAll();
        if(resp == null){
            log.error("can't get all employee :{}");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("--- response to getAll Employee : {}",resp);
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }



}
