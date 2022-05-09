package com.carefast.careops.repository.job;

import com.carefast.careops.model.job.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepository extends JpaRepository<JobModel, Integer> {
//    JobModel findByJobPositionId(Optional<EmployeeModel> employeeModel);
    JobModel findByJobPositionId(int jobPositionId);
}
