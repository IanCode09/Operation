package com.carefast.careops.repository.checklist;

import com.carefast.careops.model.checklist.ChecklistDacEmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChecklistDacEmployeeRepository extends JpaRepository<ChecklistDacEmployeeModel, Integer> {
    @Query("SELECT COUNT(*) FROM ChecklistDacEmployeeModel AS c " +
            "WHERE c.employeeId = :employeeId " +
            "AND c.projectId = :projectId " +
            "AND c.idDetailEmployeeProject = :idDetailEmployeeProject")
    Integer count(int employeeId, String projectId, int idDetailEmployeeProject);

    @Query("SELECT c FROM ChecklistDacEmployeeModel AS c " +
            "WHERE c.employeeId = :employeeId " +
            "AND c.projectId = :projectId " +
            "AND c.idDetailEmployeeProject = :idDetailEmployeeProject " +
            "AND c.activityId = :activityId " +
            "AND c.plottingId = :plottingId")
    Optional<ChecklistDacEmployeeModel> getStatusChecklistDacByEmployee(int employeeId, String projectId, int idDetailEmployeeProject, int activityId, int plottingId);
}
