package com.carefast.careops.dto.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalTime;

public class CustomEmployeeScheduleLeaderDTO {
    private int idDetailEmployeeProject;
    private String idProject;
    private int idEmployee;
    private String employeeName;
    private int idShift;
    private String shiftDescription;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime startAt;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime endAt;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", locale = "id", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "EEEE, dd-MM-yyyy", locale = "id", shape = JsonFormat.Shape.STRING)
    private LocalDate dayDate;
    private String scheduleType;
    private String isOff;
    private String statusAttendance;

    public CustomEmployeeScheduleLeaderDTO() {
    }

    public CustomEmployeeScheduleLeaderDTO(int idDetailEmployeeProject, String idProject, int idEmployee, String employeeName, int idShift, String shiftDescription, LocalTime startAt, LocalTime endAt, LocalDate date, LocalDate dayDate, String scheduleType, String isOff, String statusAttendance) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.idProject = idProject;
        this.idEmployee = idEmployee;
        this.employeeName = employeeName;
        this.idShift = idShift;
        this.shiftDescription = shiftDescription;
        this.startAt = startAt;
        this.endAt = endAt;
        this.date = date;
        this.dayDate = dayDate;
        this.scheduleType = scheduleType;
        this.isOff = isOff;
        this.statusAttendance = statusAttendance;
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

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public LocalTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalTime endAt) {
        this.endAt = endAt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDayDate() {
        return dayDate;
    }

    public void setDayDate(LocalDate dayDate) {
        this.dayDate = dayDate;
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
}
