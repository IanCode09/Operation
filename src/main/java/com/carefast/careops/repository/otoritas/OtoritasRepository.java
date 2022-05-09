package com.carefast.careops.repository.otoritas;

import com.carefast.careops.model.project.OtoritasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OtoritasRepository extends JpaRepository<OtoritasModel, Integer> {

    @Query("SELECT o FROM OtoritasModel o " +
            "WHERE o.projectCodeOtoritas = :projectCode " +
            "AND o.namaModule = 'checklist'")
    List<OtoritasModel> getOtoritasProjectChecklist(String projectCode);
}
