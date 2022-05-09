package com.carefast.careops.repository.employee;

import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeInsysRepository extends JpaRepository<InsysEmployeeModel, Integer> {

}
