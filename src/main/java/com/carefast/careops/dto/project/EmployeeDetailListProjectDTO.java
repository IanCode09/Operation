package com.carefast.careops.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeDetailListProjectDTO {

    private int idDetailEmployeeProject;
    private String idProject;
    private Integer idLocation;
    private Integer idPlotting;
    private int idShift;
    private String shiftDescription;
    private int idEmployee;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    private int month;
    private int year;
    private String scheduleType;
    private String isOff;
    private String statusAttendance;
    private Integer assignBy;
    private String status;
    private String checklistByEmployee;
    private String isDone;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "done_at")
    private LocalDateTime doneAt;

    public EmployeeDetailListProjectDTO() {
    }

    public EmployeeDetailListProjectDTO(int idDetailEmployeeProject, String idProject, Integer idLocation, Integer idPlotting, int idShift, String shiftDescription, int idEmployee, LocalDate date, int month, int year, String scheduleType, String isOff, String statusAttendance, Integer assignBy, String status, String checklistByEmployee, String isDone, LocalDateTime doneAt) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.idProject = idProject;
        this.idLocation = idLocation;
        this.idPlotting = idPlotting;
        this.idShift = idShift;
        this.shiftDescription = shiftDescription;
        this.idEmployee = idEmployee;
        this.date = date;
        this.month = month;
        this.year = year;
        this.scheduleType = scheduleType;
        this.isOff = isOff;
        this.statusAttendance = statusAttendance;
        this.assignBy = assignBy;
        this.status = status;
        this.checklistByEmployee = checklistByEmployee;
        this.isDone = isDone;
        this.doneAt = doneAt;
    }

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

    public String getShiftDescription() {
        return shiftDescription;
    }

    public void setShiftDescription(String shiftDescription) {
        this.shiftDescription = shiftDescription;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
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
}
