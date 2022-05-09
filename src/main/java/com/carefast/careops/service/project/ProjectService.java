package com.carefast.careops.service.project;

import com.carefast.careops.dto.project.ProjectShiftDTO;
import com.carefast.careops.dto.project.ShiftDTO;
import com.carefast.careops.model.project.*;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.pengawas.PengawasRepository;
import com.carefast.careops.repository.pengawas.PengawasPlottingRepository;
import com.carefast.careops.repository.project.*;
import com.carefast.careops.repository.schedule.EmployeeScheduleRepository;
import com.carefast.careops.repository.schedule.ScheduleRepository;
import com.carefast.careops.utils.ConvertDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectShiftRepository projectShiftRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private EmployeeScheduleRepository employeeScheduleRepository;
    @Autowired
    private ConvertDateUtils convertDateUtils;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private PengawasPlottingRepository pengawasPlottingRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private ProjectSubLocationAreaRepository projectSubLocationAreaRepository;
    @Autowired
    private ProjectLocationRepository projectLocationRepository;
    @Autowired
    private ProjectSubLocationRepository projectSubLocationRepository;
    @Autowired
    private PengawasRepository pengawasRepository;


    public List<ProjectShiftDTO> getShiftProject(String projectId) {
        return projectShiftRepository.getAllShiftByProjectId(projectId).stream()
                .map(this::convertProjectShiftDTO).collect(Collectors.toList());

    }

    public ProjectShiftDTO convertProjectShiftDTO(ProjectShiftModel item) {
        ProjectShiftDTO projectShiftDTO = new ProjectShiftDTO();

        projectShiftDTO.setIdDetailShift(item.getIdDetailShift());
        projectShiftDTO.setIdProject(item.getIdProject());
        projectShiftDTO.setStartAt(item.getEndAt());
        projectShiftDTO.setEndAt(item.getEndAt());

        Optional<ShiftModel> shiftDTO = shiftRepository.findById(item.getIdShift());
        projectShiftDTO.setShift(convertShiftDTO(shiftDTO.get()));

        return projectShiftDTO;
    }

    public ShiftDTO convertShiftDTO(ShiftModel item) {
        return new ShiftDTO(item.getShiftId(), item.getShiftName(), item.getShiftDescription());
    }
}
