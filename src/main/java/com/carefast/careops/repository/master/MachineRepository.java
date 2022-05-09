package com.carefast.careops.repository.master;

import com.carefast.careops.model.master.MachineModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<MachineModel, Integer> {
}
