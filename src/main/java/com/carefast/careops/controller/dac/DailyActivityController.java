package com.carefast.careops.controller.dac;

import com.carefast.careops.dto.activity.DacDTO;
import com.carefast.careops.dto.checklist.ChecklistDACEmployeeDTO;
import com.carefast.careops.dto.schedule.ScheduleDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.dac.DailyActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/employee", produces = {"application/json"})
public class DailyActivityController {

    @Autowired
    private DailyActivityService dailyActivityService;

    @GetMapping("/dac")
    public void getDailyActivity(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam int idDetailEmployeeProject,
                                 @RequestParam int employeeId,
                                 @RequestParam String projectId) throws IOException {

        DacDTO dacDTO = dailyActivityService.getDailyActivity(idDetailEmployeeProject, employeeId, projectId);
        DataResponse<DacDTO> data = new DataResponse<>();
        data.setData(dacDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @PostMapping("/dac/check")
    public void checkDailyActivity(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam int employeeId,
                                     @RequestParam int idDetailEmployeeProject,
                                     @RequestParam String projectId,
                                     @RequestParam int plottingId,
                                     @RequestParam int shiftId,
                                     @RequestParam int locationId,
                                     @RequestParam int subLocationId,
                                     @RequestParam int activityId) throws IOException {

        ChecklistDACEmployeeDTO checklistDACEmployeeDTO = dailyActivityService.checkDailyActivity(employeeId, idDetailEmployeeProject, projectId, plottingId, shiftId, locationId, subLocationId, activityId);
        DataResponse<ChecklistDACEmployeeDTO> data = new DataResponse<>();
        data.setData(checklistDACEmployeeDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @PutMapping("/dac/confirm")
    public void confirmDailyActivity(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam int idDetailEmployeeProject) throws IOException {

        ScheduleDTO scheduleDTO = dailyActivityService.confirmDailyActivity(idDetailEmployeeProject);
        DataResponse<ScheduleDTO> data = new DataResponse<>();
        data.setData(scheduleDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/dac/status/checklist")
    public void getStatusChecklistDacByEmployee(HttpServletRequest request, HttpServletResponse response,
                                                @RequestParam int employeeId,
                                                @RequestParam String projectId,
                                                @RequestParam int idDetailEmployeeProject,
                                                @RequestParam int activityId,
                                                @RequestParam int plottingId) throws IOException {

        ChecklistDACEmployeeDTO checklistDACEmployeeDTO = dailyActivityService.getStatusChecklistDacByEmployee(employeeId, projectId, idDetailEmployeeProject, activityId, plottingId);

        if (checklistDACEmployeeDTO != null) {
            DataResponse<ChecklistDACEmployeeDTO> data = new DataResponse<>();
            data.setData(checklistDACEmployeeDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong or DAC not checked");
        }
    }
}
