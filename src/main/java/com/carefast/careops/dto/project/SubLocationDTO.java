package com.carefast.careops.dto.project;

public class SubLocationDTO {
    private int subLocationId;
    private String subLocationName;

    public SubLocationDTO() {
    }

    public SubLocationDTO(int subLocationId, String subLocationName) {
        this.subLocationId = subLocationId;
        this.subLocationName = subLocationName;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }
}
