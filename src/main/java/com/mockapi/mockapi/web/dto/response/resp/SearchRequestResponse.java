package com.mockapi.mockapi.web.dto.response.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mockapi.mockapi.model.Department;
import com.mockapi.mockapi.model.Position;
import com.mockapi.mockapi.model.Role;
import com.mockapi.mockapi.model.Team;
import com.mockapi.mockapi.web.dto.response.BaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchRequestResponse{
    private Long id;

    private String username;

    private String Email;

    private Date created_date;

    private String fullName;

    private boolean is_actived;

    private boolean is_leader;

    private boolean is_manager;

    private Date last_access;

    private int phone_number;

    private String userType;

    private String role_name;

    private String department_name;

    private Long leader_id;

    @Override
    public String toString() {
        return "SearchRequestResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", Email='" + Email + '\'' +
                ", created_date=" + created_date +
                ", fullName='" + fullName + '\'' +
                ", is_actived=" + is_actived +
                ", is_leader=" + is_leader +
                ", is_manager=" + is_manager +
                ", last_access=" + last_access +
                ", phone_number=" + phone_number +
                ", userType='" + userType + '\'' +
                ", role_name='" + role_name + '\'' +
                ", department_name='" + department_name + '\'' +
                ", leader_id=" + leader_id +
                '}';
    }
}
