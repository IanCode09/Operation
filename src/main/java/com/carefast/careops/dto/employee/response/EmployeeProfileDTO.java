package com.carefast.careops.dto.employee.response;

import com.carefast.careops.dto.job.JobPositionDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.Column;
import java.time.LocalDate;

public class EmployeeProfileDTO {
    private int idEmployee;
    private String employeeCode;
    private String employeeName;
    private String employeePhoneNumber;
    private String employeePhotoProfile;
    private String employeeEmail;
    private String employeeNik;
    private String jobCode;
    private String jobName;
    private JobPositionDTO job;
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

    public EmployeeProfileDTO() {
    }

    public EmployeeProfileDTO(int idEmployee, String employeeCode, String employeeName, String employeePhoneNumber, String employeePhotoProfile, String employeeEmail, String employeeNik, String jobCode, String jobName, JobPositionDTO job, String projectCode, LocalDate beginDate, LocalDate endDate) {
        this.idEmployee = idEmployee;
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeePhotoProfile = employeePhotoProfile;
        this.employeeEmail = employeeEmail;
        this.employeeNik = employeeNik;
        this.jobCode = jobCode;
        this.jobName = jobName;
        this.job = job;
        this.projectCode = projectCode;
        this.beginDate = beginDate;
        this.endDate = endDate;
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

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeePhotoProfile() {
        return employeePhotoProfile;
    }

    public void setEmployeePhotoProfile(String employeePhotoProfile) {
        this.employeePhotoProfile = employeePhotoProfile;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeNik() {
        return employeeNik;
    }

    public void setEmployeeNik(String employeeNik) {
        this.employeeNik = employeeNik;
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

    public JobPositionDTO getJob() {
        return job;
    }

    public void setJob(JobPositionDTO job) {
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
}
