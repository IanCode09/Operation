package com.carefast.careops.model.permission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CustomPermissionModel {
    int getPermissionId();
    int getEmployeeId();
    String getEmployeeCode();
    String getEmployeeName();
    String getEmployeePhotoProfile();
    Integer getReplaceByEmployeeId();
    int getApproveByEmployeeId();
    int getIdDetailEmployeeProject();
    String getProjectId();
    String getStatusPermission();
    String getTitle();
    String getDescription();
    int getPlottingId();
    LocalDate getAtDate();
    String getShiftDescription();
    LocalTime getStartAt();
    LocalTime getEndAt();
    String getLocationName();
    String getSubLocationName();
    String getImage();
    LocalDateTime getCreatedAt();
}
