package com.carefast.careops.dto.checklist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class ChecklistDTO {
    private int checklistId;
    private int submitBy;
    private int employeeId;
    private String projectId;
    private int plottingId;
    private int activityId;
    private String objectId;
    private String objectIdValue;
    private String objectIdSecond;
    private String objectIdSecondValue;
    private String objectIdThird;
    private String objectIdThirdValue;
    private String objectIdFour;
    private String objectIdFourValue;
    private String objectIdFive;
    private String objectIdFiveValue;
    private String image;
    private String note;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    public int getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(int checklistId) {
        this.checklistId = checklistId;
    }

    public int getSubmitBy() {
        return submitBy;
    }

    public void setSubmitBy(int submitBy) {
        this.submitBy = submitBy;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getPlottingId() {
        return plottingId;
    }

    public void setPlottingId(int plottingId) {
        this.plottingId = plottingId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectIdValue() {
        return objectIdValue;
    }

    public void setObjectIdValue(String objectIdValue) {
        this.objectIdValue = objectIdValue;
    }

    public String getObjectIdSecond() {
        return objectIdSecond;
    }

    public void setObjectIdSecond(String objectIdSecond) {
        this.objectIdSecond = objectIdSecond;
    }

    public String getObjectIdSecondValue() {
        return objectIdSecondValue;
    }

    public void setObjectIdSecondValue(String objectIdSecondValue) {
        this.objectIdSecondValue = objectIdSecondValue;
    }

    public String getObjectIdThird() {
        return objectIdThird;
    }

    public void setObjectIdThird(String objectIdThird) {
        this.objectIdThird = objectIdThird;
    }

    public String getObjectIdThirdValue() {
        return objectIdThirdValue;
    }

    public void setObjectIdThirdValue(String objectIdThirdValue) {
        this.objectIdThirdValue = objectIdThirdValue;
    }

    public String getObjectIdFour() {
        return objectIdFour;
    }

    public void setObjectIdFour(String objectIdFour) {
        this.objectIdFour = objectIdFour;
    }

    public String getObjectIdFourValue() {
        return objectIdFourValue;
    }

    public void setObjectIdFourValue(String objectIdFourValue) {
        this.objectIdFourValue = objectIdFourValue;
    }

    public String getObjectIdFive() {
        return objectIdFive;
    }

    public void setObjectIdFive(String objectIdFive) {
        this.objectIdFive = objectIdFive;
    }

    public String getObjectIdFiveValue() {
        return objectIdFiveValue;
    }

    public void setObjectIdFiveValue(String objectIdFiveValue) {
        this.objectIdFiveValue = objectIdFiveValue;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
