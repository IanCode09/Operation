package com.carefast.careops.model.employee;

import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tab_employee_project")
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee_project ")
    private int idEmployeeProject;
    @Column(name = "employee_nuc")
    private String employeeNuc;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "employee_job")
    private int employeeJob;
    @Column(name = "project_id")
    private int projectId;
    @Column(name = "employee_email")
    private String employeeEmail;
    @Column(name = "employee_password")
    private String employeePassword;
    @Column(name = "employee_password_show")
    private String employeePasswordShow;
    @Column(name = "employee_phone_number")
    private String employeePhoneNumber;
    @Column(name = "employee_status", columnDefinition = "enum('Y', 'N')")
    private String employeeStatus;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at_employee_project")
    private LocalDateTime createdAtEmployeeProject;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at_employee_project")
    private LocalDateTime updatedAtEmployeeProject;

//    @OneToMany(mappedBy = "employeeModel")
//    private List<EmployeeDetailListProjectModel> employeeDetailListProjectModels;


    public int getIdEmployeeProject() {
        return idEmployeeProject;
    }

    public void setIdEmployeeProject(int idEmployeeProject) {
        this.idEmployeeProject = idEmployeeProject;
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

    public int getEmployeeJob() {
        return employeeJob;
    }

    public void setEmployeeJob(int employeeJob) {
        this.employeeJob = employeeJob;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public LocalDateTime getCreatedAtEmployeeProject() {
        return createdAtEmployeeProject;
    }

    public void setCreatedAtEmployeeProject(LocalDateTime createdAtEmployeeProject) {
        this.createdAtEmployeeProject = createdAtEmployeeProject;
    }

    public LocalDateTime getUpdatedAtEmployeeProject() {
        return updatedAtEmployeeProject;
    }

    public void setUpdatedAtEmployeeProject(LocalDateTime updatedAtEmployeeProject) {
        this.updatedAtEmployeeProject = updatedAtEmployeeProject;
    }
}
