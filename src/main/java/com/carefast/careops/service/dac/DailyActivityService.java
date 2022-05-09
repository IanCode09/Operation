package com.carefast.careops.service.dac;

import com.carefast.careops.dto.activity.DACEmployeePlottingDTO;
import com.carefast.careops.dto.activity.DACEmployeeProfileDTO;
import com.carefast.careops.dto.activity.DacDTO;
import com.carefast.careops.dto.activity.DailyActivitiesDTO;
import com.carefast.careops.dto.checklist.ChecklistDACEmployeeDTO;
import com.carefast.careops.dto.schedule.ScheduleDTO;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.checklist.ChecklistDacEmployeeModel;
import com.carefast.careops.model.employee.EmployeeAttendanceModel;
import com.carefast.careops.model.pengawas.PengawasPlottingModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.model.project.ProjectSubLocationActivityModel;
import com.carefast.careops.model.project.ProjectSubLocationAreaModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.attendance.EmployeeAttendanceRepository;
import com.carefast.careops.repository.checklist.ChecklistDacEmployeeRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.pengawas.PengawasPlottingRepository;
import com.carefast.careops.repository.project.*;
import com.carefast.careops.utils.MasterSplitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DailyActivityService {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private ProjectSubLocationAreaRepository projectSubLocationAreaRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private PengawasPlottingRepository pengawasPlottingRepository;
    @Autowired
    private ProjectLocationRepository projectLocationRepository;
    @Autowired
    private ProjectSubLocationRepository projectSubLocationRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private ProjectSubLocationActivityRepository projectSubLocationActivityRepository;
    @Autowired
    private MasterSplitUtils masterSplitUtils;
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;
    @Autowired
    private ChecklistDacEmployeeRepository checklistDacEmployeeRepository;

    public DacDTO getDailyActivity(int id, int employeeId, String projectId) {

        Optional<EmployeeDetailListProjectModel> employeeDetailListProjectModel = employeeProjectRepository.findById(id);

        if (employeeDetailListProjectModel.isPresent()) {
            Optional<ProjectSubLocationAreaModel> plotting = projectSubLocationAreaRepository.findById(employeeDetailListProjectModel.get().getIdPlotting());
            Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findByEmployeeIdProjectCode(employeeId, projectId);
            Optional<EmployeeAttendanceModel> attendanceModel = employeeAttendanceRepository.getAttendanceInfoByScheduleId(id);

            DACEmployeeProfileDTO dacEmployeeProfileDTO = new DACEmployeeProfileDTO();
            dacEmployeeProfileDTO.setEmployeeId(employee.get().getIdEmployee());
            dacEmployeeProfileDTO.setEmployeeCode(employee.get().getEmployeeCode());
            dacEmployeeProfileDTO.setEmployeeName(employee.get().getEmployeeName());
            dacEmployeeProfileDTO.setJobCode(employee.get().getJobCode());
            dacEmployeeProfileDTO.setJobName(employee.get().getJobName());
            dacEmployeeProfileDTO.setAttendanceImage(attendanceModel.isPresent() ? attendanceModel.get().getEmployeeImgSelfieIn() : null);

            DACEmployeePlottingDTO dacEmployeePlottingDTO = new DACEmployeePlottingDTO();
            PengawasPlottingModel pengawas = pengawasPlottingRepository.getEmployeePengawas(plotting.get().getIdSubLocationArea(), plotting.get().getIdProject(), LocalDate.now()).get();
            dacEmployeePlottingDTO.setEmployeePengawasId(pengawas.getEmployeeId());
            dacEmployeePlottingDTO.setEmployeePengawasName(insysEmployeeRepository.findById(pengawas.getEmployeeId()).get().getEmployeeName());
            dacEmployeePlottingDTO.setPlottingId(plotting.get().getIdSubLocationArea());
            dacEmployeePlottingDTO.setCodePlottingArea(plotting.get().getCodePlottingArea());
            dacEmployeePlottingDTO.setLocationId(plotting.get().getIdLocation());
            dacEmployeePlottingDTO.setLocationName(projectLocationRepository.findById(plotting.get().getIdLocation()).get().getLocationName());
            dacEmployeePlottingDTO.setSubLocationId(plotting.get().getSubLocationId());
            dacEmployeePlottingDTO.setSubLocationName(projectSubLocationRepository.findById(plotting.get().getSubLocationId()).get().getSubLocationName());
            dacEmployeePlottingDTO.setShiftId(plotting.get().getShiftId());
            dacEmployeePlottingDTO.setShiftDescription(shiftRepository.findById(plotting.get().getShiftId()).get().getShiftDescription());

            List<ProjectSubLocationActivityModel> activites = projectSubLocationActivityRepository.getActivities(
                    plotting.get().getIdProject(), plotting.get().getIdLocation(), plotting.get().getSubLocationId(), plotting.get().getShiftId());

            dacEmployeePlottingDTO.setIsDone(employeeDetailListProjectModel.get().getIsDone());
            dacEmployeePlottingDTO.setChecklistByEmployee(employeeDetailListProjectModel.get().getChecklistByEmployee());

            dacEmployeePlottingDTO.setCountDailyActivities(activites.size());
            dacEmployeePlottingDTO.setCountChecklistDACByEmployee(checklistDacEmployeeRepository.count(employeeId, projectId, id));

            dacEmployeePlottingDTO.setDailyActivities(activites.stream().map(activity -> convertDailyActivitiesDTO(activity)).collect(Collectors.toList()));

            DacDTO dacDTO = new DacDTO();
            dacDTO.setEmployee(dacEmployeeProfileDTO);
            dacDTO.setPlotting(dacEmployeePlottingDTO);

            return dacDTO;
        } else {
            return null;
        }
    }

    public ChecklistDACEmployeeDTO checkDailyActivity(int employeeId, int idDetailEmployeeProject, String projectId, int plottingId, int shiftId, int locationId, int subLocationId, int activityId) {

        Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findById(employeeId);

        ChecklistDacEmployeeModel checklistDacEmployeeModel = new ChecklistDacEmployeeModel();
        checklistDacEmployeeModel.setEmployeeId(employeeId);
        checklistDacEmployeeModel.setJobCode(employee.get().getJobCode());
        checklistDacEmployeeModel.setIdDetailEmployeeProject(idDetailEmployeeProject);
        checklistDacEmployeeModel.setProjectId(projectId);
        checklistDacEmployeeModel.setPlottingId(plottingId);
        checklistDacEmployeeModel.setShiftId(shiftId);
        checklistDacEmployeeModel.setLocationId(locationId);
        checklistDacEmployeeModel.setSubLocationId(subLocationId);
        checklistDacEmployeeModel.setActivityId(activityId);
        checklistDacEmployeeModel.setCreatedAt(LocalDateTime.now());

        return convertChecklistDACEmployeeDTO(checklistDacEmployeeRepository.save(checklistDacEmployeeModel));
    }

    public ScheduleDTO confirmDailyActivity(int idDetailEmployeeProject) {

        Optional<EmployeeDetailListProjectModel> employeeSchedule = employeeProjectRepository.findById(idDetailEmployeeProject);
        employeeSchedule.get().setIsDone("Y");
        employeeSchedule.get().setChecklistByEmployee("Y");
        employeeSchedule.get().setDoneAt(LocalDateTime.now());

        return convertScheduleDTO(employeeProjectRepository.save(employeeSchedule.get()));
    }

    public ChecklistDACEmployeeDTO getStatusChecklistDacByEmployee(int employeeId, String projectId, int idDetailEmployeeProject, int activityId, int plottingId) {
        Optional<ChecklistDacEmployeeModel> employeeDacCheklist = checklistDacEmployeeRepository.getStatusChecklistDacByEmployee(employeeId, projectId, idDetailEmployeeProject, activityId, plottingId);

        if (employeeDacCheklist.isPresent()) {
            return convertChecklistDACEmployeeDTO(employeeDacCheklist.get());
        } else {
            return null;
        }
    }

    public DACEmployeePlottingDTO convertDACEmployeePlottingDTO(ProjectSubLocationAreaModel item) {
        DACEmployeePlottingDTO dacEmployeePlottingDTO = new DACEmployeePlottingDTO();

        PengawasPlottingModel pengawas = pengawasPlottingRepository.getEmployeePengawas(item.getIdSubLocationArea(), item.getIdProject(), LocalDate.now()).get();

        dacEmployeePlottingDTO.setEmployeePengawasId(pengawas.getEmployeeId());
        dacEmployeePlottingDTO.setEmployeePengawasName(insysEmployeeRepository.findById(pengawas.getEmployeeId()).get().getEmployeeName());
        dacEmployeePlottingDTO.setPlottingId(item.getIdSubLocationArea());
        dacEmployeePlottingDTO.setCodePlottingArea(item.getCodePlottingArea());
        dacEmployeePlottingDTO.setLocationId(item.getIdLocation());
        dacEmployeePlottingDTO.setLocationName(projectLocationRepository.findById(item.getIdLocation()).get().getLocationName());
        dacEmployeePlottingDTO.setSubLocationId(item.getSubLocationId());
        dacEmployeePlottingDTO.setSubLocationName(projectSubLocationRepository.findById(item.getSubLocationId()).get().getSubLocationName());
        dacEmployeePlottingDTO.setShiftId(item.getShiftId());
        dacEmployeePlottingDTO.setShiftDescription(shiftRepository.findById(item.getShiftId()).get().getShiftDescription());

        List<ProjectSubLocationActivityModel> activites = projectSubLocationActivityRepository.getActivities(
                item.getIdProject(), item.getIdLocation(), item.getSubLocationId(), item.getShiftId());

        dacEmployeePlottingDTO.setDailyActivities(activites.stream().map(activity -> convertDailyActivitiesDTO(activity)).collect(Collectors.toList()));

        return dacEmployeePlottingDTO;
    }

    public DailyActivitiesDTO convertDailyActivitiesDTO(ProjectSubLocationActivityModel item) {

        DailyActivitiesDTO dailyActivitiesDTO = new DailyActivitiesDTO();

        String machine = item.getMachineId() == null ? null : masterSplitUtils.master(item.getMachineId(), MasterSplitUtils.MACHINE);
        String tool = item.getToolsId() == null ? null : masterSplitUtils.master(item.getToolsId(), MasterSplitUtils.TOOL);
        String chemical = item.getChemicalId() == null ? null : masterSplitUtils.master(item.getChemicalId(), MasterSplitUtils.CHEMICAL);

        dailyActivitiesDTO.setIdSubLocationActivity(item.getIdSubLocationActivity());
        dailyActivitiesDTO.setObjectId(item.getObjectId());
        dailyActivitiesDTO.setObjectIdSecond(item.getObjectIdSecond());
        dailyActivitiesDTO.setObjectIdThird(item.getObjectIdThird());
        dailyActivitiesDTO.setObjectIdFour(item.getObjectIdFour());
        dailyActivitiesDTO.setObjectIdFive(item.getObjectIdFive());
        dailyActivitiesDTO.setActivity(item.getActivityId());
        dailyActivitiesDTO.setShiftDescription(shiftRepository.findById(item.getShitfId()).get().getShiftDescription());
        dailyActivitiesDTO.setStartAt(item.getStartAt());
        dailyActivitiesDTO.setEndAt(item.getEndAt());
        dailyActivitiesDTO.setMachineName(machine);
        dailyActivitiesDTO.setToolName(tool);
        dailyActivitiesDTO.setChemicalName(chemical);

        return dailyActivitiesDTO;
    }

    public ChecklistDACEmployeeDTO convertChecklistDACEmployeeDTO(ChecklistDacEmployeeModel item) {
        return new ChecklistDACEmployeeDTO(
                item.getChecklistDacEmployeeId(),
                item.getEmployeeId(),
                item.getJobCode(),
                item.getIdDetailEmployeeProject(),
                item.getProjectId(),
                item.getPlottingId(),
                item.getShiftId(),
                item.getLocationId(),
                item.getSubLocationId(),
                item.getActivityId()
        );
    }

    public ScheduleDTO convertScheduleDTO(EmployeeDetailListProjectModel item) {
        return new ScheduleDTO(
                item.getIdDetailEmployeeProject(),
                item.getIdEmployee(),
                item.getIdProject(),
                item.getIdLocation(),
                item.getIdPlotting(),
                item.getIdShift(),
                item.getDate(),
                item.getScheduleType(),
                item.getAssignBy(),
                item.getStatus(),
                item.getChecklistByEmployee(),
                item.getIsDone()
        );
    }
}
