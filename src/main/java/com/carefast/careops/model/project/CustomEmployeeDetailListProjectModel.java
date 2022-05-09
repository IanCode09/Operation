package com.carefast.careops.model.project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CustomEmployeeDetailListProjectModel {
    int getIdDetailEmployeeProject();
    int getIdEmployee();
    String getEmployeeName();
    String getIdProject();
    String getScheduleType();
    Integer getIdPlotting();
    String getcodePlottingArea();
    Integer getLocationId();
    String getLocationName();
    Integer getSubLocationId();
    String getSubLocationName();
    LocalDate getDate();
    int getIdShift();
    String getShiftDescription();
    LocalTime getStartAt();
    LocalTime getEndAt();
    String getIsDone();
    LocalDateTime getDoneAt();
}
