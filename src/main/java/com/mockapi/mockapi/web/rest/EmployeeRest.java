package com.mockapi.mockapi.web.rest;

import com.mockapi.mockapi.model.Employee;
import com.mockapi.mockapi.repository.EmployeeRepo;
import com.mockapi.mockapi.service.ISEmployeeService;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.EmployeeRequest;
import com.mockapi.mockapi.web.dto.request.SearchEmployeeRequest;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.GetSingleDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.resp.SearchRequestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> addEmp(@Valid @RequestBody EmployeeRequest employeeRequest){
        log.info("--request to add new Employee: {} ");
        GetSingleDataResponseDTO<EmployeeDTO> emp  = employeeService.add(employeeRequest);
        if (emp == null){
            log.error("Faile to add employee :{}",emp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("---Rest response of add new employee: {} " + emp.getMessage());

        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
    @PostMapping(value = "/getAll",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetListDataResponseDTO<SearchRequestResponse>> getAll(@RequestBody SearchEmployeeRequest request){
        log.info("---Rest request getAll page--");
        GetListDataResponseDTO<SearchRequestResponse> data = employeeService.All(request);
        if(data ==null){
            log.error("can't get all employee ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("---Rest Response getAll Employee----");
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @PostMapping(value = "/getAll-by-params",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetListDataResponseDTO<SearchRequestResponse>> getAllByParams(@RequestBody SearchEmployeeRequest request){
        log.info("---Rest request getAll by params--");
        GetListDataResponseDTO<SearchRequestResponse> data = employeeService.AllByParams(request);
        if(data ==null){
            log.error("can't get all employee ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("---Rest Response getAll Employee----");
        return new ResponseEntity<>(data,HttpStatus.OK);
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

    @GetMapping("/allrp")
    public ResponseEntity<GetListDataResponseDTO<EmployeeDTO>> getAll(){
        log.info("--request to GET All Employee: {} ");
        GetListDataResponseDTO<EmployeeDTO> resp  = employeeService.getAll();
        if(resp == null){
            log.error("can't get all employee :{}");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("--- response to getAll Employee : {}");
        return new ResponseEntity<>(resp,HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> update(@Valid @RequestBody EmployeeDTO requestDTO) {
        log.info("--- Rest request to update employee {}: " + requestDTO.toString());
        GetSingleDataResponseDTO<EmployeeDTO> result = employeeService.update(requestDTO);
        log.info("Rest response of update employee {}: " + result.getMessage());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> delete(@PathVariable("id")Long id){
        log.info("--request delete id ");
        GetSingleDataResponseDTO<EmployeeDTO> result =  employeeService.delete(id);
        log.info("--success delete id");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/public/verify-account/{token}")
    public ResponseEntity<?> verifyAcc(@PathVariable("token") String token){
        employeeService.activateAccount(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
