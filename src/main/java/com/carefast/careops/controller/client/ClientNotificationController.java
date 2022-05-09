package com.carefast.careops.controller.client;

import com.carefast.careops.dto.schedule.CustomEmployeeDetailListProjectDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.client.ClientNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/client", produces = {"application/json"})
public class ClientNotificationController {
    @Autowired
    private ClientNotificationService clientNotificationService;

    @GetMapping("/notif/dac")
    public void getNotification(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam int clientId,
                                @RequestParam("page") int page) throws IOException {

        Slice<CustomEmployeeDetailListProjectDTO> customEmployeeDetailListProjectDTO = clientNotificationService.getNotification(clientId, page);
        DataResponse<Slice<CustomEmployeeDetailListProjectDTO>> data = new DataResponse<>();
        data.setData(customEmployeeDetailListProjectDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }
}
