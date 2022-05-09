package com.carefast.careops.repository.pengawas;

import com.carefast.careops.model.pengawas.PengawasLeaderModel;
import com.carefast.careops.model.pengawas.PengawasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PengawasRepository extends JpaRepository<PengawasModel, Integer> {

    @Query("SELECT p FROM PengawasModel p " +
            "WHERE p.employeeId = :employeeId " +
            "AND p.projectId = :projectId " +
            "AND p.startDate <= :date " +
            "AND p.status = 'Y' ")
    List<PengawasModel> getAllLeader(int employeeId, String projectId, LocalDate date);

    @Query("SELECT p FROM PengawasModel p " +
            "WHERE p.projectId = :projectId " +
            "AND p.startDate <= :date " +
            "AND p.status = 'Y' ")
    List<PengawasModel> getAllSpv(String projectId, LocalDate date);
}
