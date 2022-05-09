package com.carefast.careops.dto.activity;

public class DACEmployeeProfileDTO {
    private int employeeId;
    private String employeeCode;
    private String employeeName;
    private String jobCode;
    private String jobName;
    private String attendanceImage;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getAttendanceImage() {
        return attendanceImage;
    }

    public void setAttendanceImage(String attendanceImage) {
        this.attendanceImage = attendanceImage;
    }
}
