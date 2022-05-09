package com.carefast.careops.dto.employee.response;

import com.carefast.careops.dto.job.JobPositionDTO;
import com.carefast.careops.dto.project.ProjectDTO;
import com.carefast.careops.dto.project.ProjectDTOInsys;

public class ProfileDetailDTO {

    private int idEmployee;
    private String employeeNuc;
    private String employeeName;
    private String employeePhoneNumber;
    private String employeeEmail;
    private String employeeNik;
    private String employeePhotoProfile;
    private String jobCode;
    private String jobName;
    private String jobLevel;
    private JobPositionDTO job;
    private ProjectDTOInsys project;

    public ProfileDetailDTO() {
    }

    public ProfileDetailDTO(int idEmployee, String employeeNuc, String employeeName, String employeePhoneNumber, String employeeEmail, String employeeNik, String employeePhotoProfile, String jobCode, String jobName, String jobLevel, JobPositionDTO job, ProjectDTOInsys project) {
        this.idEmployee = idEmployee;
        this.employeeNuc = employeeNuc;
        this.employeeName = employeeName;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeEmail = employeeEmail;
        this.employeeNik = employeeNik;
        this.employeePhotoProfile = employeePhotoProfile;
        this.jobCode = jobCode;
        this.jobName = jobName;
        this.jobLevel = jobLevel;
        this.job = job;
        this.project = project;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmployeeNuc() {
        return employeeNuc;
    }

    public void setEmployeeNuc(String employeeNuc) {
        this.employeeNuc = employeeNuc;
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

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public JobPositionDTO getJob() {
        return job;
    }

    public void setJob(JobPositionDTO job) {
        this.job = job;
    }

    public ProjectDTOInsys getProject() {
        return project;
    }

    public void setProject(ProjectDTOInsys project) {
        this.project = project;
    }
}
