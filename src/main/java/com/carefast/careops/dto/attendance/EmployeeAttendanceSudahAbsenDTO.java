package com.carefast.careops.dto.attendance;

import java.util.List;

public class EmployeeAttendanceSudahAbsenDTO {
    List<ProfileSudahAbsenDTO> employeeSudahAbsen;

    public EmployeeAttendanceSudahAbsenDTO() {
    }

    public EmployeeAttendanceSudahAbsenDTO(List<ProfileSudahAbsenDTO> employeeSudahAbsen) {
        this.employeeSudahAbsen = employeeSudahAbsen;
    }

    public List<ProfileSudahAbsenDTO> getEmployeeSudahAbsen() {
        return employeeSudahAbsen;
    }

    public void setEmployeeSudahAbsen(List<ProfileSudahAbsenDTO> employeeSudahAbsen) {
        this.employeeSudahAbsen = employeeSudahAbsen;
    }
}
