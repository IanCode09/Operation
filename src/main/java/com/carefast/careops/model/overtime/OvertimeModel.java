package com.carefast.careops.model.overtime;

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
@Table(name = "tab_overtime")
public class OvertimeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "overtime_id  ")
    private int overtimeId;
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "employee_replace_id")
    private Integer employeeReplaceId;
    @Column(name = "created_by_employee_id")
    private int createdByEmployeeId;
    @Column(name = "id_detail_employee_project")
    private int idDetailEmployeeProject;
    @Column(name = "id_detail_employee_project_replace")
    private int idDetailEmployeeProjectReplace;
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
    @Column(name = "status", columnDefinition = "enum('ACCEPT','REFUSE')")
    private String status;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "process_at")
    private LocalDateTime processAt;
    @Column(name = "permission_id")
    private Integer permissionId;
    @Column(name = "is_permission", columnDefinition = "enum('Y','N')")
    private String isPermission;
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

    public int getIdDetailEmployeeProjectReplace() {
        return idDetailEmployeeProjectReplace;
    }

    public void setIdDetailEmployeeProjectReplace(int idDetailEmployeeProjectReplace) {
        this.idDetailEmployeeProjectReplace = idDetailEmployeeProjectReplace;
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
