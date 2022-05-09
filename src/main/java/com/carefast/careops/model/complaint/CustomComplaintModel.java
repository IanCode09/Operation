package com.carefast.careops.model.complaint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CustomComplaintModel {
    int getComplaintId();
    Integer getClientId();
    String getClientName();
    Integer getCreatedByEmployeeId();
    String getCreatedByEmployeeName();
    String getProjectId();
    String getTitle();
    String getDescription();
    String getImage();
    int getLocationId();
    String getLocationName();
    int getSubLocationId();
    String getSubLocationName();
    Integer getProcessBy();
    Integer getWorkerId();
    String getBeforeImage();
    String getProcessImage();
    String getAfterImage();
    String getStatusComplaint();
    LocalDateTime getCreatedAt();
    LocalDate getDate();
    LocalTime getTime();
}
