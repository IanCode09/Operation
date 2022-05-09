package com.carefast.careops.dto.attendance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalTime;

public class AttendanceNonOperatorStatusDTO {
    private int employeeId;
    private String projectCode;
    private int idScheduleLeader;
    private int shiftId;
    private String statusAttendance;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime startAt;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime endAt;

    public AttendanceNonOperatorStatusDTO() {
    }

    public AttendanceNonOperatorStatusDTO(int employeeId, String projectCode, int idScheduleLeader, int shiftId, String statusAttendance, LocalTime startAt, LocalTime endAt) {
        this.employeeId = employeeId;
        this.projectCode = projectCode;
        this.idScheduleLeader = idScheduleLeader;
        this.shiftId = shiftId;
        this.statusAttendance = statusAttendance;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getIdScheduleLeader() {
        return idScheduleLeader;
    }

    public void setIdScheduleLeader(int idScheduleLeader) {
        this.idScheduleLeader = idScheduleLeader;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getStatusAttendance() {
        return statusAttendance;
    }

    public void setStatusAttendance(String statusAttendance) {
        this.statusAttendance = statusAttendance;
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
}
