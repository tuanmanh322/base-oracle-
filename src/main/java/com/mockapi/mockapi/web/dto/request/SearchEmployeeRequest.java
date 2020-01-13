package com.mockapi.mockapi.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mockapi.mockapi.web.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
public class SearchEmployeeRequest extends BaseDTO {
    private Long id;

    private String username;

    private String password;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;

    private String education;

    private String faculty;

    private String fbLink;

    private String fullname;

    private int graduationYear;


    private String image;


    private boolean isActive;

    private boolean isLeader;

    private boolean isManager;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date last_access;


    private int phone;

    private String skypeAcc;

    private String university;

    private String user_type;

    private Long idDepartment;

    private Long idPosition;

    private Long idTeam;

}
