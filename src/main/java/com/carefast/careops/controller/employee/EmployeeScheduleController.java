package com.carefast.careops.controller.employee;

import com.carefast.careops.dto.project.ProjectDTOInsys;
import com.carefast.careops.dto.schedule.CustomEmployeeDetailListProjectDTO;
import com.carefast.careops.dto.schedule.ScheduleDTO;
import com.carefast.careops.dto.schedule.ScheduleNonOperatorDTO;
import com.carefast.careops.dto.schedule.ScheduleProjectDetailDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.employee.EmployeeScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/employee", produces = {"application/json"})
public class EmployeeScheduleController {

    @Autowired
    private EmployeeScheduleService employeeScheduleService;

    @GetMapping("/schedule")
    public void getEmployeeSchedule(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam int employeeId, @RequestParam String projectId) throws IOException {

        List<ScheduleDTO> scheduleDTO = employeeScheduleService.getEmployeeSchedule(employeeId, projectId);

        if (!scheduleDTO.isEmpty()) {
            DataResponse<List<ScheduleDTO>> data = new DataResponse<>();
            data.setData(scheduleDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Schedule Not Found");
        }
    }

    @GetMapping("/schedule/{id}")
    public void getDetailEmployeeSchedule(HttpServletRequest request, HttpServletResponse response,
                                          @PathVariable int id) throws IOException {

        ScheduleProjectDetailDTO scheduleDTO = employeeScheduleService.getDetailEmployeeSchedule(id);

        if (scheduleDTO != null) {
            DataResponse<ScheduleProjectDetailDTO> data = new DataResponse<>();
            data.setData(scheduleDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "404", "Schedule not found");
        }
    }

    @GetMapping("/non-operator/schedule")
    public void getEmployeeNonOperatorSchedule(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam int employeeId, @RequestParam String projectId) throws IOException {

        ScheduleNonOperatorDTO scheduleNonOperatorDTO = employeeScheduleService.getEmployeeNonOperatorSchedule(employeeId, projectId);

        if (scheduleNonOperatorDTO != null) {
            DataResponse<ScheduleNonOperatorDTO> data = new DataResponse<>();
            data.setData(scheduleNonOperatorDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Schedule not found or Employee off or Something wrong");
        }
    }

    @GetMapping("/schedule/today")
    public void getEmployeeScheduleToday(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam int employeeId, @RequestParam String projectId) throws IOException {

        List<CustomEmployeeDetailListProjectDTO> customEmployeeDetailListProjectDTO = employeeScheduleService.getEmployeeScheduleToday(employeeId, projectId);

        DataResponse<List<CustomEmployeeDetailListProjectDTO>> data = new DataResponse<>();
        data.setData(customEmployeeDetailListProjectDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }
}
