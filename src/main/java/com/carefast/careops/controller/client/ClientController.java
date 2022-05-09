package com.carefast.careops.controller.client;

import com.carefast.careops.dto.client.ClientDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/client/profile", produces = {"application/json"})
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{clientId}")
    public void getClientProfile(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable int clientId) throws IOException {

        ClientDTO clientDTO = clientService.getClientProfile(clientId);

        if (clientDTO != null) {
            DataResponse<ClientDTO> data = new DataResponse<>();
            data.setData(clientDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response ,"01", "User not found");
        }
    }
}
