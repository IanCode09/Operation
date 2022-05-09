package com.carefast.careops.service.attendance;

import com.carefast.careops.dto.attendance.AttendanceDTO;
import com.carefast.careops.dto.attendance.AttendanceNonOperatorStatusDTO;
import com.carefast.careops.model.employee.EmployeeAttendanceModel;
import com.carefast.careops.model.project.ProjectShiftModel;
import com.carefast.careops.model.schedule.SchedulePengawasModel;
import com.carefast.careops.repository.attendance.EmployeeAttendanceRepository;
import com.carefast.careops.repository.project.ProjectShiftRepository;
import com.carefast.careops.repository.schedule.ScheduleLeaderRepository;
import com.carefast.careops.utils.ConvertDateUtils;
import com.carefast.careops.utils.FileUtils;
import com.carefast.careops.utils.PathPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class GeoAttendanceService {
    private final static String TYPE_SCAN_OUT = "out";
    private final static String TYPE_SCAN_IN = "in";
    public final String PLEASE_WAIT = "PLEASE_WAIT";
    public final String SCAN_AVAILABLE = "SCAN_AVAILABLE";

    @Autowired
    private ScheduleLeaderRepository scheduleLeaderRepository;
    @Autowired
    private ConvertDateUtils convertDateUtils;
    @Autowired
    private ProjectShiftRepository projectShiftRepository;
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;
    @Autowired
    private FileUtils fileUtils;

    public AttendanceDTO employeeAttendanceInGeo(int employeeId, String projectCode, MultipartFile file) {
        Optional<SchedulePengawasModel> schedule = scheduleLeaderRepository.getScheduleLeader(employeeId, projectCode, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        int shiftToday = convertDateUtils.validateDate(schedule.get());

        ProjectShiftModel shift = projectShiftRepository.getDetailShiftByProjectId(projectCode, shiftToday).get();
        System.out.println(shiftToday);

        LocalTime lastScan = shift.getStartAt().plusMinutes(shift.getToleransiTime()); // LATE AFTER BATAS TOLERANSI
        String fileName = fileUtils.generateAttendanceImageName(employeeId, projectCode, file.getOriginalFilename(), TYPE_SCAN_IN);
        String isLate = LocalTime.now().isAfter(lastScan) ? "Y" : "N";

        EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
        employeeAttendanceModel.setEmployeeId(employeeId);
        employeeAttendanceModel.setProjectCode(projectCode);
        employeeAttendanceModel.setIdScheduleLeader(schedule.get().getIdSchedulePengawas());
        employeeAttendanceModel.setAttendanceType("SENDIRI");
        employeeAttendanceModel.setScanIn(LocalDateTime.now());
        employeeAttendanceModel.setEmployeeImgSelfieIn(fileName);
        employeeAttendanceModel.setIsLate(isLate);
        employeeAttendanceModel.setCreatedAt(LocalDate.now());

        fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_SELFIE);

        return convertAttendanceDTO(employeeAttendanceRepository.save(employeeAttendanceModel));
    }

    public AttendanceDTO employeeAttendanceOutGeo(int employeeId, String projectCode, MultipartFile file) {
        Optional<SchedulePengawasModel> schedule = scheduleLeaderRepository.getScheduleLeader(employeeId, projectCode, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        int shiftToday = convertDateUtils.validateDate(schedule.get());
        Optional<EmployeeAttendanceModel> attendance = employeeAttendanceRepository.getLastAttendanceIn(employeeId, projectCode, LocalDate.now());

       ProjectShiftModel shift = projectShiftRepository.getDetailShiftByProjectId(projectCode, shiftToday).get();
        System.out.println(shiftToday);

        if (LocalTime.now().isAfter(shift.getEndAt())) {
            String fileName = fileUtils.generateAttendanceImageName(employeeId, projectCode, file.getOriginalFilename(), TYPE_SCAN_OUT);

            attendance.get().setScanOut(LocalDateTime.now());
            attendance.get().setEmployeeImgSelfieOut(fileName);

            fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_SELFIE);
            return convertAttendanceDTO(employeeAttendanceRepository.save(attendance.get()));
        } else {
            return null;
        }
    }

    public AttendanceNonOperatorStatusDTO getEmployeeNonOperatorAttendanceStatus(int employeeId, String projectCode) {
        Optional<SchedulePengawasModel> schedule = scheduleLeaderRepository.getScheduleLeader(employeeId, projectCode, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        int shiftToday = convertDateUtils.validateDate(schedule.get());
        System.out.println(shiftToday);

        Optional<ProjectShiftModel> shiftModel = projectShiftRepository.getDetailShiftByProjectId(projectCode, shiftToday);

        AttendanceNonOperatorStatusDTO attendanceNonOperatorStatusDTO = new AttendanceNonOperatorStatusDTO();
        attendanceNonOperatorStatusDTO.setEmployeeId(employeeId);
        attendanceNonOperatorStatusDTO.setProjectCode(projectCode);
        attendanceNonOperatorStatusDTO.setIdScheduleLeader(schedule.get().getIdSchedulePengawas());
        attendanceNonOperatorStatusDTO.setShiftId(shiftToday);
        attendanceNonOperatorStatusDTO.setStartAt(shiftModel.get().getStartAt());
        attendanceNonOperatorStatusDTO.setEndAt(shiftModel.get().getEndAt());

        if ((shiftToday != 4)) {
            Optional<EmployeeAttendanceModel> employeeAttendance = employeeAttendanceRepository.getEmployeeNonOperatorStatusAttendanceSchedule(employeeId, projectCode, schedule.get().getIdSchedulePengawas(), LocalDate.now());

            if (employeeAttendance.isEmpty()) {
                attendanceNonOperatorStatusDTO.setStatusAttendance("Belum absen");
            } else if (employeeAttendance.get().getScanOut() != null) {
                attendanceNonOperatorStatusDTO.setStatusAttendance("Selesai");
            } else if (employeeAttendance.isPresent()) {
                attendanceNonOperatorStatusDTO.setStatusAttendance("Bertugas");
            }

            return attendanceNonOperatorStatusDTO;

        } else if (shiftToday == 4) {
            attendanceNonOperatorStatusDTO.setStatusAttendance("Off Day");
            attendanceNonOperatorStatusDTO.setStartAt(null);
            attendanceNonOperatorStatusDTO.setEndAt(null);

            return attendanceNonOperatorStatusDTO;
        } else {
            return null;
        }
    }

    public String employeeNonOperatorAttendanceCheckInGeo(int employeeId, String projectCode) {
        Optional<SchedulePengawasModel> schedule = scheduleLeaderRepository.getScheduleLeader(employeeId, projectCode, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        int shiftToday = convertDateUtils.validateDate(schedule.get());
        System.out.println(shiftToday);

        ProjectShiftModel projectShift = projectShiftRepository.getDetailShiftByProjectId(projectCode, shiftToday).get();
        LocalTime startScan = projectShift.getStartAt().minusMinutes(60); // START SCAN 60 MINUTE BEFORE ACTUAL SCHEDULE
        System.out.println(startScan);

        if (LocalTime.now().isBefore(startScan)) {
            return PLEASE_WAIT;
        } else {
            return SCAN_AVAILABLE;
        }
    }

    public String employeeNonOperatorAttendanceCheckOutGeo(int employeeId, String projectCode) {
        Optional<SchedulePengawasModel> schedule = scheduleLeaderRepository.getScheduleLeader(employeeId, projectCode, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        int shiftToday = convertDateUtils.validateDate(schedule.get());
        System.out.println(shiftToday);

        ProjectShiftModel shift = projectShiftRepository.getDetailShiftByProjectId(projectCode, shiftToday).get();

        if (LocalTime.now().isAfter(shift.getEndAt())) {
            return SCAN_AVAILABLE;
        } else {
            return PLEASE_WAIT;
        }
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
