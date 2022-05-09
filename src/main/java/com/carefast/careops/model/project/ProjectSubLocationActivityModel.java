package com.carefast.careops.model.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "tab_sub_location_activity")
public class ProjectSubLocationActivityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_sub_location_activity ")
    private int idSubLocationActivity;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "location_id")
    private int locationId;
    @Column(name = "sub_location_id")
    private int subLocationId;
    @Column(name = "object_id")
    private String objectId;
    @Column(name = "object_id_second")
    private String objectIdSecond;
    @Column(name = "object_id_third")
    private String objectIdThird;
    @Column(name = "object_id_four")
    private String objectIdFour;
    @Column(name = "object_id_five")
    private String objectIdFive;
    @Column(name = "activity_id")
    private String activityId;
    @Column(name = "shift_id")
    private int shitfId;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "start_at")
    private LocalTime startAt;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "end_at")
    private LocalTime endAt;
    @Column(name = "machine_id")
    private String machineId;
    @Column(name = "tools_id")
    private String toolsId;
    @Column(name = "chemical_id")
    private String chemicalId;

    public int getIdSubLocationActivity() {
        return idSubLocationActivity;
    }

    public void setIdSubLocationActivity(int idSubLocationActivity) {
        this.idSubLocationActivity = idSubLocationActivity;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public int getShitfId() {
        return shitfId;
    }

    public void setShitfId(int shitfId) {
        this.shitfId = shitfId;
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

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getToolsId() {
        return toolsId;
    }

    public void setToolsId(String toolsId) {
        this.toolsId = toolsId;
    }

    public String getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(String chemicalId) {
        this.chemicalId = chemicalId;
    }
}
