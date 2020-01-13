package com.mockapi.mockapi.web.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mockapi.mockapi.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Slf4j
public class CustomUserDetails implements UserDetails {
    private Long id;

    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Date lastPasswordReset;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;

    private Employee employee;
    public CustomUserDetails(String username, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        log.info("SpringSecurityUser 1{}");
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
//        this.setLastPasswordReset(lastPasswordReset);
        this.setAuthorities(authorities);
    }


    public CustomUserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CustomUserDetails(Long id, String username, String password, String email,Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;

    }

    public CustomUserDetails(Employee employee) {
        this.employee = employee;
    }

    public static CustomUserDetails create(Employee employee){
        List<GrantedAuthority> grantedAuthorities = employee.getRoles().stream()
                .map(role-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                employee.getId(),
                employee.getUsername(),
                employee.getPassword(),
                employee.getEmail(),
                grantedAuthorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonExpired();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
