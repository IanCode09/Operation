package com.carefast.careops.dto.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalTime;

public class ScheduleNonOperatorDTO {
    private int idScheduleLeader;
    private int idEmployee;
    private String projectCode;
    private int monthLeader;
    private int yearLeader;
    private int shiftId;
    private int idDetailShift;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime startAt;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime endAt;
    private String latitude;
    private String longitude;
    private Integer radius;

    public ScheduleNonOperatorDTO() {
    }

    public ScheduleNonOperatorDTO(int idScheduleLeader, int idEmployee, String projectCode, int monthLeader, int yearLeader, int shiftId, int idDetailShift, LocalTime startAt, LocalTime endAt, String latitude, String longitude, Integer radius) {
        this.idScheduleLeader = idScheduleLeader;
        this.idEmployee = idEmployee;
        this.projectCode = projectCode;
        this.monthLeader = monthLeader;
        this.yearLeader = yearLeader;
        this.shiftId = shiftId;
        this.idDetailShift = idDetailShift;
        this.startAt = startAt;
        this.endAt = endAt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public int getIdScheduleLeader() {
        return idScheduleLeader;
    }

    public void setIdScheduleLeader(int idScheduleLeader) {
        this.idScheduleLeader = idScheduleLeader;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getMonthLeader() {
        return monthLeader;
    }

    public void setMonthLeader(int monthLeader) {
        this.monthLeader = monthLeader;
    }

    public int getYearLeader() {
        return yearLeader;
    }

    public void setYearLeader(int yearLeader) {
        this.yearLeader = yearLeader;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public int getIdDetailShift() {
        return idDetailShift;
    }

    public void setIdDetailShift(int idDetailShift) {
        this.idDetailShift = idDetailShift;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }
}
