package com.carefast.careops.dto.permission;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PermissionDTO {
    private int permissionId;
    private int requestByEmployeeId;
    private Integer replaceByEmployeeId;
    private int approveByEmployeeId;
    private int idDetailEmployeeProject;
    private String projectId;
    private String title;
    private String description;
    private int plottingId;
    private int shiftId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate atDate;
    private String image;
    private String statusPermissionApproval;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime approve_at;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;

    public PermissionDTO() {
    }

    public PermissionDTO(int permissionId, int requestByEmployeeId, Integer replaceByEmployeeId, int approveByEmployeeId, int idDetailEmployeeProject, String projectId, String title, String description, int plottingId, int shiftId, LocalDate atDate, String image, String statusPermissionApproval, LocalDateTime approve_at, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.permissionId = permissionId;
        this.requestByEmployeeId = requestByEmployeeId;
        this.replaceByEmployeeId = replaceByEmployeeId;
        this.approveByEmployeeId = approveByEmployeeId;
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.plottingId = plottingId;
        this.shiftId = shiftId;
        this.atDate = atDate;
        this.image = image;
        this.statusPermissionApproval = statusPermissionApproval;
        this.approve_at = approve_at;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public int getRequestByEmployeeId() {
        return requestByEmployeeId;
    }

    public void setRequestByEmployeeId(int requestByEmployeeId) {
        this.requestByEmployeeId = requestByEmployeeId;
    }

    public Integer getReplaceByEmployeeId() {
        return replaceByEmployeeId;
    }

    public void setReplaceByEmployeeId(Integer replaceByEmployeeId) {
        this.replaceByEmployeeId = replaceByEmployeeId;
    }

    public int getApproveByEmployeeId() {
        return approveByEmployeeId;
    }

    public void setApproveByEmployeeId(int approveByEmployeeId) {
        this.approveByEmployeeId = approveByEmployeeId;
    }

    public int getIdDetailEmployeeProject() {
        return idDetailEmployeeProject;
    }

    public void setIdDetailEmployeeProject(int idDetailEmployeeProject) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
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

    public int getPlottingId() {
        return plottingId;
    }

    public void setPlottingId(int plottingId) {
        this.plottingId = plottingId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public LocalDate getAtDate() {
        return atDate;
    }

    public void setAtDate(LocalDate atDate) {
        this.atDate = atDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatusPermissionApproval() {
        return statusPermissionApproval;
    }

    public void setStatusPermissionApproval(String statusPermissionApproval) {
        this.statusPermissionApproval = statusPermissionApproval;
    }

    public LocalDateTime getApprove_at() {
        return approve_at;
    }

    public void setApprove_at(LocalDateTime approve_at) {
        this.approve_at = approve_at;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
