package com.carefast.careops.service.attendance;

import com.carefast.careops.dto.attendance.AttendanceDTO;
import com.carefast.careops.dto.attendance.AttendanceStatusDTO;
import com.carefast.careops.dto.attendance.ScheduleDTO;
import com.carefast.careops.model.employee.CustomEmployeeSchedule;
import com.carefast.careops.model.employee.EmployeeAttendanceModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.model.project.ProjectShiftModel;
import com.carefast.careops.repository.attendance.EmployeeAttendanceRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.project.ProjectShiftRepository;
import com.carefast.careops.utils.FileUtils;
import com.carefast.careops.utils.PathPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeoAttendanceOperatorService {

    private final static String TYPE_SCAN_OUT = "out";
    private final static String TYPE_SCAN_IN = "in";
    public final String PLEASE_WAIT = "PLEASE_WAIT";
    public final String LATE_SIXTY_MINUTES = "LATE_SIXTY_MINUTES";
    public final String SCAN_AVAILABLE = "SCAN_AVAILABLE";
    public final String SCAN_NOT_AVAILABLE = "SCAN_NOT_AVAILABLE";
    public final String DAC_NOT_CHECKED = "DAC_NOT_CHECKED";
    public final String IS_OVER_EIGHT_HOURS = "IS_OVER_EIGHT_HOURS";

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private ProjectShiftRepository projectShiftRepository;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;

    public AttendanceDTO employeeAttendanceInGeo(int employeeId, String projectCode, int scheduleId, MultipartFile file) {

        CustomEmployeeSchedule schedule = employeeProjectRepository.getDetailEmployeeSchedule(scheduleId).get();
        ProjectShiftModel shift = projectShiftRepository.getDetailShiftByProjectId(projectCode, schedule.getIdShift()).get();

        LocalTime startScan = shift.getStartAt().minusMinutes(120); // START SCAN 120 MINUTE BEFORE ACTUAL SCHEDULE
        LocalTime lastScan = shift.getStartAt().plusMinutes(shift.getToleransiTime()); // LATE AFTER BATAS TOLERANSI

        if (LocalTime.now().isAfter(startScan)) {
            LocalDateTime dateTimeEnd = shift.getEndAt().atDate(LocalDate.now());
            boolean isBefore = LocalTime.now().isBefore(shift.getStartAt());
            LocalDateTime scanOutAt = isBefore ? dateTimeEnd : LocalDateTime.now().plusHours(8);

            String fileName = fileUtils.generateAttendanceImageName(employeeId, schedule.getProjectCode(), file.getOriginalFilename(), TYPE_SCAN_IN);
            String isLate = LocalTime.now().isAfter(lastScan) ? "Y" : "N";

            EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
            employeeAttendanceModel.setEmployeeId(employeeId);
            employeeAttendanceModel.setJobCode(schedule.getJobCode());
            employeeAttendanceModel.setProjectCode(projectCode);
            employeeAttendanceModel.setIdDetailEmployeeProject(scheduleId);
            employeeAttendanceModel.setAttendanceType("SENDIRI");
            employeeAttendanceModel.setScanOutAt(scanOutAt);
            employeeAttendanceModel.setScanIn(LocalDateTime.now());
            employeeAttendanceModel.setEmployeeImgSelfieIn(fileName);
            employeeAttendanceModel.setIsLate(isLate);
            employeeAttendanceModel.setCreatedAt(LocalDate.now());

            fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_SELFIE);

            // UPDATE tab_employee_detail_list_project SET STATUS ATTENDANCE TO SEDANG_BEKERJA
            Optional<EmployeeDetailListProjectModel> employeeSchedule = employeeProjectRepository.findById(scheduleId);
            employeeSchedule.get().setStatusAttendance("SEDANG_BEKERJA");
            employeeProjectRepository.save(employeeSchedule.get());

            return convertAttendanceDTO(employeeAttendanceRepository.save(employeeAttendanceModel));
        } else {
            return null;
        }
    }

    public AttendanceDTO employeeAttendanceOutGeo(int employeeId, String projectCode, int scheduleId, MultipartFile file) {
        Optional<EmployeeDetailListProjectModel> employeeSchedule = employeeProjectRepository.findById(scheduleId);
        EmployeeDetailListProjectModel schedule = employeeProjectRepository.findById(scheduleId).get();
        EmployeeAttendanceModel attendance = employeeAttendanceRepository.getEmployeeAttendance(employeeId, schedule.getIdProject(), scheduleId).get();

        boolean isChecklistByEmployee = schedule.getChecklistByEmployee().equals("Y");
        boolean isScanOutAvailable = LocalDateTime.now().isAfter(attendance.getScanOutAt());

        if (isScanOutAvailable && isChecklistByEmployee) {
            String fileName = fileUtils.generateAttendanceImageName(employeeId, projectCode, file.getOriginalFilename(), TYPE_SCAN_OUT);
            fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_SELFIE);

            System.out.println(fileName);

            attendance.setScanOut(LocalDateTime.now());
            attendance.setEmployeeImgSelfieOut(fileName);

            // UPDATE tab_employee_detail_list_project SET STATUS ATTENDANCE TO HADIR
            employeeSchedule.get().setStatusAttendance("HADIR");
            employeeProjectRepository.save(employeeSchedule.get());

            return  convertAttendanceDTO(employeeAttendanceRepository.save(attendance));
        } else {
            return null;
        }
    }

    public String employeeAttendanceCheckInGeo(int scheduleId) {
        Optional<EmployeeDetailListProjectModel> schedule = employeeProjectRepository.findById(scheduleId);

        ProjectShiftModel projectShift = projectShiftRepository.getDetailShiftByProjectId(schedule.get().getIdProject(), schedule.get().getIdShift()).get();
        LocalTime startScan = projectShift.getStartAt().minusMinutes(120); // START SCAN 60 MINUTE BEFORE ACTUAL SCHEDULE
        LocalTime lastScan = projectShift.getStartAt().plusMinutes(60);
        System.out.println(startScan);

        if (LocalTime.now().isBefore(startScan)) {
            return PLEASE_WAIT;
        } else if (LocalTime.now().isAfter(lastScan)) {
            return LATE_SIXTY_MINUTES;
        } else {
            return SCAN_AVAILABLE;
        }
    }

    public String employeeAttendanceCheckOutGeo(int scheduleId, int employeeId) {
        EmployeeDetailListProjectModel schedule = employeeProjectRepository.findById(scheduleId).get();
        EmployeeAttendanceModel attendance = employeeAttendanceRepository.getEmployeeAttendance(employeeId, schedule.getIdProject(), scheduleId).get();

        boolean isChecklistByEmployee = schedule.getChecklistByEmployee().equals("Y");
        boolean isScanOutAvailable = LocalDateTime.now().isAfter(attendance.getScanOutAt());
        boolean isOverEightHours = LocalDateTime.now().isAfter(attendance.getScanOutAt().plusHours(8));
        boolean isCleaner = attendance.getJobCode().equals("CLEANER");

        if (isCleaner) {
            if (isOverEightHours) {
                return IS_OVER_EIGHT_HOURS;
            } else if (isScanOutAvailable && isChecklistByEmployee) {
                return SCAN_AVAILABLE;
            } else if (isScanOutAvailable && !isChecklistByEmployee) {
                return DAC_NOT_CHECKED;
            } else {
                return PLEASE_WAIT;
            }
        } else {
            if (isOverEightHours) {
                return IS_OVER_EIGHT_HOURS;
            } else if (isScanOutAvailable) {
                return SCAN_AVAILABLE;
            } else {
                return PLEASE_WAIT;
            }
        }
    }

    public AttendanceStatusDTO  getEmployeeAttendanceStatus(int employeeId, String projectCode) {
        List<EmployeeDetailListProjectModel> schedule = employeeProjectRepository.getScheduleByDate(employeeId, projectCode, LocalDate.now());
        List<EmployeeAttendanceModel> attendance = new ArrayList<>();

        schedule.forEach(s -> {
            Optional<EmployeeAttendanceModel> employeeAttendanceModel = employeeAttendanceRepository.getStatusAttendanceSchedule(employeeId, projectCode, s.getIdDetailEmployeeProject());

            if (employeeAttendanceModel.isPresent()) {
                attendance.add(employeeAttendanceModel.get());
            }
        });

        String statusAttendanceFirst = "";
        String statusAttendanceSecond = null;
        String statusAttendanceThird = null;
        String statusAttendanceFourth = null;

        if (schedule.size() == 1) {
            statusAttendanceFirst = getStatusAttendance(schedule.get(0).getStatusAttendance());
        } else if (schedule.size() == 2) {
            statusAttendanceFirst = getStatusAttendance(schedule.get(0).getStatusAttendance());
            statusAttendanceSecond = getStatusAttendance(schedule.get(1).getStatusAttendance());
        } else if (schedule.size() == 3) {
            statusAttendanceFirst = getStatusAttendance(schedule.get(0).getStatusAttendance());
            statusAttendanceSecond = getStatusAttendance(schedule.get(1).getStatusAttendance());
            statusAttendanceThird = getStatusAttendance(schedule.get(2).getStatusAttendance());
        } else if (schedule.size() == 4) {
            statusAttendanceFirst = getStatusAttendance(schedule.get(0).getStatusAttendance());
            statusAttendanceSecond = getStatusAttendance(schedule.get(1).getStatusAttendance());
            statusAttendanceThird = getStatusAttendance(schedule.get(2).getStatusAttendance());
            statusAttendanceFourth = getStatusAttendance(schedule.get(3).getStatusAttendance());
        }

        String status = "";
        if (schedule.get(0).getStatusAttendance().equals("LIBUR")) {
            status = "OFF DAY";
        } else if (schedule.get(0).getStatusAttendance().equals("BELUM_ABSEN")) {
            status = "BELUM ABSEN";
        } else {
            status = schedule.size() == attendance.size() ? "SELESAI" : "BERTUGAS";
        }

        AttendanceStatusDTO attendanceStatusDTO = new AttendanceStatusDTO();
        attendanceStatusDTO.setStatus(status);
        attendanceStatusDTO.setStatusAttendanceFirst(statusAttendanceFirst);
        attendanceStatusDTO.setStatusAttendanceSecond(statusAttendanceSecond);
        attendanceStatusDTO.setStatusAttendanceThird(statusAttendanceThird);
        attendanceStatusDTO.setStatusAttendanceFourth(statusAttendanceFourth);
        attendanceStatusDTO.setCountSchedule(schedule.size());
        attendanceStatusDTO.setIsDone(attendance.size());
        attendanceStatusDTO.setSchedule(schedule.stream().map(this::convertScheduleDTO).collect(Collectors.toList()));
        attendanceStatusDTO.setScheduleIsDone(attendance.stream().map(this::convertAttendanceDTO).collect(Collectors.toList()));

        return attendanceStatusDTO;
    }

    public String getStatusAttendance(String statusAttendance) {
        String status = "";

        if (statusAttendance.equals("LIBUR")) {
            status = "OFF DAY";
        } else if (statusAttendance.equals("BELUM_ABSEN")) {
            status = "BELUM ABSEN";
        } else if (statusAttendance.equals("SEDANG_BEKERJA")) {
            status = "BERTUGAS";
        } else if (statusAttendance.equals("HADIR") || statusAttendance.equals("LUPA_ABSEN")) {
            status = "SELESAI";
        }

        return status;
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

    public ScheduleDTO convertScheduleDTO(EmployeeDetailListProjectModel item ) {
        return new ScheduleDTO(
                item.getIdDetailEmployeeProject(),
                item.getIdEmployee(),
                item.getIdProject(),
                item.getIdLocation(),
                item.getIdPlotting(),
                item.getIdShift(),
                item.getDate(),
                item.getScheduleType(),
                item.getIsOff(),
                item.getStatusAttendance(),
                item.getAssignBy(),
                item.getStatus()
        );
    }
}
