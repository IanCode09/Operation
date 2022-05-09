package com.carefast.careops.dto.permission;

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

public class CustomPermissionDTO {
    private int permissionId;
    private int employeeId;
    private String employeeCode;
    private String employeeName;
    private String employeePhotoProfile;
    private Integer replaceByEmployeeId;
    private String employeeReplaceCode;
    private String employeeReplaceName;
    private String employeeReplacePhotoProfile;
    private int approveByEmployeeId;
    private int idDetailEmployeeProject;
    private String projectId;
    private String statusPermission;
    private String title;
    private String description;
    private int plottingId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "EEEE, dd/MM/yyyy", shape = JsonFormat.Shape.STRING, locale = "id")
    private LocalDate atDate;
    private String shiftDescription;
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    public CustomPermissionDTO() {
    }

    public CustomPermissionDTO(int permissionId, int employeeId, String employeeCode, String employeeName, String employeePhotoProfile, Integer replaceByEmployeeId, String employeeReplaceCode, String employeeReplaceName, String employeeReplacePhotoProfile, int approveByEmployeeId, int idDetailEmployeeProject, String projectId, String statusPermission, String title, String description, int plottingId, LocalDate atDate, String shiftDescription, LocalTime startAt, LocalTime endAt, String locationName, String subLocationName, String image, LocalDateTime createdAt) {
        this.permissionId = permissionId;
        this.employeeId = employeeId;
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.employeePhotoProfile = employeePhotoProfile;
        this.replaceByEmployeeId = replaceByEmployeeId;
        this.employeeReplaceCode = employeeReplaceCode;
        this.employeeReplaceName = employeeReplaceName;
        this.employeeReplacePhotoProfile = employeeReplacePhotoProfile;
        this.approveByEmployeeId = approveByEmployeeId;
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.projectId = projectId;
        this.statusPermission = statusPermission;
        this.title = title;
        this.description = description;
        this.plottingId = plottingId;
        this.atDate = atDate;
        this.shiftDescription = shiftDescription;
        this.startAt = startAt;
        this.endAt = endAt;
        this.locationName = locationName;
        this.subLocationName = subLocationName;
        this.image = image;
        this.createdAt = createdAt;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
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

    public Integer getReplaceByEmployeeId() {
        return replaceByEmployeeId;
    }

    public void setReplaceByEmployeeId(Integer replaceByEmployeeId) {
        this.replaceByEmployeeId = replaceByEmployeeId;
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

    public String getStatusPermission() {
        return statusPermission;
    }

    public void setStatusPermission(String statusPermission) {
        this.statusPermission = statusPermission;
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

    public LocalDate getAtDate() {
        return atDate;
    }

    public void setAtDate(LocalDate atDate) {
        this.atDate = atDate;
    }

    public String getShiftDescription() {
        return shiftDescription;
    }

    public void setShiftDescription(String shiftDescription) {
        this.shiftDescription = shiftDescription;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
