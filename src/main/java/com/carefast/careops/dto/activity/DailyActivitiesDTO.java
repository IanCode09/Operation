package com.carefast.careops.dto.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalTime;

public class DailyActivitiesDTO {
    private int idSubLocationActivity;
    private String objectId;
    private String objectIdSecond;
    private String objectIdThird;
    private String objectIdFour;
    private String objectIdFive;
    private String activity;
    private String shiftDescription;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "id")
    private LocalTime startAt;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "id")
    private LocalTime endAt;
    private String machineName;
    private String toolName;
    private String chemicalName;

    public int getIdSubLocationActivity() {
        return idSubLocationActivity;
    }

    public void setIdSubLocationActivity(int idSubLocationActivity) {
        this.idSubLocationActivity = idSubLocationActivity;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectIdSecond() {
        return objectIdSecond;
    }

    public void setObjectIdSecond(String objectIdSecond) {
        this.objectIdSecond = objectIdSecond;
    }

    public String getObjectIdThird() {
        return objectIdThird;
    }

    public void setObjectIdThird(String objectIdThird) {
        this.objectIdThird = objectIdThird;
    }

    public String getObjectIdFour() {
        return objectIdFour;
    }

    public void setObjectIdFour(String objectIdFour) {
        this.objectIdFour = objectIdFour;
    }

    public String getObjectIdFive() {
        return objectIdFive;
    }

    public void setObjectIdFive(String objectIdFive) {
        this.objectIdFive = objectIdFive;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }
}
