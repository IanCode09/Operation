package com.carefast.careops.repository.project;

import com.carefast.careops.model.project.ProjectLocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectLocationRepository extends JpaRepository<ProjectLocationModel, Integer> {
    @Query("SELECT p FROM ProjectLocationModel p " +
            "WHERE p.projectId = :projectId ")
    List<ProjectLocationModel> getLocationByProjectId(String projectId);
}
