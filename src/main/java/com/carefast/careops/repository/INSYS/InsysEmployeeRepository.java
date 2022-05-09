package com.carefast.careops.repository.INSYS;

import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InsysEmployeeRepository extends JpaRepository<InsysEmployeeModel, Integer> {

    @Query(value = "SELECT * FROM tab_employee_insys " +
            "WHERE employee_code = :employeeCode ", nativeQuery = true)
    Optional<InsysEmployeeModel> findByEmployeeCode(String employeeCode);

    @Query(value = "SELECT * FROM tab_employee_insys " +
            "WHERE employee_code = :nuc " +
            "AND employee_nik = :nik", nativeQuery = true)
    Optional<InsysEmployeeModel> findByNucNik(String nuc, String nik);

    @Query(value = "SELECT * FROM tab_employee_insys " +
            "WHERE employee_code = :nuc", nativeQuery = true)
    Optional<InsysEmployeeModel> findByNuc(String nuc);

    @Query(value = "SELECT * FROM tab_employee_insys " +
            "WHERE employee_code = :nuc " +
            "AND employee_email = :email", nativeQuery = true)
    Optional<InsysEmployeeModel> findByNucEmail(String nuc, String email);

    @Query("SELECT i FROM InsysEmployeeModel i " +
            "WHERE i.employeeEmail = :employeeEmail")
    Optional<InsysEmployeeModel> findByEmail(String employeeEmail);

    @Query("SELECT i FROM InsysEmployeeModel i " +
            "WHERE i.idEmployee = :employeeId " +
            "AND i.projectCode = :projectCode ")
    Optional<InsysEmployeeModel> findByEmployeeIdProjectCode(int employeeId, String projectCode);

    @Query("SELECT i FROM InsysEmployeeModel i " +
            "WHERE i.idEmployee = :employeeId " +
            "AND i.employeePassword = :password")
    Optional<InsysEmployeeModel> checkPasswordEmployee(int employeeId, String password);

    @Query("SELECT i FROM InsysEmployeeModel i " +
            "WHERE i.projectCode = :projectCode " +
            "AND i.jobCode = 'CLEANER' " +
            "AND i.isActive = 'Y' ")
    List<InsysEmployeeModel> findEmployeeCleanerActive(String projectCode);

    @Query("SELECT i FROM InsysEmployeeModel i " +
            "WHERE i.idEmployee = :employeeId " +
            "AND i.jobCode = 'CLEANER'")
    List<InsysEmployeeModel> getDetailEmployeeCleaner(int employeeId);

    @Query("SELECT i FROM InsysEmployeeModel i " +
            "WHERE i.projectCode = :projectCode " +
            "AND i.jobCode = 'CS-SUPERVISOR' " +
            "AND i.isActive = 'Y' ")
    List<InsysEmployeeModel> findEmployeeSupervisorActive(String projectCode);
}