package com.carefast.careops.model.project;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "tab_location")
public class ProjectLocationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private int locationId;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "location_name")
    private String locationName;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
