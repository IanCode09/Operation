package com.carefast.careops.dto.attendance;

import com.carefast.careops.dto.employee.response.EmployeeProfileDTO;

import java.util.List;

public class EmployeeAttendanceBelumAbsenDTO {
    private int employeeId;
    private String projectCode;
    private int countEmployee;
    private int countEmployeeBelumAbsen;
    private List<EmployeeProfileDTO> employeeBelumAbsen;

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

    public int getCountEmployee() {
        return countEmployee;
    }

    public void setCountEmployee(int countEmployee) {
        this.countEmployee = countEmployee;
    }

    public int getCountEmployeeBelumAbsen() {
        return countEmployeeBelumAbsen;
    }

    public void setCountEmployeeBelumAbsen(int countEmployeeBelumAbsen) {
        this.countEmployeeBelumAbsen = countEmployeeBelumAbsen;
    }

    public List<EmployeeProfileDTO> getEmployeeBelumAbsen() {
        return employeeBelumAbsen;
    }

    public void setEmployeeBelumAbsen(List<EmployeeProfileDTO> employeeBelumAbsen) {
        this.employeeBelumAbsen = employeeBelumAbsen;
    }
}
