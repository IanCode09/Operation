package com.carefast.careops.repository.INSYS;

import com.carefast.careops.model.INSYS.InsysProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InsysProjectRepository extends JpaRepository<InsysProjectModel, Integer> {

    @Query(value = "SELECT * FROM tab_project_insys " +
            "WHERE project_code  = :projectCode ", nativeQuery = true)
    Optional<InsysProjectModel> findByProjectCode(String projectCode);
}
