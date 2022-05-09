package com.carefast.careops.model.project;

import javax.persistence.*;

@Entity
@Table(name = "tab_sub_location")
public class ProjectSubLocationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_location_id")
    private Integer subLocationId;
    @Column(name = "sub_location_name")
    private String subLocationName;

    public Integer getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(Integer subLocationId) {
        this.subLocationId = subLocationId;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }
}
