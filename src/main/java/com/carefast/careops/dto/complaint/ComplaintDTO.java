package com.carefast.careops.dto.complaint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ComplaintDTO {
    private int complaintId;
    private Integer clientId;
    private Integer createdByEmployeeId;
    private String projectId;
    private String title;
    private String description;
    private String image;
    private int locationId;
    private int subLocationId;
    private Integer processBy;
    private Integer workerId;
    private String beforeImage;
    private String processImage;
    private String afterImage;
    private String statusComplaint;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    public ComplaintDTO() {
    }

    public ComplaintDTO(int complaintId, Integer clientId, Integer createdByEmployeeId, String projectId, String title, String description, String image, int locationId, int subLocationId, Integer processBy, Integer workerId, String beforeImage, String processImage, String afterImage, String statusComplaint, LocalDateTime createdAt) {
        this.complaintId = complaintId;
        this.clientId = clientId;
        this.createdByEmployeeId = createdByEmployeeId;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.image = image;
        this.locationId = locationId;
        this.subLocationId = subLocationId;
        this.processBy = processBy;
        this.workerId = workerId;
        this.beforeImage = beforeImage;
        this.processImage = processImage;
        this.afterImage = afterImage;
        this.statusComplaint = statusComplaint;
        this.createdAt = createdAt;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(Integer createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public Integer getProcessBy() {
        return processBy;
    }

    public void setProcessBy(Integer processBy) {
        this.processBy = processBy;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getBeforeImage() {
        return beforeImage;
    }

    public void setBeforeImage(String beforeImage) {
        this.beforeImage = beforeImage;
    }

    public String getProcessImage() {
        return processImage;
    }

    public void setProcessImage(String processImage) {
        this.processImage = processImage;
    }

    public String getAfterImage() {
        return afterImage;
    }

    public void setAfterImage(String afterImage) {
        this.afterImage = afterImage;
    }

    public String getStatusComplaint() {
        return statusComplaint;
    }

    public void setStatusComplaint(String statusComplaint) {
        this.statusComplaint = statusComplaint;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
