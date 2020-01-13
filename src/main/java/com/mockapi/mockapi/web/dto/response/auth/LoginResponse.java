package com.mockapi.mockapi.web.dto.response.auth;

import com.mockapi.mockapi.web.dto.response.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class LoginResponse extends BaseResponseDTO {
    private static final Logger log = LoggerFactory.getLogger(LoginResponse.class);
    private String username;
    private String password;
    private String token;

    public LoginResponse() {
        log.info("response AuthResponseDTO");
    }
}
