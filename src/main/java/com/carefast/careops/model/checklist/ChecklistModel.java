package com.carefast.careops.model.checklist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_checklist")
public class ChecklistModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="checklist_id")
    private int checklistId;
    @Column(name = "submit_by")
    private int submitBy;
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "plotting_id")
    private int plottingId;
    @Column(name = "activity_id")
    private int activityId;
    @Column(name = "object_id")
    private String objectId;
    @Column(name = "object_id_value")
    private String objectIdValue;
    @Column(name = "object_id_second", nullable = true)
    private String objectIdSecond;
    @Column(name = "object_id_second_value", nullable = true)
    private String objectIdSecondValue;
    @Column(name = "object_id_third", nullable = true)
    private String objectIdThird;
    @Column(name = "object_id_third_value", nullable = true)
    private String objectIdThirdValue;
    @Column(name = "object_id_four", nullable = true)
    private String objectIdFour;
    @Column(name = "object_id_four_value", nullable = true)
    private String objectIdFourValue;
    @Column(name = "object_id_five", nullable = true)
    private String objectIdFive;
    @Column(name = "object_id_five_value", nullable = true)
    private String objectIdFiveValue;
    @Type(type = "text")
    @Column(name = "image")
    private String image;
    @Type(type = "text")
    @Column(name = "note", nullable = true)
    private String note;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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
        this.objectIdSecond = objectIdSecond == "" ? null : objectIdSecond;
    }

    public String getObjectIdSecondValue() {
        return objectIdSecondValue;
    }

    public void setObjectIdSecondValue(String objectIdSecondValue) {
        this.objectIdSecondValue = objectIdSecondValue == "" ? null : objectIdSecondValue;
    }

    public String getObjectIdThird() {
        return objectIdThird;
    }

    public void setObjectIdThird(String objectIdThird) {
        this.objectIdThird = objectIdThird == "" ? null : objectIdThird;
    }

    public String getObjectIdThirdValue() {
        return objectIdThirdValue;
    }

    public void setObjectIdThirdValue(String objectIdThirdValue) {
        this.objectIdThirdValue = objectIdThirdValue == "" ? null : objectIdThirdValue;
    }

    public String getObjectIdFour() {
        return objectIdFour;
    }

    public void setObjectIdFour(String objectIdFour) {
        this.objectIdFour = objectIdFour == "" ? null : objectIdFour;
    }

    public String getObjectIdFourValue() {
        return objectIdFourValue;
    }

    public void setObjectIdFourValue(String objectIdFourValue) {
        this.objectIdFourValue = objectIdFourValue == "" ? null : objectIdFourValue;
    }

    public String getObjectIdFive() {
        return objectIdFive;
    }

    public void setObjectIdFive(String objectIdFive) {
        this.objectIdFive = objectIdFive == "" ? null : objectIdFive;
    }

    public String getObjectIdFiveValue() {
        return objectIdFiveValue;
    }

    public void setObjectIdFiveValue(String objectIdFiveValue) {
        this.objectIdFiveValue = objectIdFiveValue == "" ? null : objectIdFiveValue;
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
        this.note = note == "" ? null : note;
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
