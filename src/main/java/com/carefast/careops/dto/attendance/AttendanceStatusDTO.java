package com.carefast.careops.dto.attendance;

import java.util.List;

public class AttendanceStatusDTO {
    private String status;
    private String statusAttendanceFirst;
    private String statusAttendanceSecond;
    private String statusAttendanceThird;
    private String statusAttendanceFourth;
    private int countSchedule;
    private int isDone;
    private List<ScheduleDTO> schedule;
    private List<AttendanceDTO> scheduleIsDone;

    public AttendanceStatusDTO() {
    }

    public AttendanceStatusDTO(String status, String statusAttendanceFirst, String statusAttendanceSecond, String statusAttendanceThird, String statusAttendanceFourth, int countSchedule, int isDone, List<ScheduleDTO> schedule, List<AttendanceDTO> scheduleIsDone) {
        this.status = status;
        this.statusAttendanceFirst = statusAttendanceFirst;
        this.statusAttendanceSecond = statusAttendanceSecond;
        this.statusAttendanceThird = statusAttendanceThird;
        this.statusAttendanceFourth = statusAttendanceFourth;
        this.countSchedule = countSchedule;
        this.isDone = isDone;
        this.schedule = schedule;
        this.scheduleIsDone = scheduleIsDone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusAttendanceFirst() {
        return statusAttendanceFirst;
    }

    public void setStatusAttendanceFirst(String statusAttendanceFirst) {
        this.statusAttendanceFirst = statusAttendanceFirst;
    }

    public String getStatusAttendanceSecond() {
        return statusAttendanceSecond;
    }

    public void setStatusAttendanceSecond(String statusAttendanceSecond) {
        this.statusAttendanceSecond = statusAttendanceSecond;
    }

    public String getStatusAttendanceThird() {
        return statusAttendanceThird;
    }

    public void setStatusAttendanceThird(String statusAttendanceThird) {
        this.statusAttendanceThird = statusAttendanceThird;
    }

    public String getStatusAttendanceFourth() {
        return statusAttendanceFourth;
    }

    public void setStatusAttendanceFourth(String statusAttendanceFourth) {
        this.statusAttendanceFourth = statusAttendanceFourth;
    }

    public int getCountSchedule() {
        return countSchedule;
    }

    public void setCountSchedule(int countSchedule) {
        this.countSchedule = countSchedule;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public List<ScheduleDTO> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleDTO> schedule) {
        this.schedule = schedule;
    }

    public List<AttendanceDTO> getScheduleIsDone() {
        return scheduleIsDone;
    }

    public void setScheduleIsDone(List<AttendanceDTO> scheduleIsDone) {
        this.scheduleIsDone = scheduleIsDone;
    }
}
