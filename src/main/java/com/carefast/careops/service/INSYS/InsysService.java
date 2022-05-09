package com.carefast.careops.service.INSYS;

import com.carefast.careops.dto.INSYS.request.InsysEmployeeRequest;
import com.carefast.careops.dto.INSYS.request.InsysJobRequest;
import com.carefast.careops.dto.INSYS.request.InsysProjectRequest;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.INSYS.InsysJobModel;
import com.carefast.careops.model.INSYS.InsysProjectModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.INSYS.InsysJobRepository;
import com.carefast.careops.repository.INSYS.InsysProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InsysService {

    public static final int INSERT_DATA_INSYS_SUCCESS = 1;

    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private InsysProjectRepository insysProjectRepository;
    @Autowired
    private InsysJobRepository insysJobRepository;

    public int insertEmployee(List<InsysEmployeeRequest> employeeRequest) {
        List<InsysEmployeeModel> employees = insysEmployeeRepository.findAll();
        employees.forEach(e -> {
            Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findByEmployeeCode(e.getEmployeeCode());

            if (employee.isPresent()) {
                employee.get().setIsActive("N");
                employee.get().setUpdatedAt(LocalDateTime.now());

                insysEmployeeRepository.save(employee.get());
            }
        });

        employeeRequest.forEach(request -> {
            Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findByEmployeeCode(request.getEmployeeCode());

            if (employee.isPresent()) {
                String employeePhone = employee.get().getEmployeePhone() != null ? employee.get().getEmployeePhone() : request.getEmployeePhone();

                employee.get().setEmployeeName(request.getEmployeeName());
                employee.get().setEmployeePhone(employeePhone);
                employee.get().setEmployeeNik(request.getEmployeeNik());
                employee.get().setJobCode(request.getJobCode());
                employee.get().setJobName(request.getJobName());
                employee.get().setProjectCode(request.getProjectCode());
                employee.get().setBeginDate(request.getBeginDate());
                employee.get().setEndDate(request.getEndDate());
                employee.get().setIsActive("Y");
                employee.get().setUpdatedAt(LocalDateTime.now());

                insysEmployeeRepository.save(employee.get());
            } else {
                InsysEmployeeModel newEmployee = new InsysEmployeeModel(
                        request.getEmployeeCode(),
                        request.getEmployeeName(),
                        request.getEmployeePhone(),
                        request.getEmployeeNik(),
                        request.getJobCode(),
                        request.getJobName(),
                        request.getProjectCode(),
                        request.getBeginDate(),
                        request.getEndDate(),
                        "Y",
                        LocalDateTime.now()
                );

                insysEmployeeRepository.save(newEmployee);
            }
        });

        return INSERT_DATA_INSYS_SUCCESS;
    }

    public int insertProjects(List<InsysProjectRequest> projectRequests) {
        projectRequests.forEach(request -> {
            Optional<InsysProjectModel> project = insysProjectRepository.findByProjectCode(request.getProjectCode());

            if (project.isPresent()) {
                project.get().setProjectCode(request.getProjectCode());
                project.get().setProjectName(request.getProjectName());
                project.get().setBranchCode(request.getBranchCode());
                project.get().setBranchName(request.getBranchName());
                project.get().setProjectAddress(request.getProjectAddress());
                project.get().setStartDate(request.getStartDate());
                project.get().setEndDate(request.getEndDate());
                project.get().setUpdatedAt(LocalDateTime.now());

                insysProjectRepository.save(project.get());
            } else {
                InsysProjectModel projectModel = new InsysProjectModel(
                        request.getProjectCode(),
                        request.getProjectName(),
                        request.getBranchCode(),
                        request.getBranchName(),
                        request.getProjectAddress(),
                        request.getStartDate(),
                        request.getEndDate(),
                        LocalDateTime.now()
                );

                insysProjectRepository.save(projectModel);
            }
        });

        return INSERT_DATA_INSYS_SUCCESS;
    }

    public int insertJobs(List<InsysJobRequest> jobRequests) {
        jobRequests.forEach(jobRequest -> {
            Optional<InsysJobModel> job = insysJobRepository.findByJobCode(jobRequest.getJobCode());

            if (job.isPresent()) {
                job.get().setJobCode(jobRequest.getJobCode());
                job.get().setJobName(jobRequest.getJobName());
                job.get().setDescription(jobRequest.getDescription());
                job.get().setUpdatedAt(LocalDateTime.now());

                insysJobRepository.save(job.get());
            } else {
                InsysJobModel jobModel = new InsysJobModel(
                        jobRequest.getJobCode(),
                        jobRequest.getJobName(),
                        jobRequest.getDescription(),
                        LocalDateTime.now()
                );

                insysJobRepository.save(jobModel);
            }
        });

        return INSERT_DATA_INSYS_SUCCESS;
    }

}
