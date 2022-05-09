package com.carefast.careops.model.project;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CustomEmployeeScheduleLeaderModel {
    int getIdDetailEmployeeProject();
    String getIdProject();
    int getIdEmployee();
    String getEmployeeName();
    int getIdShift();
    String getShiftDescription();
    LocalTime getStartAt();
    LocalTime getEndAt();
    LocalDate getDate();
    String getScheduleType();
    String getIsOff();
    String getStatusAttendance();
}
