package com.carefast.careops.controller.attendance;

import com.carefast.careops.dto.attendance.AttendanceDTO;
import com.carefast.careops.dto.attendance.EmployeeAttendanceBelumAbsenDTO;
import com.carefast.careops.dto.attendance.EmployeeAttendanceSudahAbsenDTO;
import com.carefast.careops.dto.employee.response.EmployeeAttendanceDTO;
import com.carefast.careops.dto.employee.response.EmployeeAttendanceInfoDTO;
import com.carefast.careops.dto.employee.response.EmployeeProfileDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.attendance.EmployeeAttendanceService;
import com.carefast.careops.service.attendance.GeoAttendanceOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/employee", produces = {"application/json"})
public class AttendanceController {

    @Autowired
    private EmployeeAttendanceService employeeAttService;

    @PostMapping("/attendance/in")
    public void employeeAttendanceIn(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam int employeeId,
                                   @RequestParam String projectCode,
                                   @RequestParam String barcodeKey,
                                   @RequestParam("file") MultipartFile file) throws IOException {

        boolean attendanceIn =  employeeAttService.employeeAttendanceIn(employeeId, projectCode, barcodeKey, file);

        if (attendanceIn) {
            HandlerResponse.responseSuccessCreated(response, "Success SCAN IN");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong or Your schedule has passed");
        }
    }

    @PutMapping("/attendance/out")
    public void employeeAttendanceOut(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam int employeeId,
                                     @RequestParam String projectCode,
                                     @RequestParam String barcodeKey,
                                     @RequestParam("file") MultipartFile file) throws IOException {

        boolean attendanceOut =  employeeAttService.employeeAttendanceOut(employeeId, projectCode, barcodeKey, file);

        if (attendanceOut) {
            HandlerResponse.responseSuccessCreated(response, "Success SCAN OUT");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong or Your schedule for scan out has passed");
        }
    }

    @GetMapping("/attendance/status")
    public void employeeAttendanceStatus(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam int employeeId,
                                         @RequestParam String projectCode) throws IOException {

        EmployeeAttendanceInfoDTO attendanceInfoDTO = employeeAttService.employeeAttendanceInfo(employeeId, projectCode);

        if (attendanceInfoDTO != null) {
            DataResponse<EmployeeAttendanceInfoDTO> data = new DataResponse<>();
            data.setData(attendanceInfoDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "User not found");
        }
    }

