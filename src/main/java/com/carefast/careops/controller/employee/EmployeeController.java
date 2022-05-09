package com.carefast.careops.controller.employee;

import com.carefast.careops.dto.activity.DacDTO;
import com.carefast.careops.dto.activity.DailyActivityDTO;
import com.carefast.careops.dto.employee.request.EmployeeChangePasswordRequest;
import com.carefast.careops.dto.employee.request.GantiPasswordRequest;
import com.carefast.careops.dto.employee.response.EmployeeProjectDTO;
import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.employee.response.ProfileDetailDTO;
import com.carefast.careops.dto.schedule.ScheduleShiftDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/employee", produces = {"application/json"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/profile")
    public void getEmployeeProfile(HttpServletRequest request, HttpServletResponse response, @RequestParam int employeeId) throws IOException {

        ProfileDetailDTO profileDetailDTO = employeeService.getEmployeeProfile(employeeId);

        if (profileDetailDTO != null) {
            DataResponse<ProfileDetailDTO> data = new DataResponse<>();
            data.setData(profileDetailDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "98", "User not found");
        }
    }

    @GetMapping("/projects")
    public void getProjects(HttpServletRequest request, HttpServletResponse response, @RequestParam int employeeId) throws IOException {
        EmployeeProjectDTO employeeProjects = employeeService.getEmployeeProjects(employeeId);

        if (employeeProjects != null) {
            DataResponse<EmployeeProjectDTO> data = new DataResponse<>();
            data.setData(employeeProjects);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Project not found");
        }
    }

    @GetMapping("/activities")
    public void getUserActivities(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam int employeeId, @RequestParam String projectId) throws IOException {

        DailyActivityDTO dailyActivityDTO = employeeService.getEmployeeDailyActivity(employeeId, projectId);

        if (dailyActivityDTO != null) {
            DataResponse<DailyActivityDTO> data = new DataResponse<>();
            data.setData(dailyActivityDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something Wrong or User not assign in plotting");
        }
    }

    @PutMapping("/change-password")
    public void changePassword(HttpServletRequest request, HttpServletResponse response, @RequestBody EmployeeChangePasswordRequest changePasswordRequest) throws IOException {

        int changePassword = employeeService.changePassword(changePasswordRequest.getEmail(), changePasswordRequest.getPassword(), changePasswordRequest.getConfirmPassword());

        if (changePassword == EmployeeService.CHANGE_PASSWORD_SUCCESS) {
            HandlerResponse.responseSuccessOK(response, "Success Change Password");
        } else if (changePassword == EmployeeService.PASSWORD_UN_MATCH) {
            HandlerResponse.responseBadRequest(response, "01", "Your password incorrect");
        } else if (changePassword == EmployeeService.USER_NOT_FOUND) {
            HandlerResponse.responseBadRequest(response, "02", "User not found");
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something Wrong");
        }
    }

    @GetMapping("/activities/new")
    public void getUserActivitiesNew(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam int employeeId, @RequestParam String projectId) throws IOException {

        DacDTO dailyActivityDTO = employeeService.getDetailDailyActivity(employeeId, projectId);

        if (dailyActivityDTO != null) {
            DataResponse<DacDTO> data = new DataResponse<>();
            data.setData(dailyActivityDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something Wrong or User not assign in plotting");
        }
    }

    @PutMapping("/profile/update-password")
    public void gantiPassword(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam int employeeId,
                              @RequestBody GantiPasswordRequest gantiPasswordRequest) throws IOException {

        int gantiPassword = employeeService.gantiPassword(employeeId, gantiPasswordRequest.getOldPassword(),
                gantiPasswordRequest.getPassword(), gantiPasswordRequest.getConfirmPassword());

        if (gantiPassword == EmployeeService.CHANGE_PASSWORD_SUCCESS) {
            HandlerResponse.responseSuccessOK(response, "Success Change Password");
        } else if (gantiPassword == EmployeeService.PASSWORD_UN_MATCH) {
            HandlerResponse.responseBadRequest(response, "01", "Your password incorrect");
        } else if (gantiPassword == EmployeeService.USER_NOT_FOUND) {
            HandlerResponse.responseBadRequest(response, "02", "User not found or Your password wrong");
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something Wrong");
        }
    }

    @PutMapping("/profile/update")
    public void updateProfile(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam int employeeId,
                              @RequestParam String employeeEmail,
                              @RequestParam String employeePhone,
                              @RequestParam("file") MultipartFile file) throws IOException {

        ProfileDTO profileDTO = employeeService.updateProfile(employeeId, employeeEmail, employeePhone, file);

        if (profileDTO != null) {
            DataResponse<ProfileDTO> data = new DataResponse<>();
            data.setData(profileDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "98", "Failed to Edit Profile");
        }
    }

    @GetMapping("/schedule/date")
    public void getEmployeeScheduleByDate(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam int employeeId,
                                    @RequestParam String projectId,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<ScheduleShiftDTO> scheduleShiftDTO = employeeService.getEmployeeSchedule(employeeId, projectId, date);

        DataResponse<List<ScheduleShiftDTO>> data = new DataResponse<>();
        data.setData(scheduleShiftDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }
}
