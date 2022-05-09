package com.carefast.careops.dto.team;

public class TeamDTO {
    private int employeeId;
    private String projectCode;
    private String employeeNuc;
    private String employeeName;
    private String employee_photo_profile;
    private String jobCode;
    private String jobName;
    private int idShift;
    private String scheduleType;
    private String statusAttendance;

    public TeamDTO() {
    }

    public TeamDTO(int employeeId, String projectCode, String employeeNuc, String employeeName, String employee_photo_profile, String jobCode, String jobName, int idShift, String scheduleType, String statusAttendance) {
        this.employeeId = employeeId;
        this.projectCode = projectCode;
        this.employeeNuc = employeeNuc;
        this.employeeName = employeeName;
        this.employee_photo_profile = employee_photo_profile;
        this.jobCode = jobCode;
        this.jobName = jobName;
        this.idShift = idShift;
        this.scheduleType = scheduleType;
        this.statusAttendance = statusAttendance;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
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

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getIdShift() {
        return idShift;
    }

    public void setIdShift(int idShift) {
        this.idShift = idShift;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getStatusAttendance() {
        return statusAttendance;
    }

    public void setStatusAttendance(String statusAttendance) {
        this.statusAttendance = statusAttendance;
    }
}
