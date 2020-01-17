package com.mockapi.mockapi.web.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseDTO implements Serializable {
    protected Integer pageSize = 5;
    protected Integer page = 0;
    protected String sort;
}
