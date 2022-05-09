package com.carefast.careops.model.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tab_employee_attendance")
public class EmployeeAttendanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="attendance_id")
    private int attendanceId;
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "job_code")
    private String jobCode;
    @Column(name = "project_code")
    private String projectCode;
    @Column(name = "id_detail_employee_project")
    private Integer idDetailEmployeeProject;
    @Column(name = "id_schedule_leader")
    private Integer idScheduleLeader;
    @Column(name = "attendance_type", columnDefinition = "enum('SENDIRI','DIABSENKAN')")
    private String attendanceType;
    @Column(name = "attendance_by")
    private Integer attendanceBy;
    @Column(name = "barcode_key")
    private String barcodeKey;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "scan_out_at")
    private LocalDateTime scanOutAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "scan_in")
    private LocalDateTime scanIn;
    @Type(type = "text")
    @Column(name = "employee_img_selfie_in")
    private String employeeImgSelfieIn;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "scan_out")
    private LocalDateTime scanOut;
    @Type(type = "text")
    @Column(name = "employee_img_selfie_out")
    private String employeeImgSelfieOut;
    @Column(name = "is_late", columnDefinition = "enum('Y','N')")
    private String isLate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at")
    private LocalDate createdAt;

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

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
