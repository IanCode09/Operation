package com.carefast.careops.repository.project;

import com.carefast.careops.model.project.ProjectSubLocationActivityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectSubLocationActivityRepository extends JpaRepository<ProjectSubLocationActivityModel, Integer> {

    @Query("SELECT a FROM ProjectSubLocationActivityModel a " +
            "WHERE a.projectId = :projectId " +
            "AND a.locationId = :locationId " +
            "AND a.subLocationId = :subLocationId " +
            "AND a.shitfId = :shiftId")
    List<ProjectSubLocationActivityModel> getActivities(String projectId, int locationId, int subLocationId, int shiftId);
}
