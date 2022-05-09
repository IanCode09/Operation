package com.carefast.careops.model.overtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CustomOvertimeGantiModel {
    int getOvertimeId();
    int getEmployeeId();
    String getEmployeeCode();
    String getEmployeeName();
    String getEmployeePhotoProfile();
    int getEmployeeReplaceId();
    String getEmployeeReplaceCode();
    String getEmployeeReplaceName();
    String getEmployeeReplacePhotoProfile();
    int getCreatedByEmployeeId();
    String getEmployeeCodeCreated();
    String getEmployeeNameCreated();
    String getEmployeePhotoProfileCreated();
    int getIdDetailEmployeeProject();
    String getProjectId();
    String getTitle();
    String getDescription();
    int getPlottingId();
    String getShiftDescription();
    LocalDate getAtDate();
    LocalTime getStartAt();
    LocalTime getEndAt();
    String getLocationName();
    String getSubLocationName();
    String getImage();
    String getStatus();
    int getPermissionId();
    String getIsPermission();
    LocalDateTime getCreatedAt();
}
