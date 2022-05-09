package com.carefast.careops.dto.client;

public class ClientDTO {
    private int clientId;
    private String clientName;
    private String projectCode;
    private String email;
    private String status;

    public ClientDTO() {
    }

    public ClientDTO(int clientId, String clientName, String projectCode, String email, String status) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.projectCode = projectCode;
        this.email = email;
        this.status = status;
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
}
