package com.mockapi.mockapi.config;

//import com.mockapi.mockapi.config.jwt.JWTConfigurer;

import com.mockapi.mockapi.config.jwt.JwtAuthenticationFilter;
import com.mockapi.mockapi.config.jwt.JwtAuthorizationFilter;
import com.mockapi.mockapi.config.jwt1.RestAuthenticationEntryPoint;
import com.mockapi.mockapi.config.jwt1.TokenAuthenticationFilter;
import com.mockapi.mockapi.config.jwt1.TokenUtils;
import com.mockapi.mockapi.repository.EmployeeRepo;
import com.mockapi.mockapi.service.impl.UserCustomDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true, // Nó cho phép cú pháp kiểm soát truy cập dựa trên biểu thức phức tạp
        // hơn với @PreAuthorizevà @PostAuthorizechú thích -@PreAuthorize("hasRole('USER')")
        securedEnabled = true,  // SecureEnables: Nó cho phép @Secured e.g:@Secured({"ROLE_USER", "ROLE_ADMIN"})
        //public User getUser(Long id) {}
        jsr250Enabled = true  // cho phép @RolesAllowedchú thích @RolesAllowed("ROLE_ADMIN")
        // public Poll createPoll() {}
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserCustomDetail jwtUserDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    // Define the way of authentication
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .cors().and()

                // Allow all users to access URLs that have 'public' in them
                // Allow auth
                .authorizeRequests()
                .antMatchers("**/api/**").permitAll()
                .antMatchers("/auth/**").permitAll()

                // All other requests must be authorized
                .anyRequest().authenticated().and()

                // Intercept every request with filter
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        // TokenAuthenticationFilter will ignore all URLs below
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
        web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");

        // TokenAuthenticationFilter will ignore all paths that have 'public' in them
        web.ignoring().antMatchers(HttpMethod.GET, "/**/api/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/**/api/**");
        web.ignoring().antMatchers(HttpMethod.PUT, "/**/api/**");
        web.ignoring().antMatchers(HttpMethod.DELETE, "/**/api/**");
    }
}
