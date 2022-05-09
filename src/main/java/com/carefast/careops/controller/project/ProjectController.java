package com.carefast.careops.controller.project;

import com.carefast.careops.dto.project.ProjectShiftDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/project", produces = {"application/json"})
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/shift")
    public void getProjectShift(HttpServletRequest request, HttpServletResponse response, @RequestParam String projectId) throws IOException {

        List<ProjectShiftDTO> projectShiftDTO = new ArrayList<>();
        projectShiftDTO.addAll(projectService.getShiftProject(projectId));

        DataResponse<List<ProjectShiftDTO>> data = new DataResponse<>();
        data.setData(projectShiftDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }
}
