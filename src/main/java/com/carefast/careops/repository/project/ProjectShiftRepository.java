package com.carefast.careops.repository.project;

import com.carefast.careops.model.project.ProjectShiftModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectShiftRepository extends JpaRepository<ProjectShiftModel, Integer> {
    @Query(value = "SELECT * FROM tab_detail_shift_project " +
            "WHERE id_project = :idProject " +
            "AND id_shift = :idShift", nativeQuery = true)
    Optional<ProjectShiftModel> getDetailShiftByProjectId(String idProject, int idShift);

    @Query("SELECT d FROM ProjectShiftModel d " +
            "WHERE d.idProject = :idProject")
    List<ProjectShiftModel> getAllShiftByProjectId(String idProject);

    @Query("SELECT SUM(d.peopleTotal) FROM ProjectShiftModel d " +
            "WHERE d.idProject = :projectCode")
    int getTotalEmployeeInProject(String projectCode);
}
