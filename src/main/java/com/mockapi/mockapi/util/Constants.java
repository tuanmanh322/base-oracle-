package com.mockapi.mockapi.util;

public class Constants {
    public interface ApiErrorCode {
        String ERROR = "01";
        String SUCCESS = "00";
        String DELETE_ERROR = "02";
        String ERROR_401 = "401";
    }
    public interface ApiErrorDesc {
        String ERROR = "ERROR";
        String SUCCESS = "SUCCESS";
        String DELETE_ERROR = "DELETE_ERROR";
        String NOT_FOUND = "NOT_FOUND";
    }
    // Replace with your email here:
    public static final String MY_EMAIL = "bruceleee096@gmail.com";

    // Replace password!!
    public static final String MY_PASSWORD = "shaiya01";

    public static final String SQL_MODULE_EMPLOYEE = "employee";

    public static final String SQL_MODULE_ISSUE = "issue";

    public static final String SQL_MODULE_PROJECT = "project";

    public static final String SQL_MODULE_TEAM = "team";

    public static final String SQL_MODULE_ABSENT = "absent";

    public static final String ROLE_PUBLIC = "PUBLIC";
    public static final String ROLE_MEMBER = "MEMBER";

    public static final String ROLE_TEAMLEAD = "TEAMLEAD";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_HR= "HR";
}
