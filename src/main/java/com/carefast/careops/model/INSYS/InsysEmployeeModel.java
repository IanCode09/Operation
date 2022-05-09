package com.carefast.careops.model.INSYS;

import com.carefast.careops.model.job.JobModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_employee_insys")
public class InsysEmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private int idEmployee;
    @Column(name = "employee_code")
    private String employeeCode;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "employee_phone")
    private String employeePhone;
    @Column(name = "employee_email")
    private String employeeEmail;
    @Column(name = "employee_password")
    private String employeePassword;
    @Column(name = "employee_password_show")
    private String employeePasswordShow;
    @Column(name = "employee_nik")
    private String employeeNik;
    @Type(type = "text")
    @Column(name = "employee_photo_profile")
    private String employeePhotoProfile;
    @Column(name = "job_code")
    private String jobCode;
    @Column(name = "job_name")
    private String jobName;
    @OneToOne
    @JoinColumn(name = "job_id")
    private JobModel job;
    @Column(name = "project_code")
    private String projectCode;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "begin_date")
    private LocalDate beginDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "is_active", columnDefinition = "enum('Y','N')")
    private String isActive;
    @Column(name = "is_regis_success", columnDefinition = "enum('Y','N')")
    private String isRegisSuccess;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Jakarta", locale = "id")
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Jakarta", locale = "id")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public InsysEmployeeModel() {
    }

    public InsysEmployeeModel(String employeeCode, String employeeName, String employeePhone, String employeeNik, String jobCode, String jobName, String projectCode, LocalDate beginDate, LocalDate endDate, String isActive, LocalDateTime createdAt) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeNik = employeeNik;
        this.jobCode = jobCode;
        this.jobName = jobName;
        this.projectCode = projectCode;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeePasswordShow() {
        return employeePasswordShow;
    }

    public void setEmployeePasswordShow(String employeePasswordShow) {
        this.employeePasswordShow = employeePasswordShow;
    }

    public String getEmployeeNik() {
        return employeeNik;
    }

    public void setEmployeeNik(String employeeNik) {
        this.employeeNik = employeeNik;
    }

    public String getEmployeePhotoProfile() {
        return employeePhotoProfile;
    }

    public void setEmployeePhotoProfile(String employeePhotoProfile) {
        this.employeePhotoProfile = employeePhotoProfile;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public JobModel getJob() {
        return job;
    }

    public void setJob(JobModel job) {
        this.job = job;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsRegisSuccess() {
        return isRegisSuccess;
    }

    public void setIsRegisSuccess(String isRegisSuccess) {
        this.isRegisSuccess = isRegisSuccess;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
