package com.carefast.careops.security;

import com.carefast.careops.service.client.JWTClientService;
import com.carefast.careops.service.employee.JWTEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JWTEmployeeService jwtEmployeeService;
//    private JWTClientService jwtClientService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurityConfig(JWTEmployeeService jwtEmployeeService, JWTClientService jwtClientService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEmployeeService = jwtEmployeeService;
//        this.jwtClientService = jwtClientService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtEmployeeService).passwordEncoder(bCryptPasswordEncoder);
//        auth.userDetailsService(jwtClientService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/employee/send-otp").permitAll()
                .antMatchers("/api/v1/employee/verify-otp").permitAll()
                .antMatchers("/api/v1/employee/change-password").permitAll()
                .antMatchers("/api/v1/careops/employees").permitAll()
                .antMatchers("/api/v1/careops/projects").permitAll()
                .antMatchers("/api/v1/careops/jobs").permitAll()
                .antMatchers("/api/v1/employee/{nuc}/{nik}").permitAll()
                .antMatchers("/api/v1/employee/register/send-otp").permitAll()
                .antMatchers("/api/v1/employee/create-password").permitAll()
                .antMatchers("/api/v1/client/login").permitAll()
                .antMatchers("/api/v1/project/bo/schedule/{projectCode}/{month}/{year}").permitAll()
                .antMatchers("/api/v1/project/bo/schedule/assign/{projectCode}/{month}/{year}").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), getApplicationContext()))
                .addFilter(new com.carefast.careops.security.client.JWTAuthenticationFilter(authenticationManager(), getApplicationContext()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
