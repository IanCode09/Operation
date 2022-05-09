package com.carefast.careops.controller.attendance;

import com.carefast.careops.dto.attendance.AttendanceDTO;
import com.carefast.careops.dto.attendance.AttendanceNonOperatorStatusDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.attendance.GeoAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/employee", produces = {"application/json"})
public class GeoAttendanceNonOperatorController {

    @Autowired
    private GeoAttendanceService geoAttendanceService;

    @PostMapping("/attendance/non-operator/in/geo")
    public void employeeAttendanceInGeo(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam int employeeId,
                                        @RequestParam String projectCode,
                                        @RequestParam("file") MultipartFile file) throws IOException {

        AttendanceDTO attendanceDTO = geoAttendanceService.employeeAttendanceInGeo(employeeId, projectCode, file);

        if (attendanceDTO != null) {
            DataResponse<AttendanceDTO> data = new DataResponse<>();
            data.setData(attendanceDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong, please check your schedule");
        }
    }

    @PutMapping("/attendance/non-operator/out/geo")
    public void employeeAttendanceOutGeo(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam int employeeId,
                                        @RequestParam String projectCode,
                                        @RequestParam("file") MultipartFile file) throws IOException {

        AttendanceDTO attendanceDTO = geoAttendanceService.employeeAttendanceOutGeo(employeeId, projectCode, file);

        if (attendanceDTO != null) {
            DataResponse<AttendanceDTO> data = new DataResponse<>();
            data.setData(attendanceDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong, please check your schedule");
        }
    }

    @GetMapping("/attendance/non-operator/geo/status")
    public void getEmployeeNonOperatorAttendanceStatus(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestParam int employeeId,
                                                       @RequestParam String projectCode) throws IOException {

        AttendanceNonOperatorStatusDTO attendanceNonOperatorStatusDTO = geoAttendanceService.getEmployeeNonOperatorAttendanceStatus(employeeId, projectCode);

        if (attendanceNonOperatorStatusDTO != null) {
            DataResponse<AttendanceNonOperatorStatusDTO> data = new DataResponse<>();
            data.setData(attendanceNonOperatorStatusDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong");
        }
    }

    @GetMapping("/attendance/non-operator/geo/in/check")
    public void employeeNonOperatorAttendanceCheckInGeo(HttpServletRequest request, HttpServletResponse response,
                                                        @RequestParam int employeeId,
                                                        @RequestParam String projectCode) throws IOException {

        String result = geoAttendanceService.employeeNonOperatorAttendanceCheckInGeo(employeeId, projectCode);

        if (result == geoAttendanceService.PLEASE_WAIT) {
            HandlerResponse.responseSuccessOK(response, "Please wait, schedule not available");
        } else if (result == geoAttendanceService.SCAN_AVAILABLE) {
            HandlerResponse.responseSuccessOK(response, "Scan in available");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong");
        }
    }

    @GetMapping("/attendance/non-operator/geo/out/check")
    public void employeeNonOperatorAttendanceCheckOutGeo(HttpServletRequest request, HttpServletResponse response,
                                                         @RequestParam int employeeId,
                                                         @RequestParam String projectCode) throws IOException {

        String result = geoAttendanceService.employeeNonOperatorAttendanceCheckOutGeo(employeeId, projectCode);

        if (result == geoAttendanceService.PLEASE_WAIT) {
            HandlerResponse.responseSuccessOK(response, "Please wait, schedule not available");
        } else if (result == geoAttendanceService.SCAN_AVAILABLE) {
            HandlerResponse.responseSuccessOK(response, "Scan in available");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong");
        }
    }
}
