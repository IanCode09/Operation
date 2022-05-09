package com.carefast.careops.dto.employee.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmployeeAttendanceDTO {
    private int attendanceId;
    private int employeeId;
    private String projectCode;
    private String projectName;
    private String barcodeKey;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Jakarta", locale = "id")
    private LocalDateTime scanIn;
    private String employeeImgSelfieIn;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Jakarta", locale = "id")
    private LocalDateTime scanOut;
    private String employeeImgSelfieOut;
    private String isLate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate createdAt;

    public EmployeeAttendanceDTO() {
    }

    public EmployeeAttendanceDTO(int attendanceId, int employeeId, String projectCode, String projectName, String barcodeKey, LocalDateTime scanIn, String employeeImgSelfieIn, LocalDateTime scanOut, String employeeImgSelfieOut, String isLate, LocalDate createdAt) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.barcodeKey = barcodeKey;
        this.scanIn = scanIn;
        this.employeeImgSelfieIn = employeeImgSelfieIn;
        this.scanOut = scanOut;
        this.employeeImgSelfieOut = employeeImgSelfieOut;
        this.isLate = isLate;
        this.createdAt = createdAt;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBarcodeKey() {
        return barcodeKey;
    }

    public void setBarcodeKey(String barcodeKey) {
        this.barcodeKey = barcodeKey;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
