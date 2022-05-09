package com.carefast.careops.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class AttendanceDTO {
    private int attendanceId;
    private int employeeId;
    private String projectCode;
    private Integer idDetailEmployeeProject;
    private Integer idScheduleLeader;
    private String attendanceType;
    private Integer attendanceBy;
    private String barcodeKey;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime scanOutAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime scanIn;
    private String employeeImgSelfieIn;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime scanOut;
    private String employeeImgSelfieOut;
    private String isLate;

    public AttendanceDTO() {
    }

    public AttendanceDTO(int attendanceId, int employeeId, String projectCode, Integer idDetailEmployeeProject, Integer idScheduleLeader, String attendanceType, Integer attendanceBy, String barcodeKey, LocalDateTime scanOutAt, LocalDateTime scanIn, String employeeImgSelfieIn, LocalDateTime scanOut, String employeeImgSelfieOut, String isLate) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.projectCode = projectCode;
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.idScheduleLeader = idScheduleLeader;
        this.attendanceType = attendanceType;
        this.attendanceBy = attendanceBy;
        this.barcodeKey = barcodeKey;
        this.scanOutAt = scanOutAt;
        this.scanIn = scanIn;
        this.employeeImgSelfieIn = employeeImgSelfieIn;
        this.scanOut = scanOut;
        this.employeeImgSelfieOut = employeeImgSelfieOut;
        this.isLate = isLate;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
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

    public Integer getIdDetailEmployeeProject() {
        return idDetailEmployeeProject;
    }

    public void setIdDetailEmployeeProject(Integer idDetailEmployeeProject) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
    }

    public Integer getIdScheduleLeader() {
        return idScheduleLeader;
    }

    public void setIdScheduleLeader(Integer idScheduleLeader) {
        this.idScheduleLeader = idScheduleLeader;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public Integer getAttendanceBy() {
        return attendanceBy;
    }

    public void setAttendanceBy(Integer attendanceBy) {
        this.attendanceBy = attendanceBy;
    }

    public String getBarcodeKey() {
        return barcodeKey;
    }

    public void setBarcodeKey(String barcodeKey) {
        this.barcodeKey = barcodeKey;
    }

    public LocalDateTime getScanOutAt() {
        return scanOutAt;
    }

    public void setScanOutAt(LocalDateTime scanOutAt) {
        this.scanOutAt = scanOutAt;
    }

    public LocalDateTime getScanIn() {
        return scanIn;
    }

    public void setScanIn(LocalDateTime scanIn) {
        this.scanIn = scanIn;
    }

    public String getEmployeeImgSelfieIn() {
        return employeeImgSelfieIn;
    }

    public void setEmployeeImgSelfieIn(String employeeImgSelfieIn) {
        this.employeeImgSelfieIn = employeeImgSelfieIn;
    }

    public LocalDateTime getScanOut() {
        return scanOut;
    }

    public void setScanOut(LocalDateTime scanOut) {
        this.scanOut = scanOut;
    }

    public String getEmployeeImgSelfieOut() {
        return employeeImgSelfieOut;
    }

    public void setEmployeeImgSelfieOut(String employeeImgSelfieOut) {
        this.employeeImgSelfieOut = employeeImgSelfieOut;
    }

    public String getIsLate() {
        return isLate;
    }

    public void setIsLate(String isLate) {
        this.isLate = isLate;
    }
}
