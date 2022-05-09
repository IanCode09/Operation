package com.carefast.careops.controller.attendance;

import com.carefast.careops.dto.attendance.AttendanceDTO;
import com.carefast.careops.dto.attendance.AttendanceStatusDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.attendance.GeoAttendanceOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/employee", produces = {"application/json"})
public class GeoAttendanceOperatorController {

    @Autowired
    private GeoAttendanceOperatorService geoAttendanceOperatorService;

    @PostMapping("/attendance/in/geo")
    public void employeeAttendanceInGeo(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam int employeeId,
                                        @RequestParam String projectCode,
                                        @RequestParam int scheduleId,
                                        @RequestParam("file") MultipartFile file) throws IOException {

        AttendanceDTO attendanceDTO = geoAttendanceOperatorService.employeeAttendanceInGeo(employeeId, projectCode, scheduleId, file);

        if (attendanceDTO != null) {
            DataResponse<AttendanceDTO> data = new DataResponse<>();
            data.setData(attendanceDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong, please check your schedule");
        }
    }

    @PutMapping("/attendance/out/geo")
    public void employeeAttendanceOutGeo(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam int employeeId,
                                         @RequestParam String projectCode,
                                         @RequestParam int scheduleId,
                                         @RequestParam("file") MultipartFile file) throws IOException {

        AttendanceDTO attendanceDTO = geoAttendanceOperatorService.employeeAttendanceOutGeo(employeeId, projectCode, scheduleId, file);

        if (attendanceDTO != null) {
            DataResponse<AttendanceDTO> data = new DataResponse<>();
            data.setData(attendanceDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong, please check your schedule");
        }
    }

    @GetMapping("/attendance/in/check/{id}")
    public void employeeAttendanceCheckInGeo(HttpServletRequest request, HttpServletResponse response,
                                             @PathVariable int id) throws IOException {

        String result = geoAttendanceOperatorService.employeeAttendanceCheckInGeo(id);

        if (result == geoAttendanceOperatorService.PLEASE_WAIT) {
            HandlerResponse.responseSuccessOK(response, "Please wait, schedule not available");
        } else if (result == geoAttendanceOperatorService.LATE_SIXTY_MINUTES) {
                HandlerResponse.responseSuccessOK(response, "Sorry your schedule over 60 minutes");
        } else if (result == geoAttendanceOperatorService.SCAN_AVAILABLE) {
            HandlerResponse.responseSuccessOK(response, "Scan in available");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong");
        }
    }

    @GetMapping("/attendance/out/check")
    public void employeeAttendanceCheckOutGeo(HttpServletRequest request, HttpServletResponse response,
                                              @RequestParam int scheduleId,
                                              @RequestParam int employeeId) throws IOException {

        String result = geoAttendanceOperatorService.employeeAttendanceCheckOutGeo(scheduleId, employeeId);

        if (result == geoAttendanceOperatorService.PLEASE_WAIT) {
            HandlerResponse.responseSuccessOK(response, "Please wait, schedule not available");
        } else if (result == geoAttendanceOperatorService.SCAN_AVAILABLE) {
            HandlerResponse.responseSuccessOK(response, "Scan in available");
        } else if (result == geoAttendanceOperatorService.DAC_NOT_CHECKED) {
            HandlerResponse.responseSuccessOK(response, "DAC not checked");
        } else if (result == geoAttendanceOperatorService.IS_OVER_EIGHT_HOURS) {
            HandlerResponse.responseSuccessOK(response, "Sorry your schedule over 8 hours");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong");
        }
    }

    @GetMapping("/attendance/geo/status")
    public void getEmployeeAttendanceStatus(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam int employeeId,
                                         @RequestParam String projectCode) throws IOException {

        AttendanceStatusDTO attendanceStatusDTO = geoAttendanceOperatorService.getEmployeeAttendanceStatus(employeeId, projectCode);

        if (attendanceStatusDTO != null) {
            DataResponse<AttendanceStatusDTO> data = new DataResponse<>();
            data.setData(attendanceStatusDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong");
        }
    }
}
