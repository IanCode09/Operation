package com.carefast.careops.model.project;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "tab_sub_location_area")
public class ProjectSubLocationAreaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sub_location_area ")
    private int idSubLocationArea;
    @Column(name = "id_location")
    private int idLocation;
    @Column(name = "id_project")
    private String idProject;
    @Column(name = "sub_location_id")
    private int subLocationId;
    @Column(name = "id_position")
    private int idPosition;
    @Type(type = "text")
    @Column(name = "code_plotting_area")
    private String codePlottingArea;
    @Type(type = "text")
    @Column(name = "barcode_plotting_area")
    private String barcodePlottingArea;
    @Column(name = "shift_id")
    private int shiftId;

    public int getIdSubLocationArea() {
        return idSubLocationArea;
    }

    public void setIdSubLocationArea(int idSubLocationArea) {
        this.idSubLocationArea = idSubLocationArea;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public int getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(int idPosition) {
        this.idPosition = idPosition;
    }

    public String getCodePlottingArea() {
        return codePlottingArea;
    }

    public void setCodePlottingArea(String codePlottingArea) {
        this.codePlottingArea = codePlottingArea;
    }

    public String getBarcodePlottingArea() {
        return barcodePlottingArea;
    }

    public void setBarcodePlottingArea(String barcodePlottingArea) {
        this.barcodePlottingArea = barcodePlottingArea;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }
}
