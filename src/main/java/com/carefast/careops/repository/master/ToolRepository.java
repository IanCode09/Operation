package com.carefast.careops.repository.master;

import com.carefast.careops.model.master.ToolModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<ToolModel, Integer> {
}
