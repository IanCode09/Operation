package com.carefast.careops.dto.complaint;

import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CustomComplaintDTO {
    private int complaintId;
    private Integer clientId;
    private String clientName;
    private Integer createdByEmployeeId;
    private String createdByEmployeeName;
    private String projectId;
    private String title;
    private String description;
    private String image;
    private int locationId;
    private String locationName;
    private int subLocationId;
    private String subLocationName;
    private Integer processBy;
    private Integer workerId;
    private ProfileDTO worker;
    private String beforeImage;
    private String processImage;
    private String afterImage;
    private String statusComplaint;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    @JsonSerialize(using= LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "EEEE, dd MMMM yyyy", shape = JsonFormat.Shape.STRING, locale = "id")
    private LocalDate date;
    @JsonSerialize(using= LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.STRING, locale = "id")
    private LocalTime time;


    public CustomComplaintDTO() {
    }

    public CustomComplaintDTO(int complaintId, Integer clientId, String clientName, Integer createdByEmployeeId, String createdByEmployeeName, String projectId, String title, String description, String image, int locationId, String locationName, int subLocationId, String subLocationName, Integer processBy, Integer workerId, ProfileDTO worker, String beforeImage, String processImage, String afterImage, String statusComplaint, LocalDateTime createdAt, LocalDate date, LocalTime time) {
        this.complaintId = complaintId;
        this.clientId = clientId;
        this.clientName = clientName;
        this.createdByEmployeeId = createdByEmployeeId;
        this.createdByEmployeeName = createdByEmployeeName;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.image = image;
        this.locationId = locationId;
        this.locationName = locationName;
        this.subLocationId = subLocationId;
        this.subLocationName = subLocationName;
        this.processBy = processBy;
        this.workerId = workerId;
        this.worker = worker;
        this.beforeImage = beforeImage;
        this.processImage = processImage;
        this.afterImage = afterImage;
        this.statusComplaint = statusComplaint;
        this.createdAt = createdAt;
        this.date = date;
        this.time = time;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(Integer createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }

    public String getCreatedByEmployeeName() {
        return createdByEmployeeName;
    }

    public void setCreatedByEmployeeName(String createdByEmployeeName) {
        this.createdByEmployeeName = createdByEmployeeName;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
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

    public ProfileDTO getWorker() {
        return worker;
    }

    public void setWorker(ProfileDTO worker) {
        this.worker = worker;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
