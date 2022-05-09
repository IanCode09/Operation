package com.carefast.careops.controller.client;

import com.carefast.careops.dto.client.AuthModelClientResponse;
import com.carefast.careops.dto.client.request.ClientLoginRequestDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.client.ClientAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/client")
public class ClientAuthController {

    @Autowired
    private ClientAuthService clientAuthService;

    @PostMapping("/login")
    public void clientAuth(HttpServletRequest request, HttpServletResponse response,
                           @RequestBody ClientLoginRequestDTO clientLoginRequestDTO) throws IOException {

        AuthModelClientResponse authModelClientResponse = clientAuthService.clientAuth(clientLoginRequestDTO);

        if (authModelClientResponse != null) {
            DataResponse<AuthModelClientResponse> data = new DataResponse<>();
            data.setData(authModelClientResponse);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "9", "Email or password is wrong");
        }
    }
}
