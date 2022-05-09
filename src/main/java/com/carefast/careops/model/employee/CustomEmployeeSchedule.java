package com.carefast.careops.model.employee;

import java.time.LocalDate;

public interface CustomEmployeeSchedule {
    int getIdDetailEmployeeProject();
    String getIdProject();
    int getIdPlotting();
    int getIdEmployee();
    int getIdShift();
    LocalDate getDate();
    String getScheduleType();
    String getStatus();
    String getEmployeeCode();
    String getEmployeeName();
    String getJobCode();
    String getJobName();
    String getProjectCode();
}
