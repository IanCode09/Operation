package com.carefast.careops.dto.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CustomEmployeeDetailListProjectDTO {
    private int idDetailEmployeeProject;
    private int idEmployee;
    private String employeeName;
    private String idProject;
    private String scheduleType;
    private Integer idPlotting;
    private String codePlottingArea;
    private Integer locationId;
    private String locationName;
    private Integer subLocationId;
    private String subLocationName;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", locale = "id", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "EEEE, dd-MM-yyyy", locale = "id", shape = JsonFormat.Shape.STRING)
    private LocalDate dayDate;
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
    private String isDone;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime doneAt;

    public CustomEmployeeDetailListProjectDTO() {
    }

    public CustomEmployeeDetailListProjectDTO(int idDetailEmployeeProject, int idEmployee, String employeeName, String idProject, String scheduleType, Integer idPlotting, String codePlottingArea, Integer locationId, String locationName, Integer subLocationId, String subLocationName, LocalDate date, LocalDate dayDate, int idShift, String shiftDescription, LocalTime startAt, LocalTime endAt, String isDone, LocalDateTime doneAt) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.idEmployee = idEmployee;
        this.employeeName = employeeName;
        this.idProject = idProject;
        this.scheduleType = scheduleType;
        this.idPlotting = idPlotting;
        this.codePlottingArea = codePlottingArea;
        this.locationId = locationId;
        this.locationName = locationName;
        this.subLocationId = subLocationId;
        this.subLocationName = subLocationName;
        this.date = date;
        this.dayDate = dayDate;
        this.idShift = idShift;
        this.shiftDescription = shiftDescription;
        this.startAt = startAt;
        this.endAt = endAt;
        this.isDone = isDone;
        this.doneAt = doneAt;
    }

    public int getIdDetailEmployeeProject() {
        return idDetailEmployeeProject;
    }

    public void setIdDetailEmployeeProject(int idDetailEmployeeProject) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
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

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Integer getIdPlotting() {
        return idPlotting;
    }

    public void setIdPlotting(Integer idPlotting) {
        this.idPlotting = idPlotting;
    }

    public String getCodePlottingArea() {
        return codePlottingArea;
    }

    public void setCodePlottingArea(String codePlottingArea) {
        this.codePlottingArea = codePlottingArea;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(Integer subLocationId) {
        this.subLocationId = subLocationId;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
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
