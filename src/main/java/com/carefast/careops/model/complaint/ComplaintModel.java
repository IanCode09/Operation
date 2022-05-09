package com.carefast.careops.model.complaint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_complaint")
public class ComplaintModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="complaint_id ")
    private int complaintId;
    @Column(name = "client_id")
    private Integer clientId;
    @Column(name = "created_by_employee_id")
    private Integer createdByEmployeeId;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "title")
    private String title;
    @Type(type = "text")
    @Column(name = "description")
    private String description;
    @Type(type = "text")
    @Column(name = "image")
    private String image;
    @Column(name = "location_id")
    private int locationId;
    @Column(name = "sub_location_id")
    private int subLocationId;
    @Column(name = "process_by")
    private Integer processBy;
    @Column(name = "worker_id")
    private Integer workerId;
    @Type(type = "text")
    @Column(name = "before_image")
    private String beforeImage;
    @Type(type = "text")
    @Column(name = "process_image")
    private String processImage;
    @Type(type = "text")
    @Column(name = "after_image")
    private String afterImage;
    @Column(name = "status_complaint", columnDefinition = "enum('WAITING','ON PROGRESS','DONE')")
    private String statusComplaint;
    @JsonSerialize(using= LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "date")
    private LocalDate date;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
