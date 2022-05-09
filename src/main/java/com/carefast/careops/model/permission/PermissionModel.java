package com.carefast.careops.model.permission;

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
@Table(name = "tab_permission")
public class PermissionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id ")
    private int permissionId;
    @Column(name = "request_by_employee_id")
    private int requestByEmployeeId;
    @Column(name = "replace_by_employee_id")
    private Integer replaceByEmployeeId;
    @Column(name = "approve_by_employee_id")
    private int approveByEmployeeId;
    @Column(name = "id_detail_employee_project")
    private int idDetailEmployeeProject;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "title")
    private String title;
    @Type(type = "text")
    @Column(name = "description")
    private String description;
    @Column(name = "plotting_id")
    private int plottingId;
    @Column(name = "shift_id")
    private int shiftId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    @Column(name = "at_date")
    private LocalDate atDate;
    @Type(type = "text")
    @Column(name = "image")
    private String image;
    @Column(name = "status_permission", columnDefinition = "enum('ON_PROGRESS','ACCEPT','REFUSE','PENDING','WAITING')")
    private String statusPermission;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "process_at")
    private LocalDateTime processAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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

    public String getStatusPermission() {
        return statusPermission;
    }

    public void setStatusPermission(String statusPermission) {
        this.statusPermission = statusPermission;
    }

    public LocalDateTime getProcessAt() {
        return processAt;
    }

    public void setProcessAt(LocalDateTime processAt) {
        this.processAt = processAt;
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
