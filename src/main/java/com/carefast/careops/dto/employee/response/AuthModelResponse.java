package com.carefast.careops.dto.employee.response;

public class AuthModelResponse {

    private int idEmployeeProject;
    private String employeeNuc;
    private String employeeName;
    private String email;
    private String token;
    private String refreshToken;

    public int getIdEmployeeProject() {
        return idEmployeeProject;
    }

    public void setIdEmployeeProject(int idEmployeeProject) {
        this.idEmployeeProject = idEmployeeProject;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
