package com.carefast.careops.repository.project;

import com.carefast.careops.model.project.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, Integer> {

    @Query(value = "SELECT * FROM tab_project WHERE project_id = :projectId", nativeQuery = true)
    List<ProjectModel> findOne(String projectId);
}
