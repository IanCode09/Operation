package com.carefast.careops.repository.pengawas;

import com.carefast.careops.model.pengawas.PengawasPlottingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PengawasPlottingRepository extends JpaRepository<PengawasPlottingModel, Integer> {
    @Query("SELECT p FROM PengawasPlottingModel p " +
            "WHERE p.employeeId = :employeeId " +
            "AND p.projectId = :projectId " +
            "AND p.shiftId = :shiftId " +
            "AND p.startDate <= :currentDate " +
            "AND p.status = 'Y' ")
    List<PengawasPlottingModel> getTeamByPlottingAndShift(int employeeId, String projectId, int shiftId, LocalDate currentDate);

    @Query("SELECT p FROM PengawasPlottingModel p " +
            "WHERE p.employeeId = :employeeId " +
            "AND p.projectId = :projectId " +
            "AND p.startDate <= :currentDate " +
            "AND p.status = 'Y' ")
    List<PengawasPlottingModel> getTeamByPlotting(int employeeId, String projectId, LocalDate currentDate);

    @Query("SELECT p FROM PengawasPlottingModel p " +
            "WHERE p.plottingId = :plottingId " +
            "AND p.projectId = :projectId " +
            "AND p.startDate <= :currentDate " +
            "AND p.status = 'Y'")
    Optional<PengawasPlottingModel> getEmployeePengawas(int plottingId, String projectId, LocalDate currentDate);

    @Query("SELECT p FROM PengawasPlottingModel p " +
            "WHERE p.employeeId = :employeeId " +
            "AND p.projectId = :projectId " +
            "AND p.shiftId = :shiftId " +
            "AND p.startDate <= :date " +
            "AND p.status = 'Y' ")
    List<PengawasPlottingModel> getTeamByDate(int employeeId, String projectId, int shiftId, LocalDate date);

    @Query("SELECT p FROM PengawasPlottingModel p " +
            "WHERE p.employeeId = :employeeId " +
            "AND p.projectId = :projectId " +
            "AND p.startDate <= :currentDate " +
            "AND p.shiftId = :shiftId " +
            "AND p.status = 'Y' ")
    List<PengawasPlottingModel> getTeamLeaderSpvGroupByShift(int employeeId, String projectId, int shiftId, LocalDate currentDate);

    @Query("SELECT p FROM PengawasPlottingModel p " +
            "WHERE p.employeeId = :employeeId " +
            "AND p.projectId = :projectId " +
            "AND p.startDate <= :currentDate " +
            "AND p.status = 'Y' " +
            "GROUP BY p.shiftId ")
    List<PengawasPlottingModel> getTeamLeaderShift(int employeeId, String projectId, LocalDate currentDate);

}
