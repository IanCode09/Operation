package com.carefast.careops.repository.employee;

import com.carefast.careops.model.employee.EmployeeOTPModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmployeeOTPRepository extends JpaRepository<EmployeeOTPModel, Integer> {

    @Query(value = "SELECT * FROM tab_otp " +
            "WHERE employee_email = :email " +
            "AND otp_code = :codeOtp " +
            "AND expired_at >= :currentDateTime", nativeQuery = true)
    Optional<EmployeeOTPModel> findCodeOTPWithExpired(String email, String codeOtp, LocalDateTime currentDateTime);

    @Query(value = "SELECT * FROM tab_otp " +
            "WHERE employee_email = :email " +
            "AND otp_code = :codeOtp ", nativeQuery = true)
    Optional<EmployeeOTPModel> findCodeOTP(String email, String codeOtp);

    @Query("SELECT e FROM EmployeeOTPModel e " +
            "WHERE e.employeeEmail = :email ")
    Optional<EmployeeOTPModel> findByEmail(String email);
}
