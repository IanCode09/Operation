package com.carefast.careops.dto.project;

public class ShiftDTO {
    private int shiftId;
    private String shiftName;
    private String shiftDescription;

    public ShiftDTO() {
    }

    public ShiftDTO(int shiftId, String shiftName, String shiftDescription) {
        this.shiftId = shiftId;
        this.shiftName = shiftName;
        this.shiftDescription = shiftDescription;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getShiftDescription() {
        return shiftDescription;
    }

    public void setShiftDescription(String shiftDescription) {
        this.shiftDescription = shiftDescription;
    }
}
