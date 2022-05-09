package com.carefast.careops.controller.INSYS;

import com.carefast.careops.dto.INSYS.request.InsysEmployeeRequest;
import com.carefast.careops.dto.INSYS.request.InsysJobRequest;
import com.carefast.careops.dto.INSYS.request.InsysProjectRequest;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.INSYS.InsysService;
import com.carefast.careops.utils.InsysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/careops", produces = {"application/json"})
public class InsysController {

    @Value("${insys.credentials}")
    private String credentials;

    @Autowired
    private InsysService insysService;
    @Autowired
    private InsysUtils insysUtils;

    @PostMapping("/employees")
    public void getEmployessFromInsys(HttpServletRequest request, HttpServletResponse response, @RequestBody List<InsysEmployeeRequest> employeeRequest) throws IOException {

        if (insysUtils.apiKeyValidate(request.getHeader("x-api-key"))) {
            int result = insysService.insertEmployee(employeeRequest);

            if (result == InsysService.INSERT_DATA_INSYS_SUCCESS) {
                HandlerResponse.responseSuccessOK(response, "Insert Data Success");
            } else {
                HandlerResponse.responseBadRequest(response, "400", "Something Wrong");
            }
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Bad Credentials");
        }
    }

    @PostMapping("/projects")
    public void getProjectsFromInsys(HttpServletRequest request, HttpServletResponse response, @RequestBody List<InsysProjectRequest> projectRequests) {

        if (insysUtils.apiKeyValidate(request.getHeader("x-api-key"))) {
            int result = insysService.insertProjects(projectRequests);

            if (result == InsysService.INSERT_DATA_INSYS_SUCCESS) {
                HandlerResponse.responseSuccessOK(response, "Insert Data Success");
            } else {
                HandlerResponse.responseBadRequest(response, "400", "Something Wrong");
            }
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Bad Credentials");
        }
    }

    @PostMapping("/jobs")
    public void getJobsFromInsys(HttpServletRequest request, HttpServletResponse response, @RequestBody List<InsysJobRequest> jobRequests) {
        if (insysUtils.apiKeyValidate(request.getHeader("x-api-key"))) {
            int result = insysService.insertJobs(jobRequests);

            if (result == InsysService.INSERT_DATA_INSYS_SUCCESS) {
                DataResponse<List<InsysJobRequest>> data = new DataResponse<>();
                data.setData(jobRequests);
                HandlerResponse.responseSuccessWithData(response, data);
            } else {
                HandlerResponse.responseBadRequest(response, "400", "Something Wrong");
            }
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Bad Credentials");
        }
    }
}
