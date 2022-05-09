package com.carefast.careops.service.employee;

import com.carefast.careops.dto.project.ProjectDTOInsys;
import com.carefast.careops.dto.schedule.*;
import com.carefast.careops.model.INSYS.InsysProjectModel;
import com.carefast.careops.model.project.CustomEmployeeDetailListProjectModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.model.project.ProjectShiftModel;
import com.carefast.careops.model.schedule.SchedulePengawasModel;
import com.carefast.careops.repository.INSYS.InsysProjectRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.project.ProjectInsysRepository;
import com.carefast.careops.repository.project.ProjectShiftRepository;
import com.carefast.careops.repository.project.ShiftRepository;
import com.carefast.careops.repository.schedule.ScheduleLeaderRepository;
import com.carefast.careops.utils.ConvertDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeScheduleService {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private ProjectInsysRepository projectInsysRepository;
    @Autowired
    private ProjectShiftRepository projectShiftRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private ConvertDateUtils convertDateUtils;
    @Autowired
    private ScheduleLeaderRepository scheduleLeaderRepository;
    @Autowired
    private InsysProjectRepository insysProjectRepository;

    public List<ScheduleDTO> getEmployeeSchedule(int employeeId, String projectId) {
        return employeeProjectRepository.getScheduleByDate(employeeId, projectId, LocalDate.now()).
                stream().map(this::convertScheduleDTO).collect(Collectors.toList());
    }

    public ScheduleProjectDetailDTO getDetailEmployeeSchedule(int scheduleId) {
        Optional<EmployeeDetailListProjectModel> schedule = employeeProjectRepository.findById(scheduleId);

        if (schedule.isEmpty()) return null;

        return convertScheduleProjectDetailDTO(schedule.get());
    }

    public ScheduleNonOperatorDTO getEmployeeNonOperatorSchedule(int employeeId, String projectId) {
        Optional<SchedulePengawasModel> schedule = scheduleLeaderRepository.getScheduleLeader(employeeId, projectId, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        Optional<InsysProjectModel> project = insysProjectRepository.findByProjectCode(projectId);

        if (schedule.isPresent()) {
            int shiftToday = convertDateUtils.validateDate(schedule.get());

            Optional<ProjectShiftModel> detailShiftProject = projectShiftRepository.getDetailShiftByProjectId(projectId, shiftToday);

            System.out.println(shiftToday);

            if (shiftToday != 4) {
                ScheduleNonOperatorDTO scheduleNonOperatorDTO = new ScheduleNonOperatorDTO();
                scheduleNonOperatorDTO.setIdScheduleLeader(schedule.get().getIdSchedulePengawas());
                scheduleNonOperatorDTO.setIdEmployee(schedule.get().getIdEmployee());
                scheduleNonOperatorDTO.setProjectCode(schedule.get().getProjectCode());
                scheduleNonOperatorDTO.setMonthLeader(schedule.get().getMonthPengawas());
                scheduleNonOperatorDTO.setYearLeader(schedule.get().getYearPengawas());
                scheduleNonOperatorDTO.setShiftId(shiftToday);
                scheduleNonOperatorDTO.setIdDetailShift(detailShiftProject.get().getIdDetailShift());
                scheduleNonOperatorDTO.setStartAt(detailShiftProject.get().getStartAt());
                scheduleNonOperatorDTO.setEndAt(detailShiftProject.get().getEndAt());
                scheduleNonOperatorDTO.setLatitude(project.get().getLattitude());
                scheduleNonOperatorDTO.setLongitude(project.get().getLongittude());
                scheduleNonOperatorDTO.setRadius(project.get().getRadius());

                return scheduleNonOperatorDTO;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<CustomEmployeeDetailListProjectDTO> getEmployeeScheduleToday(int employeeId, String projectId) {
        return employeeProjectRepository.getEmployeeSchedule(employeeId, projectId, LocalDate.now())
                .stream().map(this::convertCustomEmployeeDetailListProjectDTO)
                .collect(Collectors.toList());
    }

    public ScheduleDTO convertScheduleDTO(EmployeeDetailListProjectModel item) {
        return new ScheduleDTO( item.getIdDetailEmployeeProject(), item.getIdEmployee(), item.getIdProject(), item.getIdLocation(),
                item.getIdPlotting(), item.getIdShift(), item.getDate(), item.getScheduleType(), item.getAssignBy(), item.getStatus(), item.getChecklistByEmployee(), item.getIsDone()
        );
    }

    public ScheduleProjectDetailDTO convertScheduleProjectDetailDTO(EmployeeDetailListProjectModel item) {
        ScheduleProjectDetailDTO scheduleDTO = new ScheduleProjectDetailDTO();
        scheduleDTO.setIdDetailEmployeeProject(item.getIdDetailEmployeeProject());
        scheduleDTO.setEmployeeId(item.getIdEmployee());
        scheduleDTO.setProject(convertProjectDTOInsys(projectInsysRepository.findByProjectCode(item.getIdProject()).get()));
        scheduleDTO.setLocationId(item.getIdLocation());
        scheduleDTO.setPlottingId(item.getIdPlotting());
        scheduleDTO.setScheduleShift(convertDetailShiftDTO(projectShiftRepository.getDetailShiftByProjectId(item.getIdProject(), item.getIdShift()).get()));
        scheduleDTO.setDate(item.getDate());
        scheduleDTO.setScheduleType(item.getScheduleType());
        scheduleDTO.setAssignBy(item.getAssignBy());
        scheduleDTO.setStatus(item.getStatus());

        return scheduleDTO;
    }

    public ProjectDTOInsys convertProjectDTOInsys(InsysProjectModel item) {
        ProjectDTOInsys projectDTOInsys = new ProjectDTOInsys();
        projectDTOInsys.setProjectId(item.getIdProject());
        projectDTOInsys.setProjectCode(item.getProjectCode());
        projectDTOInsys.setProjectName(item.getProjectName());
        projectDTOInsys.setBranchCode(item.getBranchCode());
        projectDTOInsys.setBranchName(item.getBranchName());
        projectDTOInsys.setProjectAddress(item.getProjectAddress());
        projectDTOInsys.setLatitude(item.getLattitude());
        projectDTOInsys.setLongitude(item.getLongittude());
        projectDTOInsys.setRadius(item.getRadius());
        projectDTOInsys.setStartDate(item.getStartDate());
        projectDTOInsys.setEndDate(item.getEndDate());

        return projectDTOInsys;
    }

    public DetailShiftDTO convertDetailShiftDTO(ProjectShiftModel item) {
        DetailShiftDTO detailShiftDTO = new DetailShiftDTO();
        detailShiftDTO.setIdDetailShift(item.getIdDetailShift());
        detailShiftDTO.setIdProject(item.getIdProject());
        detailShiftDTO.setStartAt(item.getStartAt());
        detailShiftDTO.setEndAt(item.getEndAt());

        return detailShiftDTO;
    }

    public CustomEmployeeDetailListProjectDTO convertCustomEmployeeDetailListProjectDTO(CustomEmployeeDetailListProjectModel item) {
        return new CustomEmployeeDetailListProjectDTO(
                item.getIdDetailEmployeeProject(),
                item.getIdEmployee(),
                item.getEmployeeName(),
                item.getIdProject(),
                item.getScheduleType(),
                item.getIdPlotting(),
                item.getcodePlottingArea(),
                item.getLocationId(),
                item.getLocationName(),
                item.getSubLocationId(),
                item.getSubLocationName(),
                item.getDate(),
                item.getDate(),
                item.getIdShift(),
                item.getShiftDescription(),
                item.getStartAt(),
                item.getEndAt(),
                item.getIsDone(),
                item.getDoneAt()
        );
    }
}
