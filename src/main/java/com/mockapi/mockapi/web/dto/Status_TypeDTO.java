package com.mockapi.mockapi.web.dto;

import com.mockapi.mockapi.model.Status;
import com.mockapi.mockapi.model.Status_Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Status_TypeDTO{

    private long id;

    private String name;


    public Status_TypeDTO(Status_Type status_type){
        this.id = status_type.getId();
        this.name  = status_type.getName();
    }
}
