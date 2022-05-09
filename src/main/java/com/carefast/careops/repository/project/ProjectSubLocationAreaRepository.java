package com.carefast.careops.repository.project;

import com.carefast.careops.model.project.ProjectSubLocationAreaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectSubLocationAreaRepository extends JpaRepository<ProjectSubLocationAreaModel, Integer> {
//    @Query(value = "SELECT s.sub_location_id AS subLocationId, s.sub_location_name AS subLocationName " +
//            "FROM tab_sub_location_area AS ts " +
//            "RIGHT JOIN tab_sub_location AS s ON ts.sub_location_id = s.sub_location_id " +
//            "WHERE ts.id_project = :projectId " +
//            "AND ts.id_location = :locationId ", nativeQuery = true)
//    List<ProjectSubLocationModel> getSubLocationByProjectAndLocation(String projectId, int locationId);

    @Query("SELECT p FROM ProjectSubLocationAreaModel p " +
            "WHERE p.idProject = :projectId " +
            "AND p.idLocation = :locationId")
    List<ProjectSubLocationAreaModel> getSubLocationByProjectAndLocation(String projectId, int locationId);

}
