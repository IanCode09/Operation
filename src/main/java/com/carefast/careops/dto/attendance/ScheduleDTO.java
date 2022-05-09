package com.carefast.careops.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class ScheduleDTO {
    private int idDetailEmployeeProject;
    private int employeeId;
    private String projectId;
    private Integer locationId;
    private Integer plottingId;
    private int shiftId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    private String scheduleType;
    private String isOff;
    private String statusAttendance;
    private Integer assignBy;
    private String status;

    public ScheduleDTO() {
    }

    public ScheduleDTO(int idDetailEmployeeProject, int employeeId, String projectId, Integer locationId, Integer plottingId, int shiftId, LocalDate date, String scheduleType, String isOff, String statusAttendance, Integer assignBy, String status) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.locationId = locationId;
        this.plottingId = plottingId;
        this.shiftId = shiftId;
        this.date = date;
        this.scheduleType = scheduleType;
        this.isOff = isOff;
        this.statusAttendance = statusAttendance;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
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
}
