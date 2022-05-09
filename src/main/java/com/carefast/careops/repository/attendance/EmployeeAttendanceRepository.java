package com.carefast.careops.repository.attendance;

import com.carefast.careops.model.employee.EmployeeAttendanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendanceModel, Integer> {
    @Query(value = "SELECT * FROM tab_employee_attendance " +
            "WHERE employee_id = :employeeId " +
            "AND project_code = :projectCode " +
            "AND barcode_key = :barcodeKey", nativeQuery = true)
    Optional<EmployeeAttendanceModel> checkAttendance(int employeeId, String projectCode, String barcodeKey);

    @Query(value = "SELECT * FROM tab_employee_attendance " +
            "WHERE employee_id = :employeeId " +
            "AND project_code = :projectCode " +
            "AND created_at = :currentDate", nativeQuery = true)
    Optional<EmployeeAttendanceModel> getAttendanceInfo(int employeeId, String projectCode, LocalDate currentDate);

    @Query(value = "SELECT * FROM tab_employee_attendance " +
            "WHERE employee_id = :employeeId " +
            "AND project_code = :projectCode ", nativeQuery = true)
    List<EmployeeAttendanceModel> findAll(int employeeId, String projectCode);

    @Query(value = "SELECT * FROM tab_employee_attendance " +
            "WHERE employee_id = :employeeId " +
            "AND project_code = :projectCode " +
            "AND created_at BETWEEN (:datePrefix) AND (:dateSuffix)", nativeQuery = true)
    List<EmployeeAttendanceModel> getAttendanceByDate(int employeeId, String projectCode, LocalDate datePrefix, LocalDate dateSuffix);

    @Query(value = "SELECT * FROM tab_employee_attendance " +
            "WHERE employee_id = :employeeId " +
            "AND project_code = :projectCode " +
            "AND id_detail_employee_project = :scheduleId ", nativeQuery = true)
    Optional<EmployeeAttendanceModel> getEmployeeAttendance(int employeeId, String projectCode, int scheduleId);

    @Query("SELECT e FROM EmployeeAttendanceModel e " +
            "WHERE e.employeeId = :employeeId " +
            "AND e.projectCode = :projectCode " +
            "AND e.scanIn IS NOT NULL " +
            "AND e.scanOut IS NULL " +
            "AND e.createdAt <= :date")
    Optional<EmployeeAttendanceModel> getLastAttendanceIn(int employeeId, String projectCode, LocalDate date);

    @Query("SELECT e FROM EmployeeAttendanceModel e " +
            "WHERE e.employeeId = :employeeId " +
            "AND e.projectCode = :projectCode " +
            "AND e.idDetailEmployeeProject = :scheduleId " +
            "AND e.scanIn != null " +
            "AND e.scanOut != null ")
    Optional<EmployeeAttendanceModel> getStatusAttendanceSchedule(int employeeId, String projectCode, int scheduleId);

    @Query(value = "SELECT * FROM tab_employee_attendance AS ta " +
            "INNER JOIN tab_employee_insys AS te " +
            "ON ta.employee_id = te.id_employee " +
            "AND ta.project_code = :projectCode " +
            "AND te.job_code = 'CLEANER' " +
            "AND DATE(ta.scan_in) = :date ", nativeQuery = true)
    List<EmployeeAttendanceModel> getEmployeeAttendanceStatusIn(String projectCode, LocalDate date);

    @Query("SELECT e FROM EmployeeAttendanceModel e " +
            "WHERE e.employeeId = :employeeId " +
            "AND e.projectCode = :projectCode " +
            "AND e.idScheduleLeader = :scheduleId " +
            "AND e.scanIn != null " +
            "AND e.createdAt = :date")
    Optional<EmployeeAttendanceModel> getEmployeeNonOperatorStatusAttendanceSchedule(int employeeId, String projectCode, int scheduleId, LocalDate date);

    @Query("SELECT e FROM EmployeeAttendanceModel e " +
            "WHERE e.idDetailEmployeeProject = :scheduleId ")
    Optional<EmployeeAttendanceModel> getAttendanceInfoByScheduleId(int scheduleId);

    @Query(value = "SELECT * FROM tab_employee_attendance e " +
            "WHERE e.employee_id = :employeeId " +
            "AND e.project_code = :projectCode " +
            "ORDER BY e.attendance_id  DESC LIMIT 1", nativeQuery = true)
    Optional<EmployeeAttendanceModel> getAttendanceInfo(int employeeId, String projectCode);
}
