package com.carefast.careops.model.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "tab_sub_location_object")
public class ProjectSubLocationObjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_location_object_id")
    private int subLocationObjectId;
    @Column(name = "object_id")
    private int objectId;
    @Column(name = "sub_location_id")
    private int subLocationId;
    @Column(name = "location_id")
    private int locationId;
    @Column(name = "project_id")
    private int projectId;

    public int getSubLocationObjectId() {
        return subLocationObjectId;
    }

    public void setSubLocationObjectId(int subLocationObjectId) {
        this.subLocationObjectId = subLocationObjectId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
