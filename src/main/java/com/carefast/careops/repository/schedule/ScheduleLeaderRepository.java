package com.carefast.careops.repository.schedule;

import com.carefast.careops.model.schedule.SchedulePengawasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScheduleLeaderRepository extends JpaRepository<SchedulePengawasModel, Integer> {
    @Query("SELECT s FROM SchedulePengawasModel s " +
            "WHERE s.idEmployee = :employeeId " +
            "AND s.projectCode = :projectCode " +
            "AND s.monthPengawas = :month " +
            "AND s.yearPengawas = :year ")
    Optional<SchedulePengawasModel> getScheduleLeader(int employeeId, String projectCode, int month, int year);
}
