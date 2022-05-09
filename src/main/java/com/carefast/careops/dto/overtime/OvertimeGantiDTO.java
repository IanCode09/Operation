package com.carefast.careops.dto.overtime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OvertimeGantiDTO {
    private int overtimeId;
    private int employeeId;
    private Integer employeeReplaceId;
    private int createdByEmployeeId;
    private int idDetailEmployeeProject;
    private String projectId;
    private String title;
    private String description;
    private int plottingId;
    private int shiftId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "at_date")
    private LocalDate atDate;
    private String image;
    private String status;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime processAt;
    private Integer permissionId;
    private String isPermission;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at")
    private LocalDateTime updatedAt;

    public OvertimeGantiDTO() {
    }

    public OvertimeGantiDTO(int overtimeId, int employeeId, Integer employeeReplaceId, int createdByEmployeeId, int idDetailEmployeeProject, String projectId, String title, String description, int plottingId, int shiftId, LocalDate atDate, String image, String status, LocalDateTime processAt, Integer permissionId, String isPermission, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.overtimeId = overtimeId;
        this.employeeId = employeeId;
        this.employeeReplaceId = employeeReplaceId;
        this.createdByEmployeeId = createdByEmployeeId;
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.plottingId = plottingId;
        this.shiftId = shiftId;
        this.atDate = atDate;
        this.image = image;
        this.status = status;
        this.processAt = processAt;
        this.permissionId = permissionId;
        this.isPermission = isPermission;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getOvertimeId() {
        return overtimeId;
    }

    public void setOvertimeId(int overtimeId) {
        this.overtimeId = overtimeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getEmployeeReplaceId() {
        return employeeReplaceId;
    }

    public void setEmployeeReplaceId(Integer employeeReplaceId) {
        this.employeeReplaceId = employeeReplaceId;
    }

    public int getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(int createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getProcessAt() {
        return processAt;
    }

    public void setProcessAt(LocalDateTime processAt) {
        this.processAt = processAt;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getIsPermission() {
        return isPermission;
    }

    public void setIsPermission(String isPermission) {
        this.isPermission = isPermission;
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
