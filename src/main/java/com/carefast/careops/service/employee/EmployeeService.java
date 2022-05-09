package com.carefast.careops.service.employee;

import com.carefast.careops.dto.INSYS.response.InsysEmployeeDTO;
import com.carefast.careops.dto.activity.*;
import com.carefast.careops.dto.employee.response.EmployeeProjectDTO;
import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.employee.response.ProfileDetailDTO;
import com.carefast.careops.dto.job.JobPositionDTO;
import com.carefast.careops.dto.project.ProjectDTO;
import com.carefast.careops.dto.project.ProjectDTOInsys;
import com.carefast.careops.dto.project.ShiftDTO;
import com.carefast.careops.dto.schedule.ScheduleShiftDTO;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.INSYS.InsysProjectModel;
import com.carefast.careops.model.activity.ActivityModel;
import com.carefast.careops.model.job.JobModel;
import com.carefast.careops.model.pengawas.PengawasPlottingModel;
import com.carefast.careops.model.project.*;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.activity.ActivityRepository;
import com.carefast.careops.repository.employee.EmployeeInsysRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.employee.EmployeeRepository;
import com.carefast.careops.repository.job.JobRepository;
import com.carefast.careops.repository.pengawas.PengawasPlottingRepository;
import com.carefast.careops.repository.project.*;
import com.carefast.careops.utils.FileUtils;
import com.carefast.careops.utils.JobLevelValidate;
import com.carefast.careops.utils.MasterSplitUtils;
import com.carefast.careops.utils.PathPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public static final int CHANGE_PASSWORD_SUCCESS = 1;
    public static final int PASSWORD_UN_MATCH = 2;
    public static final int USER_NOT_FOUND = 3;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private ProjectSubLocationAreaRepository projectSubLocationAreaRepository;
    @Autowired
    private ProjectSubLocationActivityRepository projectSubLocationActivityRepository;
    @Autowired
    private EmployeeInsysRepository employeeInsysRepository;
    @Autowired
    private ProjectInsysRepository projectInsysRepository;
    @Autowired
    private JobLevelValidate jobLevelValidate;
    @Autowired
    private ProjectLocationRepository projectLocationRepository;
    @Autowired
    private ProjectSubLocationRepository projectSubLocationRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private PengawasPlottingRepository pengawasPlottingRepository;
    @Autowired
    private MasterSplitUtils masterSplitUtils;
    @Autowired
    private FileUtils fileUtils;
  

    public Optional<InsysEmployeeModel> findById(int id) {
        Optional<InsysEmployeeModel> userExists = insysEmployeeRepository.findById(id);

        return userExists;
    }

    public ProfileDetailDTO getEmployeeProfile(int employeeId) {
        Optional<InsysEmployeeModel> employeeProfile = employeeInsysRepository.findById(employeeId);

        if (employeeProfile.isEmpty()) {
            return null;
        }

        return convertProfileDetailDTO(employeeProfile.get());
    }

    public EmployeeProjectDTO getEmployeeProjects(int employeeId) {
        List<EmployeeDetailListProjectModel> employeeProjects = employeeProjectRepository.getAllProjects(employeeId);

        List<ProjectModel> projects = new ArrayList<>();
        List<ProjectDTO> projectDTO = new ArrayList<>();

        employeeProjects.forEach(employeeProject -> {
            List<ProjectModel> projectModel = projectRepository.findOne(employeeProject.getIdProject());
            projects.addAll(projectModel);
        });

        projectDTO.addAll(projects.stream().map(project -> convertProjectDTO(project)).collect(Collectors.toList()));

        EmployeeProjectDTO employeeProjectDTO = new EmployeeProjectDTO();
        employeeProjectDTO.setProjects(projectDTO);

        return employeeProjectDTO;
    }

    public DailyActivityDTO getEmployeeDailyActivity(int employeeId, String projectId) {

        Optional<EmployeeDetailListProjectModel> employeeDetailListProjectModel = employeeProjectRepository.getEmployeeProject(employeeId, projectId, LocalDate.now());

        if (employeeDetailListProjectModel.isPresent()) {
            Optional<ProjectSubLocationAreaModel> plotting = projectSubLocationAreaRepository.findById(employeeDetailListProjectModel.get().getIdPlotting());

            return convertDailyActivityDTO(plotting.get());
        } else {
            return null;
        }
    }
  
    public int changePassword(String email, String password, String confirmPassword) {
        Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findByEmail(email);

        if (employee.isPresent()) {
            if (password.equals(confirmPassword)) {
                String passwordEncoder = bCryptPasswordEncoder.encode(confirmPassword);

                employee.get().setEmployeePassword(passwordEncoder);
                employee.get().setEmployeePasswordShow(confirmPassword);

                insysEmployeeRepository.save(employee.get());

                return CHANGE_PASSWORD_SUCCESS;
            } else if (password != confirmPassword) {
                return PASSWORD_UN_MATCH;
            }
        }

        return USER_NOT_FOUND;
    }

    public InsysEmployeeDTO getEmployeeByNucNik(String nuc, String nik) {
        Optional<InsysEmployeeModel> employeeExists = insysEmployeeRepository.findByNucNik(nuc, nik);

        if (employeeExists.isPresent()) {
            return convertInsysEmployeeDTO(employeeExists.get());
        }

        return null;
    };

    public int createPassword(String email, String nuc, String password, String confirmPassword) {
        Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findByNucEmail(nuc, email);

        if (employee.isPresent()) {
            if (password.equals(confirmPassword)) {
                String passwordEncoder = bCryptPasswordEncoder.encode(confirmPassword);

                employee.get().setEmployeePassword(passwordEncoder);
                employee.get().setEmployeePasswordShow(confirmPassword);
                employee.get().setIsRegisSuccess("Y");

                insysEmployeeRepository.save(employee.get());

                return CHANGE_PASSWORD_SUCCESS;
            } else if (password != confirmPassword) {
                return PASSWORD_UN_MATCH;
            }
        }

        return USER_NOT_FOUND;
    }

    public DacDTO getDetailDailyActivity(int employeeId, String projectId) {
        Optional<EmployeeDetailListProjectModel> employeeDetailListProjectModel = employeeProjectRepository.getEmployeeProject(employeeId, projectId, LocalDate.now());

        if (employeeDetailListProjectModel.isPresent()) {
            Optional<ProjectSubLocationAreaModel> plotting = projectSubLocationAreaRepository.findById(employeeDetailListProjectModel.get().getIdPlotting());
            Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findByEmployeeIdProjectCode(employeeId, projectId);

            DacDTO dacDTO = new DacDTO();
            dacDTO.setEmployee(convertDACEmployeeProfileDTO(employee.get()));
            dacDTO.setPlotting(convertDACEmployeePlottingDTO(plotting.get()));

            return dacDTO;
        } else {
            return null;
        }
    }

    public int gantiPassword(int employeeId, String oldPassword, String newPassword, String confirmPassword) {

        Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findById(employeeId);
        boolean isMatch = bCryptPasswordEncoder.matches(oldPassword, employee.get().getEmployeePassword());

        if (employee.isPresent() & isMatch) {
            if (newPassword.equals(confirmPassword)) {
                String passwordEncoder = bCryptPasswordEncoder.encode(confirmPassword);

                employee.get().setEmployeePassword(passwordEncoder);
                employee.get().setEmployeePasswordShow(confirmPassword);

                insysEmployeeRepository.save(employee.get());

                return CHANGE_PASSWORD_SUCCESS;
            } else if (newPassword != confirmPassword) {
                return PASSWORD_UN_MATCH;
            }
        }

        return USER_NOT_FOUND;
    }

    public ProfileDTO updateProfile (int employeeId, String employeeEmail, String employeePhone, MultipartFile file) {
        Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findById(employeeId);

        if (employee.isPresent()) {
            String fileName = fileUtils.generateImageProfile(employee.get().getEmployeeName(), employee.get().getEmployeeCode(), file.getOriginalFilename());
            fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_PROFILE);

            employee.get().setEmployeeEmail(employeeEmail);
            employee.get().setEmployeePhone(employeePhone);
            employee.get().setEmployeePhotoProfile(fileName);

            return convertProfileDTO(insysEmployeeRepository.save(employee.get()));
        } else {
            return null;
        }
    }

    public List<ScheduleShiftDTO> getEmployeeSchedule(int employeeId, String projectId, LocalDate date) {

        return employeeProjectRepository.getScheduleByDate(employeeId, projectId, date).stream().
                map(this::convertScheduleDTO).collect(Collectors.toList());
    }

    public ProfileDetailDTO convertProfileDetailDTO(InsysEmployeeModel item) {
        Optional<InsysProjectModel> employeeProject = projectInsysRepository.findByProjectCode(item.getProjectCode());
        JobPositionDTO jobPositionDTO = item.getJob() != null ? convertJobPositionDTO(item.getJob()) : null;

        return new ProfileDetailDTO(
                item.getIdEmployee(),
                item.getEmployeeCode(),
                item.getEmployeeName(),
                item.getEmployeePhone(),
                item.getEmployeeEmail(),
                item.getEmployeeNik(),
                item.getEmployeePhotoProfile(),
                item.getJobCode(),
                item.getJobName(),
                jobLevelValidate.jobLevel(item.getJobCode()),
                jobPositionDTO,
                convertProjectDTOInsys(employeeProject.get())
        );
    }

    public ProfileDTO convertProfileDTO(InsysEmployeeModel item) {
        return new ProfileDTO(
                item.getIdEmployee(),
                item.getEmployeeCode(),
                item.getEmployeeName(),
                item.getEmployeePhone(),
                item.getEmployeeEmail(),
                item.getEmployeeNik(),
                item.getEmployeePhotoProfile()
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

    public ProjectDTOInsys convertProjectDTOInsys(InsysProjectModel item) {
        return new ProjectDTOInsys(
                item.getIdProject(),
                item.getProjectCode(),
                item.getProjectName(),
                item.getBranchCode(),
                item.getBranchName(),
                item.getProjectAddress(),
                item.getLattitude(),
                item.getLongittude(),
                item.getRadius(),
                item.getStartDate(),
                item.getEndDate()
        );
    }

    public ProjectDTO convertProjectDTO(ProjectModel item) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(item.getProjectId());
        projectDTO.setProjectCode(item.getProjectCode());
        projectDTO.setProjectName(item.getProjectName());
        projectDTO.setStartAt(item.getStartAt());
        projectDTO.setEndAt(item.getEndAt());
        projectDTO.setBranchId(item.getBranchId());
        projectDTO.setStatusProject(item.getStatusProject());

        return projectDTO;
    }

    public DailyActivityDTO convertDailyActivityDTO(ProjectSubLocationAreaModel item) {
        DailyActivityDTO dailyActivityDTO = new DailyActivityDTO();

        PengawasPlottingModel pengawas = pengawasPlottingRepository.getEmployeePengawas(item.getIdSubLocationArea(), item.getIdProject(), LocalDate.now()).get();
        List<DailyActivityDetailDTO> dailyActivityDetailDTO = new ArrayList<>();

        List<ProjectSubLocationActivityModel> activites = projectSubLocationActivityRepository.getActivities(
                item.getIdProject(), item.getIdLocation(), item.getSubLocationId(), item.getShiftId());

        dailyActivityDetailDTO.addAll(activites.stream().map(activity -> convertDailyActivityDetailDTO(activity)).collect(Collectors.toList()));

        dailyActivityDTO.setEmployeePengawasName(insysEmployeeRepository.findById(pengawas.getEmployeeId()).get().getEmployeeName());
        dailyActivityDTO.setDate(LocalDate.now());
        dailyActivityDTO.setShiftDescription(shiftRepository.findById(item.getShiftId()).get().getShiftDescription());
        dailyActivityDTO.setCodePlottingArea(item.getCodePlottingArea());
        dailyActivityDTO.setLocationName(projectLocationRepository.findById(item.getIdLocation()).get().getLocationName());
        dailyActivityDTO.setSubLocationName(projectSubLocationRepository.findById(item.getSubLocationId()).get().getSubLocationName());
        dailyActivityDTO.setDailyActivities(dailyActivityDetailDTO);

        return dailyActivityDTO;
    }

    public DailyActivityDetailDTO convertDailyActivityDetailDTO(ProjectSubLocationActivityModel item) {
        DailyActivityDetailDTO dailyActivityDetailDTO = new DailyActivityDetailDTO();

        Optional<ProjectLocationModel> projectLocationModel = projectLocationRepository.findById(item.getLocationId());
        Optional<ProjectSubLocationModel> projectSubLocationModel = projectSubLocationRepository.findById(item.getSubLocationId());
        Optional<ShiftModel> shiftModel = shiftRepository.findById(item.getShitfId());

        String machine = item.getMachineId() == null ? null : masterSplitUtils.master(item.getMachineId(), MasterSplitUtils.MACHINE);
        String tool = item.getToolsId() == null ? null : masterSplitUtils.master(item.getToolsId(), MasterSplitUtils.TOOL);
        String chemical = item.getChemicalId() == null ? null : masterSplitUtils.master(item.getChemicalId(), MasterSplitUtils.CHEMICAL);

        dailyActivityDetailDTO.setProjectId(item.getProjectId());
        dailyActivityDetailDTO.setLocationName(projectLocationModel.get().getLocationName());
        dailyActivityDetailDTO.setSubLocationName(projectSubLocationModel.get().getSubLocationName());
        dailyActivityDetailDTO.setObjectId(item.getObjectId());
        dailyActivityDetailDTO.setShiftDescription(shiftModel.get().getShiftDescription());
        dailyActivityDetailDTO.setStartAt(item.getStartAt());
        dailyActivityDetailDTO.setEndAt(item.getEndAt());
        dailyActivityDetailDTO.setMachineName(machine);
        dailyActivityDetailDTO.setToolName(tool);
        dailyActivityDetailDTO.setChemicalName(chemical);
        dailyActivityDetailDTO.setActivity(item.getActivityId());

        return dailyActivityDetailDTO;
    }

    public ActivityDTO convertActivityDTO(ActivityModel item) {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityId(item.getActivityId());
        activityDTO.setActivityName(item.getActivityName());
        activityDTO.setEstimation(item.getEstimation());
        activityDTO.setFrequency(item.getFrequency());

        return activityDTO;
    }

    public InsysEmployeeDTO convertInsysEmployeeDTO(InsysEmployeeModel item) {
        InsysEmployeeDTO insysEmployeeDTO = new InsysEmployeeDTO(
                item.getIdEmployee(),
                item.getEmployeeCode(),
                item.getEmployeeName(),
                item.getEmployeePhone(),
                item.getEmployeeEmail(),
                item.getEmployeeNik(),
                item.getJobCode(),
                item.getJobName(),
                item.getProjectCode(),
                item.getBeginDate(),
                item.getEndDate(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );

        return insysEmployeeDTO;
    }

    public DACEmployeeProfileDTO convertDACEmployeeProfileDTO (InsysEmployeeModel item) {

        DACEmployeeProfileDTO dacEmployeeProfileDTO = new DACEmployeeProfileDTO();
        dacEmployeeProfileDTO.setEmployeeId(item.getIdEmployee());
        dacEmployeeProfileDTO.setEmployeeCode(item.getEmployeeCode());
        dacEmployeeProfileDTO.setEmployeeName(item.getEmployeeName());
        dacEmployeeProfileDTO.setJobCode(item.getJobCode());
        dacEmployeeProfileDTO.setJobName(item.getJobName());

        return dacEmployeeProfileDTO;
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

    public ScheduleShiftDTO convertScheduleDTO (EmployeeDetailListProjectModel item) {
        ScheduleShiftDTO schedule = new ScheduleShiftDTO();
        schedule.setIdDetailEmployeeProject(item.getIdDetailEmployeeProject());
        schedule.setEmployeeId(item.getIdEmployee());
        schedule.setProjectId(item.getIdProject());
        schedule.setLocationId(item.getIdLocation());
        schedule.setPlottingId(item.getIdPlotting());
        schedule.setShift(convertShiftDTO(shiftRepository.findById(item.getIdShift()).get()));
        schedule.setDate(item.getDate());
        schedule.setScheduleType(item.getScheduleType());
        schedule.setAssignBy(item.getAssignBy());
        schedule.setStatus(item.getStatus());

        return schedule;
    }

    public ShiftDTO convertShiftDTO(ShiftModel item) {
        return new ShiftDTO(item.getShiftId(), item.getShiftName(), item.getShiftDescription());
    }
}
