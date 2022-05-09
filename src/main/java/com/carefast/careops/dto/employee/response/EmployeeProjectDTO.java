package com.carefast.careops.dto.employee.response;

import com.carefast.careops.dto.project.ProjectDTO;

import java.util.List;

public class EmployeeProjectDTO {
    List<ProjectDTO> projects;

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }
}
