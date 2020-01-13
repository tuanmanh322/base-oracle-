package com.mockapi.mockapi.web.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDTO implements Serializable {
    protected Integer pageSize = 5;
    protected Integer page = 0;
    protected String sort;
}