    @GetMapping("/attendance/history")
    public void employeeAttendanceHistory(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam int employeeId,
                                          @RequestParam String projectCode) throws IOException {

        List<EmployeeAttendanceDTO> employeeAttendanceDTOS = new ArrayList<>();
        employeeAttendanceDTOS.addAll(employeeAttService.getAllEmployeeAtt(employeeId, projectCode));

        DataResponse<List<EmployeeAttendanceDTO>> data = new DataResponse<>();
        data.setData(employeeAttendanceDTOS);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/attendance/history/date")
    public void employeeAttendanceHistoryByDate(HttpServletRequest request, HttpServletResponse response,
                                                @RequestParam int employeeId,
                                                @RequestParam String projectCode,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePrefix,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSuffix) throws IOException {

        List<EmployeeAttendanceDTO> employeeAttendanceDTO = new ArrayList<>();
        employeeAttendanceDTO.addAll(employeeAttService.getEmployeeAttByDate(employeeId, projectCode, datePrefix, dateSuffix));

        DataResponse<List<EmployeeAttendanceDTO>> data = new DataResponse<>();
        data.setData(employeeAttendanceDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/attendance/belum-absen")
    public void employeeAttendanceBelumAbsen(HttpServletRequest request, HttpServletResponse response,
                                                @RequestParam int employeeId,
                                                @RequestParam int shiftId,
                                                @RequestParam String projectCode) throws IOException {

        EmployeeAttendanceBelumAbsenDTO employeeProfileDTO = employeeAttService.employeeBelumAbsen(employeeId, projectCode, shiftId);

        DataResponse<EmployeeAttendanceBelumAbsenDTO> data = new DataResponse<>();
        data.setData(employeeProfileDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/spv/attendance/belum-absen")
    public void getSPVTeamEmployeeBelumAbsen(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam int employeeId,
                                             @RequestParam int shiftId,
                                             @RequestParam String projectCode) throws IOException {

        EmployeeAttendanceBelumAbsenDTO employeeProfileDTO = employeeAttService.getSPVTeamEmployeeBelumAbsen(employeeId, projectCode, shiftId);

        DataResponse<EmployeeAttendanceBelumAbsenDTO> data = new DataResponse<>();
        data.setData(employeeProfileDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/chief-spv/attendance/belum-absen")
    public void getChiefSupervisorTeamBelumAbsen(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam int employeeId, @RequestParam String projectCode, @RequestParam int shiftId) throws IOException {

        EmployeeAttendanceBelumAbsenDTO employeeProfileDTO = employeeAttService.getChiefSupervisorTeamBelumAbsen(employeeId, projectCode, shiftId);

        DataResponse<EmployeeAttendanceBelumAbsenDTO> data = new DataResponse<>();
        data.setData(employeeProfileDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/attendance/sudah-absen")
    public void employeeAttendanceSudahAbsen(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam int employeeId,
                                             @RequestParam int shiftId,
                                             @RequestParam String projectCode) throws IOException {

        EmployeeAttendanceSudahAbsenDTO employeeAttendanceSudahAbsenDTO = employeeAttService.employeeSudahAbsen(employeeId, projectCode, shiftId);

        DataResponse<EmployeeAttendanceSudahAbsenDTO> data = new DataResponse<>();
        data.setData(employeeAttendanceSudahAbsenDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/spv/attendance/sudah-absen")
    public void getSPVTeamEmployeeSudahAbsen(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam int employeeId,
                                             @RequestParam int shiftId,
                                             @RequestParam String projectCode) throws IOException {

        EmployeeAttendanceSudahAbsenDTO employeeAttendanceSudahAbsenDTO = employeeAttService.getSPVTeamEmployeeSudahAbsen(employeeId, projectCode, shiftId);

        DataResponse<EmployeeAttendanceSudahAbsenDTO> data = new DataResponse<>();
        data.setData(employeeAttendanceSudahAbsenDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/chief-spv/attendance/sudah-absen")
    public void getChiefSupervisorTeamSudahAbsen(HttpServletRequest request, HttpServletResponse response,
                                                 @RequestParam String projectCode, @RequestParam int shiftId) throws IOException {

        EmployeeAttendanceSudahAbsenDTO employeeAttendanceSudahAbsenDTO = employeeAttService.getChiefSupervisorTeamSudahAbsen(projectCode, shiftId);

        DataResponse<EmployeeAttendanceSudahAbsenDTO> data = new DataResponse<>();
        data.setData(employeeAttendanceSudahAbsenDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @PostMapping("/attendance/in/diabsenkan")
    public void employeeAttendanceInDiabsenkan(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam int leaderId,
                                               @RequestParam int employeeId,
                                               @RequestParam String projectCode,
                                               @RequestParam String barcodeKey,
                                               @RequestParam("file") MultipartFile file) throws IOException {

        boolean attendanceInByLeader = employeeAttService.employeeAttendanceInByLeader(leaderId, employeeId, projectCode, barcodeKey, file);

        if (attendanceInByLeader) {
            HandlerResponse.responseSuccessCreated(response, "Success SCAN IN By Leader");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something wrong or Your schedule has passed");
        }
    }

    @PostMapping("/attendance/in/check")
    public void employeeAttendanceCheckIn(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam int employeeId,
                                     @RequestParam String projectCode) throws IOException {

        int attendance =  employeeAttService.employeeAttendanceCheckIn(employeeId, projectCode);

        if (attendance == employeeAttService.PLEASE_WAIT) {
            HandlerResponse.responseSuccessOK(response, "Please wait, schedule not available");
        } else if (attendance == employeeAttService.SCAN_AVAILABLE){
            HandlerResponse.responseSuccessOK(response, "Scan in available");
        } else if (attendance == employeeAttService.USER_NOT_FOUND) {
            HandlerResponse.responseBadRequest(response, "01", "User not found");
        }
    }

    @PostMapping("/attendance/out/check")
    public void employeeAttendanceCheckOut(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam int employeeId,
                                        @RequestParam String projectCode) throws IOException {

        int attendance =  employeeAttService.employeeAttendanceCheckOut(employeeId, projectCode);

        if (attendance == employeeAttService.PLEASE_WAIT) {
            HandlerResponse.responseSuccessOK(response, "Please wait, schedule not available");
        } else if (attendance == employeeAttService.SCAN_NOT_AVAILABLE) {
            HandlerResponse.responseBadRequest(response, "05", "Scan out not available");
        } else if (attendance == employeeAttService.SCAN_AVAILABLE){
            HandlerResponse.responseSuccessOK(response, "Scan out available");
        } else if (attendance == employeeAttService.USER_NOT_FOUND) {
            HandlerResponse.responseBadRequest(response, "01", "User not found");
        }
    }

    @GetMapping("/attendance")
    public void getAttendance(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam int employeeId, @RequestParam String projectCode) throws IOException {

        AttendanceDTO attendanceDTO = employeeAttService.getAttendance(employeeId, projectCode);

        if (attendanceDTO != null) {
            DataResponse<AttendanceDTO> data = new DataResponse<>();
            data.setData(attendanceDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseSuccessOK(response, "Your schedule not found or something wrong");
        }
    }

    @GetMapping("/attendance/{scheduleId}")
    public void getDetailAttendance(HttpServletRequest request, HttpServletResponse response, @PathVariable int scheduleId) throws IOException {
        AttendanceDTO attendanceDTO = employeeAttService.getDetailAttendance(scheduleId);

        if (attendanceDTO != null) {
            DataResponse<AttendanceDTO> data = new DataResponse<>();
            data.setData(attendanceDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "404", "Schedule not found or something wrong");
        }
    }
}
