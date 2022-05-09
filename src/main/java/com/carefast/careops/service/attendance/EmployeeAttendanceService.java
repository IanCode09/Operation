package com.carefast.careops.service.attendance;

import com.carefast.careops.dto.attendance.AttendanceDTO;
import com.carefast.careops.dto.attendance.EmployeeAttendanceBelumAbsenDTO;
import com.carefast.careops.dto.attendance.EmployeeAttendanceSudahAbsenDTO;
import com.carefast.careops.dto.attendance.ProfileSudahAbsenDTO;
import com.carefast.careops.dto.employee.response.EmployeeAttendanceDTO;
import com.carefast.careops.dto.employee.response.EmployeeAttendanceInfoDTO;
import com.carefast.careops.dto.employee.response.EmployeeProfileDTO;
import com.carefast.careops.dto.job.JobPositionDTO;
import com.carefast.careops.dto.team.TeamDTO;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.barcode.EmployeeBarcodeModel;
import com.carefast.careops.model.employee.EmployeeAttendanceModel;
import com.carefast.careops.model.job.JobModel;
import com.carefast.careops.model.pengawas.PengawasModel;
import com.carefast.careops.model.pengawas.PengawasPlottingModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.model.project.ProjectShiftModel;
import com.carefast.careops.model.project.ProjectSubLocationAreaModel;
import com.carefast.careops.model.schedule.EmployeeScheduleModel;
import com.carefast.careops.model.schedule.ScheduleModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.INSYS.InsysProjectRepository;
import com.carefast.careops.repository.barcode.EmployeeBarcodeRepository;
import com.carefast.careops.repository.attendance.EmployeeAttendanceRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.employee.EmployeeRepository;
import com.carefast.careops.repository.pengawas.PengawasPlottingRepository;
import com.carefast.careops.repository.pengawas.PengawasRepository;
import com.carefast.careops.repository.project.ProjectShiftRepository;
import com.carefast.careops.repository.project.ProjectSubLocationAreaRepository;
import com.carefast.careops.repository.schedule.EmployeeScheduleRepository;
import com.carefast.careops.repository.schedule.ScheduleRepository;
import com.carefast.careops.utils.ConvertDateUtils;
import com.carefast.careops.utils.FileUtils;
import com.carefast.careops.utils.PathPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeAttendanceService {

    private final static String TYPE_SCAN_OUT = "out";
    private final static String TYPE_SCAN_IN = "in";
    private Path PATH_PHOTO_SELFIE = Paths.get("./assets/attendance_photo_selfie");
    public final int PLEASE_WAIT = 100;
    public final int SCAN_AVAILABLE = 200;
    public final int SCAN_NOT_AVAILABLE = 300;
    public final int USER_NOT_FOUND = 400;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;
    @Autowired
    private EmployeeBarcodeRepository employeeBarcodeRepository;
    @Autowired
    private EmployeeScheduleRepository employeeScheduleRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ConvertDateUtils convertDateUtils;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private PengawasPlottingRepository pengawasPlottingRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private ProjectSubLocationAreaRepository projectSubLocationAreaRepository;
    @Autowired
    private ProjectShiftRepository projectShiftRepository;
    @Autowired
    private InsysProjectRepository insysProjectRepository;
    @Autowired
    private PengawasRepository pengawasRepository;

    public boolean employeeAttendanceIn(int employeeId, String projectCode, String barcodeKey, MultipartFile file) {
        Optional<InsysEmployeeModel> employeeModel = insysEmployeeRepository.findById(employeeId);
        Optional<EmployeeBarcodeModel> employeeBarcode = employeeBarcodeRepository.getEmployeeBarcodeByBarcodeKey(employeeId, barcodeKey);

        LocalTime startScan = employeeBarcode.get().getCheckIn().minusMinutes(60); // START SCAN 60 MINUTE BEFORE ACTUAL SCHEDULE
        LocalTime lastScan =  employeeBarcode.get().getCheckIn().plusMinutes(16); // LAST SCAN 15 MINUTE AFTER ACTUAL SCHEDULE

        if (employeeModel.isPresent() && (LocalTime.now().isAfter(startScan) && LocalTime.now().isBefore(lastScan)) ) {

            String fileName = fileUtils.generateAttendanceImageName(employeeId, employeeModel.get().getProjectCode(), file.getOriginalFilename(), TYPE_SCAN_IN);
            String isLate = employeeBarcode.get().getCheckIn().isBefore(LocalTime.now()) ? "Y" : "N";

            EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
            employeeAttendanceModel.setEmployeeId(employeeId);
            employeeAttendanceModel.setProjectCode(projectCode);
            employeeAttendanceModel.setAttendanceType("SENDIRI");
            employeeAttendanceModel.setBarcodeKey(barcodeKey);
            employeeAttendanceModel.setScanIn(LocalDateTime.now());
            employeeAttendanceModel.setEmployeeImgSelfieIn(fileName);
            employeeAttendanceModel.setIsLate(isLate);
            employeeAttendanceModel.setCreatedAt(LocalDate.now());

            fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_SELFIE);
            employeeAttendanceRepository.save(employeeAttendanceModel);

            return true;
        } else {
            return false;
        }
    }

    public boolean employeeAttendanceOut(int employeeId, String projectCode, String barcodeKey, MultipartFile file) {
        Optional<InsysEmployeeModel> employeeModel = insysEmployeeRepository.findById(employeeId);
        Optional<EmployeeAttendanceModel> employeeAttendanceModel = employeeAttendanceRepository.checkAttendance(employeeId, projectCode, barcodeKey);
        Optional<EmployeeBarcodeModel> employeeBarcode = employeeBarcodeRepository.getEmployeeBarcodeByBarcodeKey(employeeId, barcodeKey);

        LocalTime lastScan =  employeeBarcode.get().getCheckOut().plusMinutes(60); // LAST SCAN 15 MINUTE AFTER ACTUAL SCHEDULE

        if ((employeeModel.isPresent() || employeeAttendanceModel.isPresent()) && (LocalTime.now().isAfter(employeeBarcode.get().getCheckOut()) && LocalTime.now().isBefore(lastScan))) {
            String fileName = fileUtils.generateAttendanceImageName(employeeId, employeeModel.get().getProjectCode(), file.getOriginalFilename(), TYPE_SCAN_OUT);

            employeeAttendanceModel.get().setScanOut(LocalDateTime.now());
            employeeAttendanceModel.get().setEmployeeImgSelfieOut(fileName);

            fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_SELFIE);
            employeeAttendanceRepository.save(employeeAttendanceModel.get());

            return true;
        } else {
            return false;
        }
    }

    public EmployeeAttendanceBelumAbsenDTO employeeBelumAbsen(int employeeId, String projectCode, int shiftId) {
        List<PengawasPlottingModel> pengawasPlottingModel =  pengawasPlottingRepository.getTeamByPlottingAndShift(employeeId, projectCode, shiftId, LocalDate.now());
        List<EmployeeDetailListProjectModel> employeeNotAbsent = new ArrayList<>();
        List<InsysEmployeeModel> employee = new ArrayList<>();
        List<Integer> plottingsId = new ArrayList<>();

        pengawasPlottingModel.forEach(p -> {
            plottingsId.add(p.getPlottingId());

            List<EmployeeDetailListProjectModel> employeeBelumAbsen = employeeProjectRepository.getEmployeeBelumAbsen(p.getPlottingId(), LocalDate.now());
            employeeNotAbsent.addAll(employeeBelumAbsen);
        });

        int employeeCount = employeeProjectRepository.countEmployeeByPlotting(plottingsId, LocalDate.now());

        employeeNotAbsent.forEach(e -> {
            Optional<InsysEmployeeModel> employeeExists = insysEmployeeRepository.findById(e.getIdEmployee());
            employee.add(employeeExists.get());
        });

        EmployeeAttendanceBelumAbsenDTO employeeAttendanceBelumAbsenDTO = new EmployeeAttendanceBelumAbsenDTO();

        employeeAttendanceBelumAbsenDTO.setEmployeeId(employeeId);
        employeeAttendanceBelumAbsenDTO.setProjectCode(projectCode);
        employeeAttendanceBelumAbsenDTO.setCountEmployee(employeeCount);
        employeeAttendanceBelumAbsenDTO.setCountEmployeeBelumAbsen(employeeNotAbsent.size());
        employeeAttendanceBelumAbsenDTO.setEmployeeBelumAbsen(employee.stream().map(this::convertEmployeeProfileDTO).collect(Collectors.toList()));

        return employeeAttendanceBelumAbsenDTO;
    }

    public EmployeeAttendanceBelumAbsenDTO getSPVTeamEmployeeBelumAbsen(int employeeId, String projectCode, int shiftId) {
        List<PengawasModel> pengawasModel = pengawasRepository.getAllLeader(employeeId, projectCode, LocalDate.now());
        List<EmployeeDetailListProjectModel> employeeNotAbsent = new ArrayList<>();
        List<Integer> leaderId = new ArrayList<>();
        List<InsysEmployeeModel> employee = new ArrayList<>();

        pengawasModel.forEach(pengawas -> {
            leaderId.add(pengawas.getLeaderId());

            Optional<EmployeeDetailListProjectModel> employeeBelumAbsen = employeeProjectRepository.getEmployeeNonOperatorBelumAbsen(pengawas.getLeaderId(), shiftId, LocalDate.now());

            if (employeeBelumAbsen.isPresent()) {
                employeeNotAbsent.add(employeeBelumAbsen.get());
            }
        });

        int leaderCount = employeeProjectRepository.countLeaderByDate(leaderId, LocalDate.now());

        employeeNotAbsent.forEach(e -> {
            Optional<InsysEmployeeModel> employeeExists = insysEmployeeRepository.findById(e.getIdEmployee());
            employee.add(employeeExists.get());
        });

        EmployeeAttendanceBelumAbsenDTO employeeAttendanceBelumAbsenDTO = new EmployeeAttendanceBelumAbsenDTO();

        employeeAttendanceBelumAbsenDTO.setEmployeeId(employeeId);
        employeeAttendanceBelumAbsenDTO.setProjectCode(projectCode);
        employeeAttendanceBelumAbsenDTO.setCountEmployee(leaderCount);
        employeeAttendanceBelumAbsenDTO.setCountEmployeeBelumAbsen(employeeNotAbsent.size());
        employeeAttendanceBelumAbsenDTO.setEmployeeBelumAbsen(employee.stream().map(this::convertEmployeeProfileDTO).collect(Collectors.toList()));

        return employeeAttendanceBelumAbsenDTO;
    }

    public EmployeeAttendanceBelumAbsenDTO getChiefSupervisorTeamBelumAbsen(int employeeId, String projectCode, int shiftId) {
        List<EmployeeDetailListProjectModel> SpvBelumAbsen = employeeProjectRepository.getEmployeeSPVBelumAbsen(projectCode, shiftId, LocalDate.now());
        List<InsysEmployeeModel> employee = new ArrayList<>();

        SpvBelumAbsen.forEach(spv -> {
            Optional<InsysEmployeeModel> employeeExists = insysEmployeeRepository.findById(spv.getIdEmployee());
            employee.add(employeeExists.get());
        });

        int countSPVByDate = employeeProjectRepository.countSPVByDate(projectCode, shiftId, LocalDate.now());

        EmployeeAttendanceBelumAbsenDTO employeeAttendanceBelumAbsenDTO = new EmployeeAttendanceBelumAbsenDTO();
        employeeAttendanceBelumAbsenDTO.setEmployeeId(employeeId);
        employeeAttendanceBelumAbsenDTO.setProjectCode(projectCode);
        employeeAttendanceBelumAbsenDTO.setCountEmployee(countSPVByDate);
        employeeAttendanceBelumAbsenDTO.setCountEmployeeBelumAbsen(SpvBelumAbsen.size());
        employeeAttendanceBelumAbsenDTO.setEmployeeBelumAbsen(employee.stream().map(this::convertEmployeeProfileDTO).collect(Collectors.toList()));

        return employeeAttendanceBelumAbsenDTO;
    }

    public EmployeeAttendanceSudahAbsenDTO employeeSudahAbsen(int employeeId, String projectCode, int shiftId) {
        List<PengawasPlottingModel> pengawasPlottingModel =  pengawasPlottingRepository.getTeamByPlottingAndShift(employeeId, projectCode, shiftId, LocalDate.now());
        List<EmployeeDetailListProjectModel> employeeScanIn = new ArrayList<>();
        List<EmployeeAttendanceModel> employee = new ArrayList<>();

        pengawasPlottingModel.forEach(p -> {
            List<EmployeeDetailListProjectModel> employeeSudahAbsen = employeeProjectRepository.getEmployeeSudahAbsen(p.getPlottingId(), LocalDate.now());
            employeeScanIn.addAll(employeeSudahAbsen);
        });

        employeeScanIn.forEach(e -> {
            Optional<EmployeeAttendanceModel> attendanceInfo = employeeAttendanceRepository.getAttendanceInfoByScheduleId(e.getIdDetailEmployeeProject());
            employee.add(attendanceInfo.get());
        });

        EmployeeAttendanceSudahAbsenDTO employeeAttendanceSudahAbsenDTO = new EmployeeAttendanceSudahAbsenDTO();
        employeeAttendanceSudahAbsenDTO.setEmployeeSudahAbsen(employee.stream().map(this::convertProfileSudahAbsenDTO).collect(Collectors.toList()));

        return employeeAttendanceSudahAbsenDTO;
    }

    public EmployeeAttendanceSudahAbsenDTO getSPVTeamEmployeeSudahAbsen(int employeeId, String projectCode, int shiftId) {
        List<PengawasModel> pengawasModel = pengawasRepository.getAllLeader(employeeId, projectCode, LocalDate.now());
        List<EmployeeDetailListProjectModel> employeeScanIn = new ArrayList<>();
        List<EmployeeAttendanceModel> employee = new ArrayList<>();

        pengawasModel.forEach(pengawas -> {
            Optional<EmployeeDetailListProjectModel> employeeAbsen = employeeProjectRepository.getEmployeeNonOperatorSudahAbsen(pengawas.getLeaderId(), shiftId, LocalDate.now());

            if (employeeAbsen.isPresent()) {
                employeeScanIn.add(employeeAbsen.get());
            }
        });

        employeeScanIn.forEach(e -> {
            Optional<EmployeeAttendanceModel> attendanceInfo = employeeAttendanceRepository.getAttendanceInfoByScheduleId(e.getIdDetailEmployeeProject());
            employee.add(attendanceInfo.get());
        });

        EmployeeAttendanceSudahAbsenDTO employeeAttendanceSudahAbsenDTO = new EmployeeAttendanceSudahAbsenDTO();
        employeeAttendanceSudahAbsenDTO.setEmployeeSudahAbsen(employee.stream().map(this::convertProfileSudahAbsenDTO).collect(Collectors.toList()));

        return employeeAttendanceSudahAbsenDTO;
    }

    public EmployeeAttendanceSudahAbsenDTO getChiefSupervisorTeamSudahAbsen(String projectCode, int shiftId) {
        List<EmployeeDetailListProjectModel> SpvBelumAbsen = employeeProjectRepository.getEmployeeSPVSudahAbsen(projectCode, shiftId, LocalDate.now());
        List<EmployeeAttendanceModel> employee = new ArrayList<>();

        SpvBelumAbsen.forEach(spv -> {
            Optional<EmployeeAttendanceModel> employeeExists = employeeAttendanceRepository.getAttendanceInfoByScheduleId(spv.getIdDetailEmployeeProject());

            if (employeeExists.isPresent()) {
                employee.add(employeeExists.get());
            }
        });

        EmployeeAttendanceSudahAbsenDTO employeeAttendanceSudahAbsenDTO = new EmployeeAttendanceSudahAbsenDTO();
        employeeAttendanceSudahAbsenDTO.setEmployeeSudahAbsen(employee.stream().map(this::convertProfileSudahAbsenDTO).collect(Collectors.toList()));

        return employeeAttendanceSudahAbsenDTO;
    }

    public boolean employeeAttendanceInByLeader(int leaderId, int employeeId, String projectCode, String barcodeKey, MultipartFile file) {
        Optional<InsysEmployeeModel> employeeModel = insysEmployeeRepository.findById(employeeId);
        Optional<EmployeeBarcodeModel> employeeBarcode = employeeBarcodeRepository.getEmployeeBarcodeByBarcodeKey(employeeId, barcodeKey);

        LocalTime startScan = employeeBarcode.get().getCheckIn().minusMinutes(60); // START SCAN 60 MINUTE BEFORE ACTUAL SCHEDULE
        LocalTime lastScan =  employeeBarcode.get().getCheckIn().plusMinutes(16); // LAST SCAN 15 MINUTE AFTER ACTUAL SCHEDULE

        if (employeeModel.isPresent() && (LocalTime.now().isAfter(startScan) && LocalTime.now().isBefore(lastScan)) ) {

            String fileName = fileUtils.generateAttendanceImageName(employeeId, employeeModel.get().getProjectCode(), file.getOriginalFilename(), TYPE_SCAN_IN);
            String isLate = employeeBarcode.get().getCheckIn().isBefore(LocalTime.now()) ? "Y" : "N";

            EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
            employeeAttendanceModel.setEmployeeId(employeeId);
            employeeAttendanceModel.setProjectCode(projectCode);
            employeeAttendanceModel.setAttendanceType("DIABSENKAN");
            employeeAttendanceModel.setAttendanceBy(leaderId);
            employeeAttendanceModel.setBarcodeKey(barcodeKey);
            employeeAttendanceModel.setScanIn(LocalDateTime.now());
            employeeAttendanceModel.setEmployeeImgSelfieIn(fileName);
            employeeAttendanceModel.setIsLate(isLate);
            employeeAttendanceModel.setCreatedAt(LocalDate.now());

            fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_SELFIE);
            employeeAttendanceRepository.save(employeeAttendanceModel);

            return true;
        } else {
            return false;
        }
    }

    public EmployeeAttendanceInfoDTO employeeAttendanceInfo(int employeeId, String projectCode) {

        Optional<EmployeeScheduleModel> scheduleModel = employeeScheduleRepository.getEmployeeSchedule(employeeId, projectCode, LocalDate.now());

        if (scheduleModel.isPresent()) {
            Optional<ScheduleModel> scheduleExist = scheduleRepository.findByScheduleId(scheduleModel.get().getScheduleId());

            int result = convertDateUtils.validateDate(scheduleExist.get());

            if (result == 4) {
                EmployeeAttendanceInfoDTO employeeAttendanceInfo = new EmployeeAttendanceInfoDTO();
                employeeAttendanceInfo.setEmployeeId(employeeId);
                employeeAttendanceInfo.setProjectCode(projectCode);
                employeeAttendanceInfo.setStatusAttendance("Off");
                employeeAttendanceInfo.setAttendanceInfo(null);

                return employeeAttendanceInfo;
            } else {
                Optional<EmployeeAttendanceModel> employeeAttendance = employeeAttendanceRepository.getAttendanceInfo(employeeId, projectCode, LocalDate.now());

                if (employeeAttendance.isPresent()) {
                    EmployeeAttendanceDTO employeeAttendanceDTO = convertEmployeeAttendanceDTO(employeeAttendance.get());

                    String statusEmployeeAtt = employeeAttendance.get().getScanOut() == null ? "Bertugas" : "Selesai";
                    String statusEmployeeAttOut = employeeAttendance.get().getScanOut() == null ? "Belum absen" : "Sudah absen";

                    EmployeeAttendanceInfoDTO employeeAttendanceInfo = new EmployeeAttendanceInfoDTO();
                    employeeAttendanceInfo.setEmployeeId(employeeId);
                    employeeAttendanceInfo.setProjectCode(projectCode);
                    employeeAttendanceInfo.setStatusAttendance(statusEmployeeAtt);
                    employeeAttendanceInfo.setStatusAttendanceIn("Sudah absen");
                    employeeAttendanceInfo.setStatusAttendanceOut(statusEmployeeAttOut);
                    employeeAttendanceInfo.setAttendanceInfo(employeeAttendanceDTO);

                    return employeeAttendanceInfo;
                } else if (employeeAttendance.isEmpty()) {
                    EmployeeAttendanceInfoDTO employeeAttendanceInfo = new EmployeeAttendanceInfoDTO();
                    employeeAttendanceInfo.setEmployeeId(employeeId);
                    employeeAttendanceInfo.setProjectCode(projectCode);
                    employeeAttendanceInfo.setStatusAttendance("Belum Absen");
                    employeeAttendanceInfo.setStatusAttendanceIn("Belum absen");
                    employeeAttendanceInfo.setStatusAttendanceOut("Belum absen");
                    employeeAttendanceInfo.setAttendanceInfo(null);

                    return employeeAttendanceInfo;
                }
            }
        }

        return  null;
    }

    public List<EmployeeAttendanceDTO> getAllEmployeeAtt(int employeeId, String projectCode) {
        return employeeAttendanceRepository.findAll(employeeId, projectCode).stream()
                .map(this::convertEmployeeAttendanceDTO).collect(Collectors.toList());
    }

    public List<EmployeeAttendanceDTO> getEmployeeAttByDate(int employeeId, String projectCode, LocalDate datePrefix, LocalDate dateSuffix) {
        return employeeAttendanceRepository.getAttendanceByDate(employeeId, projectCode, datePrefix, dateSuffix).stream()
                .map(this::convertEmployeeAttendanceDTO).collect(Collectors.toList());
    }

    public int employeeAttendanceCheckIn(int employeeId, String projectCode) {

        Optional<EmployeeDetailListProjectModel> employee = employeeProjectRepository.getEmployeeProject(employeeId, projectCode, LocalDate.now());

        if (employee.isPresent()) {
            ProjectSubLocationAreaModel plotting = projectSubLocationAreaRepository.findById(employee.get().getIdPlotting()).get();
            ProjectShiftModel projectShift = projectShiftRepository.getDetailShiftByProjectId(projectCode, plotting.getShiftId()).get();

            LocalTime startScan = projectShift.getStartAt().minusMinutes(60); // START SCAN 60 MINUTE BEFORE ACTUAL SCHEDULE

            if (LocalTime.now().isBefore(startScan)) {
                return PLEASE_WAIT;
            } else {
                return SCAN_AVAILABLE;
            }
        } else {
            return USER_NOT_FOUND;
        }
    }

    public int employeeAttendanceCheckOut(int employeeId, String projectCode) {

        Optional<EmployeeDetailListProjectModel> employee = employeeProjectRepository.getEmployeeProject(employeeId, projectCode, LocalDate.now());

        if (employee.isPresent()) {
            ProjectSubLocationAreaModel plotting = projectSubLocationAreaRepository.findById(employee.get().getIdPlotting()).get();
            ProjectShiftModel projectShift = projectShiftRepository.getDetailShiftByProjectId(projectCode, plotting.getShiftId()).get();

            LocalTime lastScan = projectShift.getEndAt().minusMinutes(60);

            if (LocalTime.now().isBefore(projectShift.getEndAt())) {
                return PLEASE_WAIT;
            } else if (LocalTime.now().isAfter(lastScan)) {
                return SCAN_NOT_AVAILABLE;
            } else {
                return SCAN_AVAILABLE;
            }
        } else {
            return USER_NOT_FOUND;
        }
    }

    public AttendanceDTO getAttendance(int employeeId, String projectCode) {
        Optional<EmployeeAttendanceModel> attendance = employeeAttendanceRepository.getAttendanceInfo(employeeId, projectCode);

        if (attendance.isPresent()) {
            if (LocalDateTime.now().isAfter(attendance.get().getScanOutAt().plusHours(8))) {
                return null;
            } else {
                return convertAttendanceDTO(attendance.get());
            }
        } else {
            return null;
        }
    }

    public AttendanceDTO getDetailAttendance(int scheduleId) {
        Optional<EmployeeAttendanceModel> attendance = employeeAttendanceRepository.getAttendanceInfoByScheduleId(scheduleId);

        if (attendance.isPresent()) {
            return convertAttendanceDTO(attendance.get());
        } else {
            return null;
        }
    }

    public EmployeeAttendanceDTO convertEmployeeAttendanceDTO(EmployeeAttendanceModel item) {
        EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();

        employeeAttendanceDTO.setAttendanceId(item.getAttendanceId());
        employeeAttendanceDTO.setEmployeeId(item.getEmployeeId());
        employeeAttendanceDTO.setProjectCode(item.getProjectCode());
        employeeAttendanceDTO.setProjectName(insysProjectRepository.findByProjectCode(item.getProjectCode()).get().getProjectName());
        employeeAttendanceDTO.setBarcodeKey(item.getBarcodeKey());
        employeeAttendanceDTO.setScanIn(item.getScanIn());
        employeeAttendanceDTO.setEmployeeImgSelfieIn(item.getEmployeeImgSelfieIn());
        employeeAttendanceDTO.setScanOut(item.getScanOut());
        employeeAttendanceDTO.setEmployeeImgSelfieOut(item.getEmployeeImgSelfieOut());
        employeeAttendanceDTO.setIsLate(item.getIsLate());
        employeeAttendanceDTO.setCreatedAt(item.getCreatedAt());

        return employeeAttendanceDTO;
    }

    public EmployeeProfileDTO convertEmployeeProfileDTO(InsysEmployeeModel item) {
        JobPositionDTO jobPositionDTO = item.getJob() != null ? convertJobPositionDTO(item.getJob()) : null;

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
                jobPositionDTO,
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

    public ProfileSudahAbsenDTO convertProfileSudahAbsenDTO (EmployeeAttendanceModel item) {
        Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findById(item.getEmployeeId());

        return new ProfileSudahAbsenDTO(
                employee.get().getIdEmployee(),
                employee.get().getEmployeeCode(),
                employee.get().getEmployeeName(),
                employee.get().getEmployeePhone(),
                employee.get().getEmployeePhotoProfile(),
                employee.get().getProjectCode(),
                convertAttendanceDTO(item)
        );
    }

    public AttendanceDTO convertAttendanceDTO(EmployeeAttendanceModel item) {
        return new AttendanceDTO(
                item.getAttendanceId(),
                item.getEmployeeId(),
                item.getProjectCode(),
                item.getIdDetailEmployeeProject(),
                item.getIdScheduleLeader(),
                item.getAttendanceType(),
                item.getAttendanceBy(),
                item.getBarcodeKey(),
                item.getScanOutAt(),
                item.getScanIn(),
                item.getEmployeeImgSelfieIn(),
                item.getScanOut(),
                item.getEmployeeImgSelfieOut(),
                item.getIsLate()
        );
    }
}
