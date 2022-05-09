package com.carefast.careops.repository.project;

import com.carefast.careops.model.project.ShiftModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<ShiftModel, Integer> {
}
