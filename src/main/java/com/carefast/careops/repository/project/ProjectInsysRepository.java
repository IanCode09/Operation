package com.carefast.careops.repository.project;

import com.carefast.careops.model.INSYS.InsysProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectInsysRepository extends JpaRepository<InsysProjectModel, Integer> {
    @Query("SELECT p FROM InsysProjectModel p " +
            "WHERE p.projectCode = :projectCode")
    Optional<InsysProjectModel> findByProjectCode(String projectCode);
}
