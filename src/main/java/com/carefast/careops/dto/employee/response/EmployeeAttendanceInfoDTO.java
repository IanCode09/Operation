package com.carefast.careops.dto.employee.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.time.LocalTime;

public class EmployeeAttendanceInfoDTO {
    private int employeeId;
    private String projectCode;
    private String statusAttendance;
    private String statusAttendanceIn;
    private String statusAttendanceOut;
    EmployeeAttendanceDTO attendanceInfo;

    public EmployeeAttendanceInfoDTO() {
    }

    public EmployeeAttendanceInfoDTO(int employeeId, String projectCode, String statusAttendance, String statusAttendanceIn, String statusAttendanceOut, EmployeeAttendanceDTO attendanceInfo) {
        this.employeeId = employeeId;
        this.projectCode = projectCode;
        this.statusAttendance = statusAttendance;
        this.statusAttendanceIn = statusAttendanceIn;
        this.statusAttendanceOut = statusAttendanceOut;
        this.attendanceInfo = attendanceInfo;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStatusAttendance() {
        return statusAttendance;
    }

    public void setStatusAttendance(String statusAttendance) {
        this.statusAttendance = statusAttendance;
    }

    public String getStatusAttendanceIn() {
        return statusAttendanceIn;
    }

    public void setStatusAttendanceIn(String statusAttendanceIn) {
        this.statusAttendanceIn = statusAttendanceIn;
    }

    public String getStatusAttendanceOut() {
        return statusAttendanceOut;
    }

    public void setStatusAttendanceOut(String statusAttendanceOut) {
        this.statusAttendanceOut = statusAttendanceOut;
    }

    public EmployeeAttendanceDTO getAttendanceInfo() {
        return attendanceInfo;
    }

    public void setAttendanceInfo(EmployeeAttendanceDTO attendanceInfo) {
        this.attendanceInfo = attendanceInfo;
    }
}
