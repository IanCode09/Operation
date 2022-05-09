package com.carefast.careops.model.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_employee_detail_list_project")
public class EmployeeDetailListProjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_detail_employee_project")
    private int idDetailEmployeeProject;
    @Column(name = "id_project")
    private String idProject;
    @Column(name = "id_location")
    private Integer idLocation;
    @Column(name = "id_plotting")
    private Integer idPlotting;
    @Column(name = "id_shift")
    private int idShift;
//    @ManyToOne
//    @JoinColumn(name = "id_employee", nullable = false)
//    private EmployeeModel employeeModel;
    @Column(name = "id_employee")
    private int idEmployee;
    @Column(name = "job_code")
    private String jobCode;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "date")
    private LocalDate date;
    private int month;
    private int year;
    @Column(name = "schedule_type", columnDefinition = "enum('ACTUAL SCHEDULE','LEMBUR GANTI')")
    private String scheduleType;
    @Column(name = "is_off", columnDefinition = "enum('Y','N')")
    private String isOff;
    @Column(name = "status_attendance", columnDefinition = "enum('HADIR', 'IZIN', 'BELUM_ABSEN', 'LIBUR')")
    private String statusAttendance;
    @Column(name = "assign_by")
    private Integer assignBy;
    @Column(name = "status", columnDefinition = "enum('Y','N')")
    private String status;
    @Column(name = "checklist_by_employee", columnDefinition = "enum('Y','N')")
    private String checklistByEmployee;
    @Column(name = "is_done", columnDefinition = "enum('Y','N')")
    private String isDone;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "done_at")
    private LocalDateTime doneAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at_detail_list_project")
    private LocalDateTime createdAtDetailListProject;


    public int getIdDetailEmployeeProject() {
        return idDetailEmployeeProject;
    }

    public void setIdDetailEmployeeProject(int idDetailEmployeeProject) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public Integer getIdPlotting() {
        return idPlotting;
    }

    public void setIdPlotting(Integer idPlotting) {
        this.idPlotting = idPlotting;
    }

    public int getIdShift() {
        return idShift;
    }

    public void setIdShift(int idShift) {
        this.idShift = idShift;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getIsOff() {
        return isOff;
    }

    public void setIsOff(String isOff) {
        this.isOff = isOff;
    }

    public String getStatusAttendance() {
        return statusAttendance;
    }

    public void setStatusAttendance(String statusAttendance) {
        this.statusAttendance = statusAttendance;
    }

    public Integer getAssignBy() {
        return assignBy;
    }

    public void setAssignBy(Integer assignBy) {
        this.assignBy = assignBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChecklistByEmployee() {
        return checklistByEmployee;
    }

    public void setChecklistByEmployee(String checklistByEmployee) {
        this.checklistByEmployee = checklistByEmployee;
    }

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }

    public LocalDateTime getCreatedAtDetailListProject() {
        return createdAtDetailListProject;
    }

    public void setCreatedAtDetailListProject(LocalDateTime createdAtDetailListProject) {
        this.createdAtDetailListProject = createdAtDetailListProject;
    }
}
