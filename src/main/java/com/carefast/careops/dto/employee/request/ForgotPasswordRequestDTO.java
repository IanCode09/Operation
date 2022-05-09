package com.carefast.careops.dto.employee.request;

public class ForgotPasswordRequestDTO {

    private String email;
    private String nuc;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNuc() {
        return nuc;
    }

    public void setNuc(String nuc) {
        this.nuc = nuc;
    }
}
