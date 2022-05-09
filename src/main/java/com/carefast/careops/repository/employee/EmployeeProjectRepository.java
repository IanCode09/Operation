package com.carefast.careops.repository.employee;

import com.carefast.careops.model.employee.CustomEmployeeSchedule;
import com.carefast.careops.model.project.CustomEmployeeDetailListProjectModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.model.project.ProjectLocationModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeDetailListProjectModel, Integer> {

    @Query(value = "SELECT * FROM tab_employee_detail_list_project" +
            " WHERE id_employee = :idEmployee" +
            " GROUP BY id_project", nativeQuery = true)
    List<EmployeeDetailListProjectModel> getAllProjects(int idEmployee);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idEmployee = :idEmployee " +
            "AND e.idProject = :idProject " +
            "AND e.date = :currentDate " +
            "AND e.status = 'Y' ")
    Optional<EmployeeDetailListProjectModel> getEmployeeProject(int idEmployee, String idProject, LocalDate currentDate);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idPlotting = :plottingId " +
            "AND e.date = :currentDate " +
            "AND e.status = 'Y'")
    Optional<EmployeeDetailListProjectModel> getEmployeeByPlottingId(int plottingId, LocalDate currentDate);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idShift = :shiftId " +
            "AND e.idEmployee = :employeeId " +
            "AND e.jobCode = 'CS-LEADER' " +
            "AND e.date = :date " +
            "AND e.statusAttendance = 'SEDANG_BEKERJA' " +
            "AND e.status = 'Y'")
    Optional<EmployeeDetailListProjectModel> getEmployeeNonOperatorSudahAbsen(int employeeId, int shiftId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idProject = :projectCode " +
            "AND e.idShift = :shiftId " +
            "AND e.jobCode = 'CS-SUPERVISOR' " +
            "AND e.date = :date " +
            "AND e.statusAttendance = 'BELUM_ABSEN' " +
            "AND e.status = 'Y'")
    List<EmployeeDetailListProjectModel> getEmployeeSPVBelumAbsen(String projectCode, int shiftId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idShift = :shiftId " +
            "AND e.idEmployee = :employeeId " +
            "AND e.jobCode = 'CS-LEADER' " +
            "AND e.date = :date " +
            "AND e.statusAttendance = 'BELUM_ABSEN' " +
            "AND e.status = 'Y'")
    Optional<EmployeeDetailListProjectModel> getEmployeeNonOperatorBelumAbsen(int employeeId, int shiftId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idPlotting = :plottingId " +
            "AND e.date = :date " +
            "AND e.statusAttendance = 'BELUM_ABSEN' " +
            "AND e.status = 'Y'")
    List<EmployeeDetailListProjectModel> getEmployeeBelumAbsen(int plottingId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idPlotting = :plottingId " +
            "AND e.date = :date " +
            "AND e.statusAttendance = 'SEDANG_BEKERJA' " +
            "AND e.status = 'Y'")
    List<EmployeeDetailListProjectModel> getEmployeeSudahAbsen(int plottingId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idProject = :projectCode " +
            "AND e.idShift = :shiftId " +
            "AND e.jobCode = 'CS-SUPERVISOR' " +
            "AND e.date = :date " +
            "AND e.statusAttendance = 'SEDANG_BEKERJA' " +
            "AND e.status = 'Y'")
    List<EmployeeDetailListProjectModel> getEmployeeSPVSudahAbsen(String projectCode, int shiftId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idPlotting = :plottingId " +
            "AND e.date = :currentDate " +
            "AND e.idShift = :shiftId " +
            "AND e.status = 'Y'")
    List<EmployeeDetailListProjectModel> getAllEmployeeByPlottingId(int plottingId, int shiftId, LocalDate currentDate);

    @Query("SELECT COUNT(e) FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idPlotting in (:plottingId) " +
            "AND e.date = :currentDate " +
            "AND e.isOff = 'N' " +
            "AND e.statusAttendance != 'IZIN' " +
            "AND e.status = 'Y'")
    int countEmployeeByPlotting(List<Integer> plottingId, LocalDate currentDate);

    @Query("SELECT COUNT(e) FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idEmployee in (:leaderId) " +
            "AND e.date = :currentDate " +
            "AND e.isOff = 'N' " +
            "AND e.statusAttendance != 'IZIN' " +
            "AND e.status = 'Y'")
    int countLeaderByDate(List<Integer> leaderId, LocalDate currentDate);

    @Query("SELECT COUNT(e) FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idProject = :projectCode " +
            "AND e.idShift = :shiftId " +
            "AND e.jobCode = 'CS-SUPERVISOR' " +
            "AND e.date = :currentDate " +
            "AND e.isOff = 'N' " +
            "AND e.statusAttendance != 'IZIN' " +
            "AND e.status = 'Y'")
    int countSPVByDate(String projectCode, int shiftId, LocalDate currentDate);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idEmployee = :idEmployee " +
            "AND e.idProject = :idProject " +
            "AND e.date = :date " +
            "AND e.status = 'Y' ")
    List<EmployeeDetailListProjectModel> getScheduleByDate(int idEmployee, String idProject, LocalDate date);

    @Query(value = "SELECT * FROM tab_employee_detail_list_project AS e " +
            "WHERE e.id_employee = :idEmployee " +
            "AND (e.date = :date - INTERVAL 1 DAY OR e.date = :date) " +
            "AND (e.status_attendance = 'SEDANG_BEKERJA' OR  e.status_attendance = 'BELUM_ABSEN' )" +
            "AND e.id_project = :idProject " +
            "AND e.status = 'Y' ", nativeQuery = true)
    List<EmployeeDetailListProjectModel> getScheduleByDateIncludeShiftMalam(int idEmployee, String idProject, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idProject = :projectId " +
            "AND e.date = :date " +
            "AND e.status = 'Y' " +
            "AND e.statusAttendance = 'LIBUR' ")
    List<EmployeeDetailListProjectModel> getEmployeeReplace(String projectId, LocalDate date);

    @Query(value = "SELECT te.id_detail_employee_project AS idDetailEmployeeProject, " +
            "te.id_project AS idProject, te.id_plotting AS idPlotting, " +
            "te.id_employee AS idEmployee, te.id_shift AS idShift, " +
            "te.date AS date, te.schedule_type AS scheduleType, te.status AS status, " +
            "e.employee_code AS employeeCode, e.employee_name AS employeeName, " +
            "e.job_code AS jobCode, e.job_name AS jobName, e.project_code AS projectCode " +
            "FROM tab_employee_detail_list_project AS te " +
            "INNER JOIN tab_employee_insys AS e ON te.id_employee = e.id_employee " +
            "WHERE te.id_detail_employee_project = :scheduleId ", nativeQuery = true)
    Optional < CustomEmployeeSchedule> getDetailEmployeeSchedule(int scheduleId);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idEmployee = :employeeId " +
            "AND e.idProject = :projectId " +
            "AND e.idPlotting = :plottingId " +
            "AND e.idShift = :shiftId " +
            "AND e.date = :date " +
            "AND e.status = 'Y' ")
    Optional<EmployeeDetailListProjectModel> getScheduleByPlottingShiftDate(int employeeId, String projectId, int plottingId, int shiftId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idEmployee = :employeeId " +
            "AND e.idProject = :projectId " +
            "AND e.date = :date " +
            "AND e.scheduleType = 'ACTUAL SCHEDULE'" +
            "AND e.status = 'Y' ")
    Optional<EmployeeDetailListProjectModel> getActualScheduleByDate(int employeeId, String projectId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idProject = :projectId " +
            "AND e.date = :date " +
            "AND e.scheduleType = 'ACTUAL SCHEDULE' " +
            "AND e.status = 'Y' ")
    List<EmployeeDetailListProjectModel> getAllEmployeeActualSchedule(String projectId, LocalDate date);

    @Query(value = "SELECT te.id_detail_employee_project AS idDetailEmployeeProject, " +
            "te.id_employee AS idEmployee, tei.employee_name AS employeeName, " +
            "te.id_project AS idProject, te.schedule_type AS scheduleType, " +
            "te.id_plotting AS idPlotting, tsa.code_plotting_area as codePlottingArea, " +
            "tl.location_id AS locationId, tl.location_name AS locationName, " +
            "tsl.sub_location_id AS subLocationId, tsl.sub_location_name AS subLocationName, " +
            "te.date AS date, te.id_shift AS idShift, te.is_done AS isDone, te.done_at AS doneAt, " +
            "ts.shift_description AS shiftDescription, " +
            "td.start_at AS startAt, td.end_at AS endAt " +
            "FROM tab_employee_detail_list_project AS te " +
            "INNER JOIN tab_employee_insys AS tei ON te.id_employee = tei.id_employee " +
            "LEFT JOIN tab_sub_location_area AS tsa ON te.id_plotting = tsa.id_sub_location_area " +
            "LEFT JOIN tab_location AS tl ON tsa.id_location = tl.location_id " +
            "LEFT JOIN tab_sub_location AS tsl ON tsa.sub_location_id = tsl.sub_location_id " +
            "LEFT JOIN tab_shift AS ts ON te.id_shift = ts.shift_id " +
            "RIGHT JOIN tab_detail_shift_project AS td ON te.id_shift = td.id_shift " +
            "WHERE te.id_project = :projectId " +
            "AND td.id_project = :projectId " +
            "AND te.id_employee = :employeeId " +
            "AND te.date = :date " +
            "AND te.status = 'Y' " +
            "ORDER BY td.start_at ", nativeQuery = true)
    List<CustomEmployeeDetailListProjectModel> getEmployeeSchedule(int employeeId, String projectId, LocalDate date);

    @Query(value = "SELECT te.id_detail_employee_project AS idDetailEmployeeProject, " +
            "te.id_employee AS idEmployee, tei.employee_name AS employeeName, " +
            "te.id_project AS idProject, te.schedule_type AS scheduleType, " +
            "te.id_plotting AS idPlotting, tsa.code_plotting_area as codePlottingArea, " +
            "tl.location_id AS locationId, tl.location_name AS locationName, " +
            "tsl.sub_location_id AS subLocationId, tsl.sub_location_name AS subLocationName, " +
            "te.date AS date, te.id_shift AS idShift, te.is_done AS isDone, te.done_at AS doneAt, " +
            "ts.shift_description AS shiftDescription, " +
            "td.start_at AS startAt, td.end_at AS endAt " +
            "FROM tab_employee_detail_list_project AS te " +
            "INNER JOIN tab_employee_insys AS tei ON te.id_employee = tei.id_employee " +
            "LEFT JOIN tab_sub_location_area AS tsa ON te.id_plotting = tsa.id_sub_location_area " +
            "LEFT JOIN tab_location AS tl ON tsa.id_location = tl.location_id " +
            "LEFT JOIN tab_sub_location AS tsl ON tsa.sub_location_id = tsl.sub_location_id " +
            "LEFT JOIN tab_shift AS ts ON te.id_shift = ts.shift_id " +
            "RIGHT JOIN tab_detail_shift_project AS td ON te.id_shift = td.id_shift " +
            "WHERE te.id_employee = :employeeId " +
            "AND td.id_project = te.id_project " +
            "AND te.date >= :date " +
            "AND te.status = 'Y' " +
            "ORDER BY te.date ASC, td.start_at", nativeQuery = true)
    Slice<CustomEmployeeDetailListProjectModel> getEmployeeCalendar(int employeeId, LocalDate date, Pageable pageable);

    @Query(value = "SELECT te.id_detail_employee_project AS idDetailEmployeeProject, " +
            "te.id_employee AS idEmployee, tei.employee_name AS employeeName, " +
            "te.id_project AS idProject, te.schedule_type AS scheduleType, " +
            "te.id_plotting AS idPlotting, tsa.code_plotting_area as codePlottingArea, " +
            "tl.location_id AS locationId, tl.location_name AS locationName, " +
            "tsl.sub_location_id AS subLocationId, tsl.sub_location_name AS subLocationName, " +
            "te.date AS date, te.id_shift AS idShift, te.is_done AS isDone, te.done_at AS doneAt, " +
            "ts.shift_description AS shiftDescription, " +
            "td.start_at AS startAt, td.end_at AS endAt " +
            "FROM tab_employee_detail_list_project AS te " +
            "INNER JOIN tab_employee_insys AS tei ON te.id_employee = tei.id_employee " +
            "LEFT JOIN tab_sub_location_area AS tsa ON te.id_plotting = tsa.id_sub_location_area " +
            "LEFT JOIN tab_location AS tl ON tsa.id_location = tl.location_id " +
            "LEFT JOIN tab_sub_location AS tsl ON tsa.sub_location_id = tsl.sub_location_id " +
            "LEFT JOIN tab_shift AS ts ON te.id_shift = ts.shift_id " +
            "RIGHT JOIN tab_detail_shift_project AS td ON te.id_shift = td.id_shift " +
            "WHERE te.id_project = :projectId " +
            "AND td.id_project = :projectId " +
            "AND te.is_done = 'Y' " +
            "ORDER BY te.done_at DESC", nativeQuery = true)
    Slice<CustomEmployeeDetailListProjectModel> getEmployeeScheduleIsDone(String projectId, Pageable pageable);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idEmployee = :employeeId " +
            "AND e.idProject = :projectId " +
            "AND e.date = :date " +
            "AND e.scheduleType = 'ACTUAL SCHEDULE' " +
            "AND e.statusAttendance = 'LIBUR' " +
            "AND e.status = 'Y'")
    Optional<EmployeeDetailListProjectModel> getEmployeeScheduleLibur(int employeeId, String projectId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idEmployee = :employeeId " +
            "AND e.idProject = :projectId " +
            "AND e.idShift = :shiftId " +
            "AND e.jobCode = 'CS-LEADER' " +
            "AND e.date = :date " +
            "AND e.status = 'Y'")
    List<EmployeeDetailListProjectModel> getLeaderByShift(int employeeId, String projectId, int shiftId, LocalDate date);

    @Query("SELECT e FROM EmployeeDetailListProjectModel e " +
            "WHERE e.idEmployee = :employeeId " +
            "AND e.idProject = :projectId " +
            "AND e.jobCode = 'CS-SUPERVISOR' " +
            "AND e.date = :date " +
            "AND e.status = 'Y'")
    List<EmployeeDetailListProjectModel> getSupervisorSchedule(int employeeId, String projectId, LocalDate date);
}
