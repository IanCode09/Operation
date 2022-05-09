package com.carefast.careops.repository.project;

import com.carefast.careops.model.project.CustomEmployeeScheduleLeaderModel;
import com.carefast.careops.model.project.EmployeeScheduleLeader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface EmployeeScheduleLeaderRepository extends JpaRepository<EmployeeScheduleLeader, Integer> {

    @Query(value = "SELECT te.id_detail_employee_project AS idDetailEmployeeProject, " +
            "te.id_project AS idProject, te.id_employee AS idEmployee, " +
            "tei.employee_name AS employeeName, te.id_shift AS idShift, " +
            "ts.shift_description AS shiftDescription, " +
            "te.date AS date, td.start_at AS startAt, td.end_at AS endAt, " +
            "te.schedule_type AS scheduleType, te.is_off AS isOff, " +
            "te.status_attendance AS statusAttendance " +
            "FROM tab_employee_schedule_pengawas AS te " +
            "INNER JOIN tab_employee_insys AS tei ON te.id_employee = tei.id_employee " +
            "INNER JOIN tab_shift AS ts ON te.id_shift = ts.shift_id " +
            "INNER JOIN tab_detail_shift_project AS td ON te.id_shift = td.id_shift " +
            "WHERE te.id_employee = :employeeId " +
            "AND td.id_project = te.id_project " +
            "AND te.date >= :date " +
            "AND te.status = 'Y' " +
            "ORDER BY te.date ASC, td.start_at", nativeQuery = true)
    Slice<CustomEmployeeScheduleLeaderModel> getNonOperatorCalendar(int employeeId, LocalDate date, Pageable pageable);
}
