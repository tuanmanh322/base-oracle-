package com.mockapi.mockapi.web.rest.auth;

import com.mockapi.mockapi.config.jwt1.TokenUtils;
import com.mockapi.mockapi.model.EmployeeToken;
import com.mockapi.mockapi.service.impl.UserCustomDetail;
import com.mockapi.mockapi.web.dto.request.LoginRequest;
import com.mockapi.mockapi.web.dto.request.PasswordChangerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@Slf4j
public class AuthRest {
    @Autowired
    private TokenUtils tokenUtils;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserCustomDetail userCustomDetail;


    @PostMapping("/login")
    public ResponseEntity<?> authenticationEmp(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userCustomDetail.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<EmployeeToken> refreshAuthenticationToken(HttpServletRequest request) {
        return new ResponseEntity<>(userCustomDetail.refreshAuthenticationToken(request), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity changePassword(@Valid @RequestBody PasswordChangerRequest passwordChanger) {
        userCustomDetail.ChangePassword(passwordChanger.getOldPassword(), passwordChanger.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
