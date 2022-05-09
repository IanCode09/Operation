package com.carefast.careops.service.team;

import com.carefast.careops.dto.employee.response.EmployeeProfileDTO;
import com.carefast.careops.dto.job.JobPositionDTO;
import com.carefast.careops.dto.team.TeamOperatorDTO;
import com.carefast.careops.dto.team.TeamDTO;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.job.JobModel;
import com.carefast.careops.model.pengawas.PengawasModel;
import com.carefast.careops.model.pengawas.PengawasPlottingModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.model.project.ProjectSubLocationAreaModel;
import com.carefast.careops.model.schedule.EmployeeScheduleModel;
import com.carefast.careops.model.schedule.ScheduleModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.pengawas.PengawasPlottingRepository;
import com.carefast.careops.repository.pengawas.PengawasRepository;
import com.carefast.careops.repository.project.*;
import com.carefast.careops.repository.schedule.EmployeeScheduleRepository;
import com.carefast.careops.repository.schedule.ScheduleRepository;
import com.carefast.careops.utils.ConvertDateUtils;
import com.carefast.careops.utils.JobPositionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

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


    public List<EmployeeProfileDTO> getEmployeeOperator(int employeeId, String projectCode, int shiftId) {

        Optional<InsysEmployeeModel> employeeExist = insysEmployeeRepository.findById(employeeId);

        if (employeeExist.get().getJob().getJobPositionId() == JobPositionUtils.TEAM_LEADER_CLEANING_SERVICE) {
            return filterEmployeeByJobPositionShitfSchedule(projectCode, shiftId, JobPositionUtils.CLEANING_SERVICE);
        } else if (employeeExist.get().getJob().getJobPositionId() == JobPositionUtils.TEAM_LEADER_GONDOLA){
            return filterEmployeeByJobPositionShitfSchedule(projectCode, shiftId, JobPositionUtils.GONDOLA);
        } else if (employeeExist.get().getJob().getJobPositionId() == JobPositionUtils.TEAM_LEADER_GARDENER) {
            return filterEmployeeByJobPositionShitfSchedule(projectCode, shiftId, JobPositionUtils.GARDENER);
        } else {
            return null;
        }
    }

    public List<EmployeeProfileDTO> filterEmployeeByJobPositionShitfSchedule(String projectCode, int shiftId, int JobId) {
        List<Optional<ScheduleModel>> scheduleModels = scheduleRepository.getAllCurrentScheduleByProjectCode(projectCode, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        List<Optional<ScheduleModel>> scheduleFilter = scheduleModels.stream().filter(s -> convertDateUtils.validateDate(s.get()) == shiftId).collect(Collectors.toList());

        List<EmployeeScheduleModel> employeeScheduleModels = new ArrayList<>();
        List<InsysEmployeeModel> employeeModel = new ArrayList<>();

        scheduleFilter.forEach(s -> {
            Optional<EmployeeScheduleModel> employeeScheduleModel = employeeScheduleRepository.getScheduleByScheduleId(projectCode, s.get().getScheduleId());
            employeeScheduleModels.add(employeeScheduleModel.get());
        });

        employeeScheduleModels.forEach(e -> {
            Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findById(e.getEmployeeId());
            employeeModel.add(employee.get());
        });

        return employeeModel.stream().filter(e -> e.getJob().getJobPositionId() == JobId)
                .map(this::convertEmployeeProfileDTO).collect(Collectors.toList());
    }

    public List<TeamOperatorDTO> getEmployeeTeam(String projectId, int employeeId, int shiftId) {

        List<PengawasPlottingModel> pengawasPlottingModel = pengawasPlottingRepository.getTeamByPlotting(employeeId, projectId, LocalDate.now());

        List<TeamOperatorDTO> teamOperatorDTO = new ArrayList<>();

        pengawasPlottingModel.forEach(pengawas -> {
            List<EmployeeDetailListProjectModel> employeeDetailListProjectModel = employeeProjectRepository.getAllEmployeeByPlottingId(pengawas.getPlottingId(), shiftId, LocalDate.now());

            employeeDetailListProjectModel.forEach(e -> {
                InsysEmployeeModel employee = insysEmployeeRepository.findByEmployeeIdProjectCode(e.getIdEmployee(), e.getIdProject()).get();
                ProjectSubLocationAreaModel place = projectSubLocationAreaRepository.findById(e.getIdPlotting()).get();
                String locationName = projectLocationRepository.findById(place.getIdLocation()).get().getLocationName();
                String subLocationName = projectSubLocationRepository.findById(place.getSubLocationId()).get().getSubLocationName();

                TeamOperatorDTO teamOperator = new TeamOperatorDTO();
                teamOperator.setEmployeeId(employee.getIdEmployee());
                teamOperator.setEmployeeNuc(employee.getEmployeeCode());
                teamOperator.setEmployeeName(employee.getEmployeeName());
                teamOperator.setEmployee_photo_profile(employee.getEmployeePhotoProfile());
                teamOperator.setLocationName(locationName);
                teamOperator.setSubLocationName(subLocationName);
                teamOperator.setStatusAttendance(e.getStatusAttendance());

                teamOperatorDTO.add(teamOperator);
            });
        });

        return teamOperatorDTO;
    }

    public List<TeamOperatorDTO> getEmployeeTeamByDate(String projectId, int employeeId, int shiftId, LocalDate date) {

        List<PengawasPlottingModel> pengawasPlottingModel = pengawasPlottingRepository.getTeamByDate(employeeId, projectId, shiftId, date);

        List<EmployeeDetailListProjectModel> employeeDetailListProjectModel = new ArrayList<>();

        pengawasPlottingModel.forEach(pengawas -> {
            Optional<EmployeeDetailListProjectModel> employeeProject = employeeProjectRepository.getEmployeeByPlottingId(pengawas.getPlottingId(), date);

            if (employeeProject.isPresent()) {
                employeeDetailListProjectModel.add(employeeProjectRepository.getEmployeeByPlottingId(pengawas.getPlottingId(), date).get());
            }
        });

        return employeeDetailListProjectModel.stream().map(this::convertProjectTeamDTONew).collect(Collectors.toList());
    }

    public List<TeamDTO> getTeamSpv(String projectId, int employeeId, int shiftId) {
        List<PengawasModel> pengawasModel = pengawasRepository.getAllLeader(employeeId, projectId, LocalDate.now());
        List<TeamDTO> teamDTO = new ArrayList<>();

        pengawasModel.forEach(pengawas -> {
            List<EmployeeDetailListProjectModel> employeeDetailListProjectModel = employeeProjectRepository.getLeaderByShift(pengawas.getLeaderId(), projectId, shiftId, LocalDate.now());

            employeeDetailListProjectModel.forEach(e -> {
                InsysEmployeeModel employee = insysEmployeeRepository.findByEmployeeIdProjectCode(e.getIdEmployee(), e.getIdProject()).get();

                TeamDTO teamSPV = new TeamDTO();
                teamSPV.setEmployeeId(e.getIdEmployee());
                teamSPV.setProjectCode(projectId);
                teamSPV.setEmployeeNuc(employee.getEmployeeCode());
                teamSPV.setEmployeeName(employee.getEmployeeName());
                teamSPV.setEmployee_photo_profile(employee.getEmployeePhotoProfile());
                teamSPV.setJobCode(employee.getJobCode());
                teamSPV.setJobName(employee.getJobName());
                teamSPV.setIdShift(e.getIdShift());
                teamSPV.setScheduleType(e.getScheduleType());
                teamSPV.setStatusAttendance(e.getStatusAttendance());

                teamDTO.add(teamSPV);
            });
        });

        return teamDTO;
    }

    public List<TeamDTO> getTeamChiefSpv(String projectId) {
//        List<PengawasModel> pengawasModel = pengawasRepository.getAllSpv(projectId, LocalDate.now());
        List<InsysEmployeeModel> pengawasModel = insysEmployeeRepository.findEmployeeSupervisorActive(projectId);
        List<TeamDTO> teamDTO = new ArrayList<>();

        pengawasModel.forEach(pengawas -> {
            List<EmployeeDetailListProjectModel> employeeDetailListProjectModel = employeeProjectRepository.getSupervisorSchedule(pengawas.getIdEmployee(), projectId, LocalDate.now());

            employeeDetailListProjectModel.forEach(e -> {
                InsysEmployeeModel employee = insysEmployeeRepository.findByEmployeeIdProjectCode(e.getIdEmployee(), e.getIdProject()).get();

                TeamDTO teamSPV = new TeamDTO();
                teamSPV.setEmployeeId(e.getIdEmployee());
                teamSPV.setProjectCode(projectId);
                teamSPV.setEmployeeNuc(employee.getEmployeeCode());
                teamSPV.setEmployeeName(employee.getEmployeeName());
                teamSPV.setEmployee_photo_profile(employee.getEmployeePhotoProfile());
                teamSPV.setJobCode(employee.getJobCode());
                teamSPV.setJobName(employee.getJobName());
                teamSPV.setIdShift(e.getIdShift());
                teamSPV.setScheduleType(e.getScheduleType());
                teamSPV.setStatusAttendance(e.getStatusAttendance());

                teamDTO.add(teamSPV);
            });
        });

        return teamDTO;
    }

    public EmployeeProfileDTO convertEmployeeProfileDTO(InsysEmployeeModel item) {
        return new EmployeeProfileDTO(
                item.getIdEmployee(),
                item.getEmployeeCode(),
                item.getEmployeeName(),
                item.getEmployeePhone(),
                item.getEmployeePhotoProfile(),
                item.getEmployeeEmail(),
                item.getEmployeeNik(),
                item.getJobCode(),
                item.getJobName(),
                convertJobPositionDTO(item.getJob()),
                item.getProjectCode(),
                item.getBeginDate(),
                item.getEndDate()
        );
    }

    public JobPositionDTO convertJobPositionDTO(JobModel item) {
        return new JobPositionDTO(
                item.getJobPositionId(),
                item.getCodePosition(),
                item.getNamaPosition(),
                item.getLevelPosition(),
                item.getPositionImage());
    }

    public TeamOperatorDTO convertProjectTeamDTONew(EmployeeDetailListProjectModel item) {

        InsysEmployeeModel employee = insysEmployeeRepository.findByEmployeeIdProjectCode(item.getIdEmployee(), item.getIdProject()).get();
        ProjectSubLocationAreaModel place = projectSubLocationAreaRepository.findById(item.getIdPlotting()).get();

        TeamOperatorDTO teamOperatorDTO = new TeamOperatorDTO();
        teamOperatorDTO.setEmployeeId(item.getIdEmployee());
        teamOperatorDTO.setEmployeeNuc(employee.getEmployeeCode());
        teamOperatorDTO.setEmployeeName(employee.getEmployeeName());
        teamOperatorDTO.setEmployee_photo_profile(employee.getEmployeePhotoProfile());
        teamOperatorDTO.setLocationName(projectLocationRepository.findById(place.getIdLocation()).get().getLocationName());
        teamOperatorDTO.setSubLocationName(projectSubLocationRepository.findById(place.getSubLocationId()).get().getSubLocationName());

        return teamOperatorDTO;
    }
}
