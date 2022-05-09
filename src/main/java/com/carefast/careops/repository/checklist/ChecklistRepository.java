package com.carefast.careops.repository.checklist;

import com.carefast.careops.model.checklist.ChecklistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<ChecklistModel, Integer> {
    @Query(value = "SELECT * FROM tab_checklist " +
            "WHERE employee_id = :employeeId " +
            "AND project_id = :projectId " +
            "AND plotting_id = :plottingId " +
            "AND activity_id = :activityId " +
            "AND DATE(created_at) <= :date ", nativeQuery = true )
    Optional<ChecklistModel> getActivityEmployeeChecklist(int employeeId, String projectId, int plottingId, int activityId, LocalDate date);
}
