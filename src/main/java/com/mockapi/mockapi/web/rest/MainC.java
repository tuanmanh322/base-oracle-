package com.mockapi.mockapi.web.rest;

import com.mockapi.mockapi.model.Department;
import com.mockapi.mockapi.service.ISDepartmentService;
import com.mockapi.mockapi.service.ISStatus_Type;
import com.mockapi.mockapi.web.dto.Status_TypeDTO;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class MainC {
    @Autowired
    private ISStatus_Type isStatus_type;

    @Autowired
    private ISDepartmentService isDepartmentService;

    @GetMapping("/ss")
    public ResponseEntity<GetListDataResponseDTO<Status_TypeDTO>> getAll(){
        GetListDataResponseDTO<Status_TypeDTO> status_typeDTOS = isStatus_type.getAll();
        if(status_typeDTOS == null){
            log.error("ss is not found {}",status_typeDTOS);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(status_typeDTOS,HttpStatus.OK);
    }
    @GetMapping("/de")
    public ResponseEntity<?> getDP(){
        List<Department> departments = isDepartmentService.findAll();
        return new ResponseEntity<>(departments,HttpStatus.OK);
    }
}
