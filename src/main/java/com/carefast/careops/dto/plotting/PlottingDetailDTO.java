package com.carefast.careops.dto.plotting;

public class PlottingDetailDTO {
    private int idSubLocationArea;
    private String locationName;
    private String idProject;
    private String subLocationName;
    private int idPosition;
    private String codePlottingArea;
    private String barcodePlottingArea;
    private int shiftId;
    private String shiftDescription;

    public int getIdSubLocationArea() {
        return idSubLocationArea;
    }

    public void setIdSubLocationArea(int idSubLocationArea) {
        this.idSubLocationArea = idSubLocationArea;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
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

    public String getShiftDescription() {
        return shiftDescription;
    }

    public void setShiftDescription(String shiftDescription) {
        this.shiftDescription = shiftDescription;
    }
}
