package com.carefast.careops.repository.INSYS;

import com.carefast.careops.model.INSYS.InsysJobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InsysJobRepository extends JpaRepository<InsysJobModel, Integer> {

    @Query("SELECT i FROM InsysJobModel i " +
            "WHERE i.jobCode = :jobCode")
    Optional<InsysJobModel> findByJobCode(String jobCode);
}
