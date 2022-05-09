package com.carefast.careops.dto.attendance;

public class ProfileSudahAbsenDTO {
    private int idEmployee;
    private String employeeCode;
    private String employeeName;
    private String employeePhoneNumber;
    private String employeePhotoProfile;
    private String projectCode;
    private AttendanceDTO attendanceInfo;

    public ProfileSudahAbsenDTO() {
    }

    public ProfileSudahAbsenDTO(int idEmployee, String employeeCode, String employeeName, String employeePhoneNumber, String employeePhotoProfile, String projectCode, AttendanceDTO attendanceInfo) {
        this.idEmployee = idEmployee;
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeePhotoProfile = employeePhotoProfile;
        this.projectCode = projectCode;
        this.attendanceInfo = attendanceInfo;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
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

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeePhotoProfile() {
        return employeePhotoProfile;
    }

    public void setEmployeePhotoProfile(String employeePhotoProfile) {
        this.employeePhotoProfile = employeePhotoProfile;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public AttendanceDTO getAttendanceInfo() {
        return attendanceInfo;
    }

    public void setAttendanceInfo(AttendanceDTO attendanceInfo) {
        this.attendanceInfo = attendanceInfo;
    }
}
