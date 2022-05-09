package com.carefast.careops.repository.schedule;

import com.carefast.careops.model.schedule.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleModel, Integer> {

    @Query(value = "SELECT * FROM tab_schedule " +
            "ORDER BY schedule_id DESC LIMIT 1", nativeQuery = true)
    ScheduleModel getLastSchedule();

    @Query("SELECT s FROM ScheduleModel s " +
            "WHERE s.scheduleId = :scheduleId")
    Optional<ScheduleModel> findByScheduleId(int scheduleId);

    @Query("SELECT s FROM ScheduleModel s " +
            "WHERE s.projectCode = :projectCode " +
            "AND s.month = :currentMonth " +
            "AND s.year = :currentYear")
    List<Optional<ScheduleModel>> getAllCurrentScheduleByProjectCode(String projectCode, int currentMonth, int currentYear);

    @Query("SELECT s FROM ScheduleModel s " +
            "WHERE s.projectCode = :projectCode " +
            "AND s.month = :month " +
            "AND s.year = :currentYear")
    List<ScheduleModel> getAllSchedulePerMonth(String projectCode, int month, int currentYear);
}
