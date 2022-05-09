package com.carefast.careops.repository.master;

import com.carefast.careops.model.master.ChemicalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemicalRepository extends JpaRepository<ChemicalModel, Integer> {
}
