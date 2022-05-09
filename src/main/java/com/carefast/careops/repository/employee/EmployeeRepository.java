package com.carefast.careops.repository.employee;

import com.carefast.careops.model.employee.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer> {

    @Query(value = "SELECT * FROM tab_employee_project " +
            "WHERE employee_email = :email", nativeQuery = true)
    EmployeeModel findByEmail(String email);

}
