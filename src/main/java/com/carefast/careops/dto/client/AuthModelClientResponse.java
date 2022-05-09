package com.carefast.careops.dto.client;

public class AuthModelClientResponse {

    private int clientId;
    private String clientName;
    private String projectCode;
    private String email;
    private String status;
    private String token;
    private String refreshToken;

    public AuthModelClientResponse() {
    }

    public AuthModelClientResponse(int clientId, String clientName, String projectCode, String email, String status, String token, String refreshToken) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.projectCode = projectCode;
        this.email = email;
        this.status = status;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
