package com.mockapi.mockapi.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class EmployeeRequest {
    private Long id;

    @NotNull(message = "Username can't be null")
    private String username;


    @Email(message = "email can't be null")
    private String email;

    //@NotNull(message = "birthday can't be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "address can't be null")
    private String address;

    //@NotNull(message = "createDate can't be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;

    @NotNull(message = "education can't be null")
    private String education;

    @NotNull(message = "faculity can't be null")
    private String faculty;

    @NotNull(message = "facebook can't be null")
    private String fbLink;

    @NotNull(message = "fullName can't be null")
    private String fullname;

    @NotNull(message = "GraduatedYear can't be null")
    private int graduationYear;


    private String image;


    private boolean isActive;

    private boolean isLeader;

    private boolean isManager;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date last_access;

    @NotNull(message = "phoneNumber can't be null")
    private int phone;

    @NotNull(message = "skypeAcc can't be null")
    private String skypeAcc;

    @NotNull(message = "university can't be null")
    private String university;

    @NotNull(message = "userType can't be null")
    private String user_type;

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", create_date=" + create_date +
                ", education='" + education + '\'' +
                ", faculty='" + faculty + '\'' +
                ", fbLink='" + fbLink + '\'' +
                ", fullname='" + fullname + '\'' +
                ", graduationYear=" + graduationYear +
                ", image='" + image + '\'' +
                ", isActive=" + isActive +
                ", isLeader=" + isLeader +
                ", isManager=" + isManager +
                ", last_access=" + last_access +
                ", phone=" + phone +
                ", skypeAcc='" + skypeAcc + '\'' +
                ", university='" + university + '\'' +
                ", user_type='" + user_type + '\'' +
                '}';
    }
}
