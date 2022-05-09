package com.carefast.careops.dto.activity;

import java.util.List;

public class DACEmployeePlottingDTO {
    private int plottingId;
    private int employeePengawasId;
    private String employeePengawasName;
    private String codePlottingArea;
    private int locationId;
    private String locationName;
    private int subLocationId;
    private String subLocationName;
    private int shiftId;
    private String shiftDescription;
    private String isDone;
    private String checklistByEmployee;
    Integer countDailyActivities;
    Integer countChecklistDACByEmployee;
    private List<DailyActivitiesDTO> dailyActivities;

    public DACEmployeePlottingDTO() {
    }

    public DACEmployeePlottingDTO(int plottingId, int employeePengawasId, String employeePengawasName, String codePlottingArea, int locationId, String locationName, int subLocationId, String subLocationName, int shiftId, String shiftDescription, String isDone, String checklistByEmployee, Integer countDailyActivities, Integer countChecklistDACByEmployee, List<DailyActivitiesDTO> dailyActivities) {
        this.plottingId = plottingId;
        this.employeePengawasId = employeePengawasId;
        this.employeePengawasName = employeePengawasName;
        this.codePlottingArea = codePlottingArea;
        this.locationId = locationId;
        this.locationName = locationName;
        this.subLocationId = subLocationId;
        this.subLocationName = subLocationName;
        this.shiftId = shiftId;
        this.shiftDescription = shiftDescription;
        this.isDone = isDone;
        this.checklistByEmployee = checklistByEmployee;
        this.countDailyActivities = countDailyActivities;
        this.countChecklistDACByEmployee = countChecklistDACByEmployee;
        this.dailyActivities = dailyActivities;
    }

    public int getPlottingId() {
        return plottingId;
    }

    public void setPlottingId(int plottingId) {
        this.plottingId = plottingId;
    }

    public int getEmployeePengawasId() {
        return employeePengawasId;
    }

    public void setEmployeePengawasId(int employeePengawasId) {
        this.employeePengawasId = employeePengawasId;
    }

    public String getEmployeePengawasName() {
        return employeePengawasName;
    }

    public void setEmployeePengawasName(String employeePengawasName) {
        this.employeePengawasName = employeePengawasName;
    }

    public String getCodePlottingArea() {
        return codePlottingArea;
    }

    public void setCodePlottingArea(String codePlottingArea) {
        this.codePlottingArea = codePlottingArea;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    public String getChecklistByEmployee() {
        return checklistByEmployee;
    }

    public void setChecklistByEmployee(String checklistByEmployee) {
        this.checklistByEmployee = checklistByEmployee;
    }

    public Integer getCountDailyActivities() {
        return countDailyActivities;
    }

    public void setCountDailyActivities(Integer countDailyActivities) {
        this.countDailyActivities = countDailyActivities;
    }

    public Integer getCountChecklistDACByEmployee() {
        return countChecklistDACByEmployee;
    }

    public void setCountChecklistDACByEmployee(Integer countChecklistDACByEmployee) {
        this.countChecklistDACByEmployee = countChecklistDACByEmployee;
    }

    public List<DailyActivitiesDTO> getDailyActivities() {
        return dailyActivities;
    }

    public void setDailyActivities(List<DailyActivitiesDTO> dailyActivities) {
        this.dailyActivities = dailyActivities;
    }
}
