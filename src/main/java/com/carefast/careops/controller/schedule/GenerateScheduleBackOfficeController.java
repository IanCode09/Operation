package com.carefast.careops.controller.schedule;

import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.schedule.GenerateScheduleBOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/project", produces = {"application/json"})
public class GenerateScheduleBackOfficeController {

    @Autowired
    private GenerateScheduleBOService generateScheduleBOService;

    @PostMapping("/bo/schedule/{projectCode}/{month}/{year}")
    public void generateCsoSchedule(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable String projectCode,
                                    @PathVariable int month,
                                    @PathVariable int year) throws IOException {

        boolean generate = generateScheduleBOService.generateCsoSchedule(projectCode, month, year);

        if (generate) {
            HandlerResponse.responseSuccessOK(response, "Generate Success");
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong");
        }
    }
}
