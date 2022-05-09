package com.carefast.careops.repository.barcode;

import com.carefast.careops.model.barcode.EmployeeBarcodeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeBarcodeRepository extends JpaRepository<EmployeeBarcodeModel, Integer> {
    @Query(value = "SELECT * FROM tab_employee_barcode " +
            "WHERE employee_id = :employeeId " +
            "AND project_code = :projectCode " +
            "ORDER BY id_barcode DESC " +
            "LIMIT 1", nativeQuery = true)
    Optional<EmployeeBarcodeModel> getEmployeeBarcode(int employeeId, String projectCode);

    @Query(value = "SELECT * FROM tab_employee_barcode " +
            "WHERE employee_id = :employeeId " +
            "AND barcode_key = :barcodeKey", nativeQuery = true)
    Optional<EmployeeBarcodeModel> getEmployeeBarcodeByBarcodeKey(int employeeId, String barcodeKey);
}
