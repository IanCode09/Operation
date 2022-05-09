package com.carefast.careops.security.client;

import com.carefast.careops.dto.client.AuthModelClientResponse;
import com.carefast.careops.dto.client.request.ClientLoginRequestDTO;
import com.carefast.careops.model.client.ClientModel;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.client.ClientService;
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
    private ClientService clientService;
    @Autowired
    private ValidationUtils validationUtils;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        this.authenticationManager = authenticationManager;
        this.clientService = ctx.getBean(ClientService.class);
        this.validationUtils = ctx.getBean(ValidationUtils.class);
//        setFilterProcessesUrl("/api/v1/client/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ClientLoginRequestDTO clientLoginRequestDTO = new ObjectMapper()
                    .readValue(request.getInputStream(), ClientLoginRequestDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(clientLoginRequestDTO.getEmail(), clientLoginRequestDTO.getPassword(), new ArrayList<>())
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
            Optional<ClientModel> clientModel = clientService.findById(Integer.parseInt(userName[0]));

            if (validationUtils.emailValidation(userName[1])) {
                System.out.println(clientModel.get().getClientId());
                successAuthenticationResponse(response, userName, clientModel);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            HandlerResponse.responseInternalServerError(response, e.getMessage());
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        HandlerResponse.responseBadRequest(response, "9", "Username or password is wrong");
    }

    private void successAuthenticationResponse(HttpServletResponse response, String[] userName, Optional<ClientModel> clientModel) {
        String token = TokenUtils.generateToken(userName[0]);

        String refreshToken = TokenUtils.generateRefreshToken();

        AuthModelClientResponse authModelClientResponse = new AuthModelClientResponse();
        authModelClientResponse.setClientId(clientModel.get().getClientId());
        authModelClientResponse.setClientName(clientModel.get().getClientName());
        authModelClientResponse.setProjectCode(clientModel.get().getProjectCode());
        authModelClientResponse.setEmail(clientModel.get().getEmail());
        authModelClientResponse.setStatus(clientModel.get().getStatus());
        authModelClientResponse.setToken(token);
        authModelClientResponse.setRefreshToken(refreshToken);

        DataResponse<AuthModelClientResponse> dataResponse = new DataResponse<>();
        dataResponse.setData(authModelClientResponse);

        HandlerResponse.responseSuccessWithData(response, dataResponse);
    }
}
