package com.carefast.careops.dto.overtime;

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

public class CustomOvertimeGantiDTO {
    private int overtimeId;
    private int employeeId;
    private String employeeCode;
    private String employeeName;
    private String employeePhotoProfile;
    private int employeeReplaceId;
    private String employeeReplaceCode;
    private String employeeReplaceName;
    private String employeeReplacePhotoProfile;
    private int createdByEmployeeId;
    private String employeeCodeCreated;
    private String employeeNameCreated;
    private String employeePhotoProfileCreated;
    private int idDetailEmployeeProject;
    private String projectId;
    private String title;
    private String description;
    private int plottingId;
    private String shiftDescription;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "EEEE, dd/MM/yyyy", shape = JsonFormat.Shape.STRING, locale = "id")
    private LocalDate atDate;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime startAt;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime endAt;
    private String locationName;
    private String subLocationName;
    private String image;
    private String status;
    private int permissionId;
    private String isPermission;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    public CustomOvertimeGantiDTO() {
    }

    public CustomOvertimeGantiDTO(int overtimeId, int employeeId, String employeeCode, String employeeName, String employeePhotoProfile, int employeeReplaceId, String employeeReplaceCode, String employeeReplaceName, String employeeReplacePhotoProfile, int createdByEmployeeId, String employeeCodeCreated, String employeeNameCreated, String employeePhotoProfileCreated, int idDetailEmployeeProject, String projectId, String title, String description, int plottingId, String shiftDescription, LocalDate atDate, LocalTime startAt, LocalTime endAt, String locationName, String subLocationName, String image, String status, int permissionId, String isPermission, LocalDateTime createdAt) {
        this.overtimeId = overtimeId;
        this.employeeId = employeeId;
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.employeePhotoProfile = employeePhotoProfile;
        this.employeeReplaceId = employeeReplaceId;
        this.employeeReplaceCode = employeeReplaceCode;
        this.employeeReplaceName = employeeReplaceName;
        this.employeeReplacePhotoProfile = employeeReplacePhotoProfile;
        this.createdByEmployeeId = createdByEmployeeId;
        this.employeeCodeCreated = employeeCodeCreated;
        this.employeeNameCreated = employeeNameCreated;
        this.employeePhotoProfileCreated = employeePhotoProfileCreated;
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.plottingId = plottingId;
        this.shiftDescription = shiftDescription;
        this.atDate = atDate;
        this.startAt = startAt;
        this.endAt = endAt;
        this.locationName = locationName;
        this.subLocationName = subLocationName;
        this.image = image;
        this.status = status;
        this.permissionId = permissionId;
        this.isPermission = isPermission;
        this.createdAt = createdAt;
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

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhotoProfile() {
        return employeePhotoProfile;
    }

    public void setEmployeePhotoProfile(String employeePhotoProfile) {
        this.employeePhotoProfile = employeePhotoProfile;
    }

    public int getEmployeeReplaceId() {
        return employeeReplaceId;
    }

    public void setEmployeeReplaceId(int employeeReplaceId) {
        this.employeeReplaceId = employeeReplaceId;
    }

    public String getEmployeeReplaceCode() {
        return employeeReplaceCode;
    }

    public void setEmployeeReplaceCode(String employeeReplaceCode) {
        this.employeeReplaceCode = employeeReplaceCode;
    }

    public String getEmployeeReplaceName() {
        return employeeReplaceName;
    }

    public void setEmployeeReplaceName(String employeeReplaceName) {
        this.employeeReplaceName = employeeReplaceName;
    }

    public String getEmployeeReplacePhotoProfile() {
        return employeeReplacePhotoProfile;
    }

    public void setEmployeeReplacePhotoProfile(String employeeReplacePhotoProfile) {
        this.employeeReplacePhotoProfile = employeeReplacePhotoProfile;
    }

    public int getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(int createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }

    public String getEmployeeCodeCreated() {
        return employeeCodeCreated;
    }

    public void setEmployeeCodeCreated(String employeeCodeCreated) {
        this.employeeCodeCreated = employeeCodeCreated;
    }

    public String getEmployeeNameCreated() {
        return employeeNameCreated;
    }

    public void setEmployeeNameCreated(String employeeNameCreated) {
        this.employeeNameCreated = employeeNameCreated;
    }

    public String getEmployeePhotoProfileCreated() {
        return employeePhotoProfileCreated;
    }

    public void setEmployeePhotoProfileCreated(String employeePhotoProfileCreated) {
        this.employeePhotoProfileCreated = employeePhotoProfileCreated;
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

    public String getShiftDescription() {
        return shiftDescription;
    }

    public void setShiftDescription(String shiftDescription) {
        this.shiftDescription = shiftDescription;
    }

    public LocalDate getAtDate() {
        return atDate;
    }

    public void setAtDate(LocalDate atDate) {
        this.atDate = atDate;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public LocalTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalTime endAt) {
        this.endAt = endAt;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
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

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
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
}
