package com.carefast.careops.dto.checklist;

public class ChecklistDACEmployeeDTO {
    private int checklistDacEmployeeId;
    private int employeeId;
    private String jobCode;
    private int idDetailEmployeeProject;
    private String projectId;
    private int plottingId;
    private int shiftId;
    private int locationId;
    private int subLocationId;
    private int activityId;

    public ChecklistDACEmployeeDTO() {
    }

    public ChecklistDACEmployeeDTO(int checklistDacEmployeeId, int employeeId, String jobCode, int idDetailEmployeeProject, String projectId, int plottingId, int shiftId, int locationId, int subLocationId, int activityId) {
        this.checklistDacEmployeeId = checklistDacEmployeeId;
        this.employeeId = employeeId;
        this.jobCode = jobCode;
        this.idDetailEmployeeProject = idDetailEmployeeProject;
        this.projectId = projectId;
        this.plottingId = plottingId;
        this.shiftId = shiftId;
        this.locationId = locationId;
        this.subLocationId = subLocationId;
        this.activityId = activityId;
    }

    public int getChecklistDacEmployeeId() {
        return checklistDacEmployeeId;
    }

    public void setChecklistDacEmployeeId(int checklistDacEmployeeId) {
        this.checklistDacEmployeeId = checklistDacEmployeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public int getIdDetailEmployeeProject() {
        return idDetailEmployeeProject;
    }

    public void setIdDetailEmployeeProject(int idDetailEmployeeProject) {
        this.idDetailEmployeeProject = idDetailEmployeeProject;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getPlottingId() {
        return plottingId;
    }

    public void setPlottingId(int plottingId) {
        this.plottingId = plottingId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
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

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
