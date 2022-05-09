package com.carefast.careops.security;

import com.carefast.careops.dto.employee.request.EmployeeLoginRequestDTO;

import com.carefast.careops.dto.employee.response.AuthModelResponse;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.employee.EmployeeService;
import com.carefast.careops.utils.TokenUtils;
import com.carefast.careops.utils.ValidationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ValidationUtils validationUtils;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        this.authenticationManager = authenticationManager;
        this.employeeService = ctx.getBean(EmployeeService.class);
        this.validationUtils = ctx.getBean(ValidationUtils.class);
        setFilterProcessesUrl("/api/v1/employee/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            EmployeeLoginRequestDTO employeeLoginRequestDTO = new ObjectMapper()
                    .readValue(request.getInputStream(), EmployeeLoginRequestDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(employeeLoginRequestDTO.getEmail(), employeeLoginRequestDTO.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String[] userName =((User) authResult.getPrincipal()).getUsername().split("-");

        try {
            Optional<InsysEmployeeModel> insysEmployeeModel = employeeService.findById(Integer.parseInt(userName[0]));

            if (validationUtils.emailValidation(userName[1])) {
                successAuthenticationResponse(response, userName, insysEmployeeModel);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            HandlerResponse.responseInternalServerError(response, e.getMessage());
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        HandlerResponse.responseBadRequest(response, "9", "Email or password is wrong");
    }

    private void successAuthenticationResponse(HttpServletResponse response, String[] userName, Optional<InsysEmployeeModel> insysEmployeeModel) {
        String token = TokenUtils.generateToken(userName[0]);

        String refreshToken = TokenUtils.generateRefreshToken();

        AuthModelResponse authModelResponse = new AuthModelResponse();
        authModelResponse.setIdEmployeeProject(insysEmployeeModel.get().getIdEmployee());
        authModelResponse.setEmployeeNuc(insysEmployeeModel.get().getEmployeeCode());
        authModelResponse.setEmployeeName(insysEmployeeModel.get().getEmployeeName());
        authModelResponse.setEmail(insysEmployeeModel.get().getEmployeeEmail());
        authModelResponse.setToken(token);
        authModelResponse.setRefreshToken(refreshToken);

        DataResponse<AuthModelResponse> dataResponse = new DataResponse<>();
        dataResponse.setData(authModelResponse);

        HandlerResponse.responseSuccessWithData(response, dataResponse);
    }
}
