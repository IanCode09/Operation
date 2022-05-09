package com.carefast.careops.dto.employee.response;

public class ProfileDTO {

    private int idEmployee;
    private String employeeNuc;
    private String employeeName;
    private String employeePhoneNumber;
    private String employeeEmail;
    private String employeeNik;
    private String employeePhotoProfile;

    public ProfileDTO() {
    }

    public ProfileDTO(int idEmployee, String employeeNuc, String employeeName, String employeePhoneNumber, String employeeEmail, String employeeNik, String employeePhotoProfile) {
        this.idEmployee = idEmployee;
        this.employeeNuc = employeeNuc;
        this.employeeName = employeeName;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeEmail = employeeEmail;
        this.employeeNik = employeeNik;
        this.employeePhotoProfile = employeePhotoProfile;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
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

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeNik() {
        return employeeNik;
    }

    public void setEmployeeNik(String employeeNik) {
        this.employeeNik = employeeNik;
    }

    public String getEmployeePhotoProfile() {
        return employeePhotoProfile;
    }

    public void setEmployeePhotoProfile(String employeePhotoProfile) {
        this.employeePhotoProfile = employeePhotoProfile;
    }
}
