package com.carefast.careops.dto.employee.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalTime;

public class EmployeeBarcodeDTO {
    private int idBarcode;
    private int employeeId;
    private String projectCode;
    private String barcodeKey;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Jakarta", locale = "id")
    private LocalTime checkIn;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Jakarta", locale = "id")
    private LocalTime checkOut;

    public int getIdBarcode() {
        return idBarcode;
    }

    public void setIdBarcode(int idBarcode) {
        this.idBarcode = idBarcode;
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

    public String getBarcodeKey() {
        return barcodeKey;
    }

    public void setBarcodeKey(String barcodeKey) {
        this.barcodeKey = barcodeKey;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public EmployeeBarcodeDTO(int idBarcode, int employeeId, String projectCode, String barcodeKey, LocalTime checkIn, LocalTime checkOut) {
        this.idBarcode = idBarcode;
        this.employeeId = employeeId;
        this.projectCode = projectCode;
        this.barcodeKey = barcodeKey;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
