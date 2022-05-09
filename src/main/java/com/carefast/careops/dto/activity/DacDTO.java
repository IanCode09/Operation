package com.carefast.careops.dto.activity;

public class DacDTO {
    private DACEmployeeProfileDTO employee;
    private DACEmployeePlottingDTO plotting;

    public DACEmployeeProfileDTO getEmployee() {
        return employee;
    }

    public void setEmployee(DACEmployeeProfileDTO employee) {
        this.employee = employee;
    }

    public DACEmployeePlottingDTO getPlotting() {
        return plotting;
    }

    public void setPlotting(DACEmployeePlottingDTO plotting) {
        this.plotting = plotting;
    }
}
