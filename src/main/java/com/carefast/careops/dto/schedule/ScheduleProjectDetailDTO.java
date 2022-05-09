package com.carefast.careops.dto.schedule;

import com.carefast.careops.dto.project.ProjectDTOInsys;
import com.carefast.careops.dto.project.ProjectShiftDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class ScheduleProjectDetailDTO {
    private int idDetailEmployeeProject;
    private int employeeId;
    private ProjectDTOInsys project;
    private Integer locationId;
    private Integer plottingId;
    private DetailShiftDTO scheduleShift;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    private String scheduleType;
    private Integer assignBy;
    private String status;

    public ScheduleProjectDetailDTO() {
    }

    public ScheduleProjectDetailDTO(int idDetailEmployeeProject, int employeeId, ProjectDTOInsys project, Integer locationId, Integer plottingId, DetailShiftDTO scheduleShift, LocalDate date, String scheduleType, Integer assignBy, String status) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.employeeId = employeeId;
        this.project = project;
        this.locationId = locationId;
        this.plottingId = plottingId;
        this.scheduleShift = scheduleShift;
        this.date = date;
        this.scheduleType = scheduleType;
        this.assignBy = assignBy;
        this.status = status;
    }

    public int getIdDetailEmployeeProject() {
        return idDetailEmployeeProject;
    }

    public void setIdDetailEmployeeProject(int idDetailEmployeeProject) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public ProjectDTOInsys getProject() {
        return project;
    }

    public void setProject(ProjectDTOInsys project) {
        this.project = project;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getPlottingId() {
        return plottingId;
    }

    public void setPlottingId(Integer plottingId) {
        this.plottingId = plottingId;
    }

    public DetailShiftDTO getScheduleShift() {
        return scheduleShift;
    }

    public void setScheduleShift(DetailShiftDTO scheduleShift) {
        this.scheduleShift = scheduleShift;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
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
}
