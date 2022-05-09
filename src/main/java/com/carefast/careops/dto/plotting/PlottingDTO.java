package com.carefast.careops.dto.plotting;

public class PlottingDTO {
    private int idSubLocationArea;
    private int idLocation;
    private String idProject;
    private int subLocationId;
    private int idPosition;
    private String codePlottingArea;
    private String barcodePlottingArea;
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
