package com.carefast.careops.repository.schedule;

import com.carefast.careops.model.schedule.EmployeeScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeScheduleRepository extends JpaRepository<EmployeeScheduleModel, Integer> {

    @Query("SELECT e FROM EmployeeScheduleModel e " +
            "WHERE e.employeeId = :employeeId " +
            "AND e.projectCode = :projectCode " +
            "AND e.startAt <= :currentDate " +
            "AND e.endAt >= :currentDate")
    Optional<EmployeeScheduleModel> getEmployeeSchedule(int employeeId, String projectCode, LocalDate currentDate);

    @Query("SELECT e FROM EmployeeScheduleModel e " +
            "WHERE e.projectCode = :projectCode " +
            "AND e.startAt <= :currentDate " +
            "AND e.endAt >= :currentDate")
    List<EmployeeScheduleModel> getAllEmployeeByProject(String projectCode, LocalDate currentDate);

    @Query("SELECT e FROM EmployeeScheduleModel e " +
            "WHERE e.projectCode = :projectCode " +
            "AND e.scheduleId = :scheduleId")
    Optional<EmployeeScheduleModel> getScheduleByScheduleId(String projectCode, int scheduleId);
}
