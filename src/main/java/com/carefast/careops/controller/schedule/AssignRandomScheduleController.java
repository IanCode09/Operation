package com.carefast.careops.controller.schedule;

import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.schedule.AssignRandomScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/project", produces = {"application/json"} )
public class AssignRandomScheduleController {

    @Autowired
    private AssignRandomScheduleService assignRandomScheduleService;

    @PostMapping("/bo/schedule/assign/{projectCode}/{month}/{year}")
    public void assignSchedule(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable String projectCode,
                               @PathVariable int month,
                               @PathVariable int year) throws IOException {

        boolean isAssign = assignRandomScheduleService.assignSchedule(projectCode, month, year);

        if (isAssign) {
            HandlerResponse.responseSuccessOK(response, "Assign employee to schedulle success");
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong");
        }
    }
}
