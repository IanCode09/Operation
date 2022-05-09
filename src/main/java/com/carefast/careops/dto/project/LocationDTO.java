package com.carefast.careops.dto.project;

public class LocationDTO {
    private int locationId;
    private String projectId;
    private String locationName;

    public LocationDTO() {
    }

    public LocationDTO(int locationId, String projectId, String locationName) {
        this.locationId = locationId;
        this.projectId = projectId;
        this.locationName = locationName;
    }

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
