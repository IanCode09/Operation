package com.carefast.careops.dto.team;

public class TeamOperatorDTO {
    private int employeeId;
    private String employeeNuc;
    private String employeeName;
    private String employee_photo_profile;
    private String jobName;
    private String locationName;
    private String subLocationName;
    private String statusAttendance;

    public TeamOperatorDTO() {
    }

    public TeamOperatorDTO(int employeeId, String employeeNuc, String employeeName, String employee_photo_profile, String jobName, String locationName, String subLocationName, String statusAttendance) {
        this.employeeId = employeeId;
        this.employeeNuc = employeeNuc;
        this.employeeName = employeeName;
        this.employee_photo_profile = employee_photo_profile;
        this.jobName = jobName;
        this.locationName = locationName;
        this.subLocationName = subLocationName;
        this.statusAttendance = statusAttendance;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNuc() {
        return employeeNuc;
    }

    public void setEmployeeNuc(String employeeNuc) {
        this.employeeNuc = employeeNuc;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployee_photo_profile() {
        return employee_photo_profile;
    }

    public void setEmployee_photo_profile(String employee_photo_profile) {
        this.employee_photo_profile = employee_photo_profile;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }

    public String getStatusAttendance() {
        return statusAttendance;
    }

    public void setStatusAttendance(String statusAttendance) {
        this.statusAttendance = statusAttendance;
    }
}
