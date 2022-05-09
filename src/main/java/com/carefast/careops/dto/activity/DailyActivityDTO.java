package com.carefast.careops.dto.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;

public class DailyActivityDTO {
    private String employeePengawasName;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM YYYY", timezone = "Asia/Jakarta", locale = "id")
    private LocalDate date;
    private String shiftDescription;
    private String codePlottingArea;
    private String locationName;
    private String subLocationName;
    List<DailyActivityDetailDTO> dailyActivities;

    public String getEmployeePengawasName() {
        return employeePengawasName;
    }

    public void setEmployeePengawasName(String employeePengawasName) {
        this.employeePengawasName = employeePengawasName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getShiftDescription() {
        return shiftDescription;
    }

    public void setShiftDescription(String shiftDescription) {
        this.shiftDescription = shiftDescription;
    }

    public String getCodePlottingArea() {
        return codePlottingArea;
    }

    public void setCodePlottingArea(String codePlottingArea) {
        this.codePlottingArea = codePlottingArea;
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

    public List<DailyActivityDetailDTO> getDailyActivities() {
        return dailyActivities;
    }

    public void setDailyActivities(List<DailyActivityDetailDTO> dailyActivities) {
        this.dailyActivities = dailyActivities;
    }
}
