package com.carefast.careops.service.overtime;

import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.overtime.CustomOvertimeGantiDTO;
import com.carefast.careops.dto.overtime.OvertimeGantiDTO;
import com.carefast.careops.dto.project.EmployeeDetailListProjectDTO;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.overtime.CustomOvertimeGantiModel;
import com.carefast.careops.model.overtime.OvertimeModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.model.project.ShiftModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.overtime.OvertimeRepository;
import com.carefast.careops.repository.project.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OvertimeGantiService {
    public static final int OVERTIME_COUNT = 10;

    @Autowired
    private OvertimeRepository overtimeRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private ShiftRepository shiftRepository;

    public Slice<CustomOvertimeGantiDTO> getAllOvertimeGanti(int employeeId, String projectId, int page) {
        Pageable pagination = PageRequest.of(page, OVERTIME_COUNT);

        return overtimeRepository.getAllOvertimeGanti(employeeId, projectId, pagination)
                .map(this::convertCustomOvertimeGantiDTO);
    }

    public CustomOvertimeGantiDTO getOvertimeGantiDetail(int overtimeGantiId) {
        Optional<CustomOvertimeGantiModel> overtime = overtimeRepository.getOvertimeGantiDetail(overtimeGantiId);

        if (overtime.isPresent()) {
            return convertCustomOvertimeGantiDTO(overtime.get());
        } else {
            return null;
        }
    }

    public OvertimeGantiDTO createOvertimeGanti(int createdById, int employeeId, int employeeReplaceId, String projectId, String title, String description, LocalDate date, int shiftId) {

        Optional<EmployeeDetailListProjectModel> employeeReplaceSchedule = employeeProjectRepository.getActualScheduleByDate(employeeReplaceId, projectId, date);
        Optional<EmployeeDetailListProjectModel> employee = employeeProjectRepository.getEmployeeScheduleLibur(employeeId, projectId, date);

        OvertimeModel overtimeModel = new OvertimeModel();
        overtimeModel.setEmployeeId(employeeId);
        overtimeModel.setEmployeeReplaceId(employeeReplaceId);
        overtimeModel.setCreatedByEmployeeId(createdById);
        overtimeModel.setIdDetailEmployeeProject(employee.get().getIdDetailEmployeeProject());
        overtimeModel.setIdDetailEmployeeProjectReplace(employeeReplaceSchedule.get().getIdDetailEmployeeProject());
        overtimeModel.setProjectId(projectId);
        overtimeModel.setTitle(title);
        overtimeModel.setDescription(description);
        overtimeModel.setPlottingId(employeeReplaceSchedule.get().getIdPlotting());
        overtimeModel.setShiftId(shiftId);
        overtimeModel.setAtDate(date);
        overtimeModel.setStatus("ACCEPT");
        overtimeModel.setProcessAt(LocalDateTime.now());
        overtimeModel.setIsPermission("N");
        overtimeModel.setCreatedAt(LocalDateTime.now());

        return convertOvertimeGantiDTO(overtimeRepository.save(overtimeModel));
    }

    public List<ProfileDTO> getEmployeeGanti(String projectId, LocalDate date) {
        List<EmployeeDetailListProjectModel> employee = employeeProjectRepository.getAllEmployeeActualSchedule(projectId, date);
        List<ProfileDTO> profileDTO = new ArrayList<>();

        employee.forEach(e -> {
            profileDTO.addAll(insysEmployeeRepository.getDetailEmployeeCleaner(e.getIdEmployee()).stream().map(this::convertProfileDTO).collect(Collectors.toList()));
        });

        return profileDTO;
    }

    public EmployeeDetailListProjectDTO getShift(String projectId, int employeeId, LocalDate date) {
        Optional<EmployeeDetailListProjectModel> employeeProject = employeeProjectRepository.getActualScheduleByDate(employeeId, projectId, date);

        if (employeeProject.isPresent()) {
            return convertEmployeeDetailListProjectDTO(employeeProject.get());
        } else
            return null;
    }

    public List<ProfileDTO> getEmployeeReplace(String projectId, LocalDate date) {
        List<EmployeeDetailListProjectModel> employee = employeeProjectRepository.getEmployeeReplace(projectId, date);
        List<ProfileDTO> profileDTO = new ArrayList<>();

        employee.forEach(e -> {
            profileDTO.addAll(insysEmployeeRepository.getDetailEmployeeCleaner(e.getIdEmployee()).stream().map(this::convertProfileDTO).collect(Collectors.toList()));
        });

        return profileDTO;
    }

    public CustomOvertimeGantiDTO convertCustomOvertimeGantiDTO(CustomOvertimeGantiModel item) {
        return new CustomOvertimeGantiDTO(
                item.getOvertimeId(), item.getEmployeeId(), item.getEmployeeCode(), item.getEmployeeName(), item.getEmployeePhotoProfile(),
                item.getEmployeeReplaceId(), item.getEmployeeReplaceCode(), item.getEmployeeReplaceName(), item.getEmployeeReplacePhotoProfile(),
                item.getCreatedByEmployeeId(), item.getEmployeeCodeCreated(), item.getEmployeeNameCreated(), item.getEmployeePhotoProfileCreated(),
                item.getIdDetailEmployeeProject(), item.getProjectId(), item.getTitle(), item.getDescription(),
                item.getPlottingId(), item.getShiftDescription(), item.getAtDate(), item.getStartAt(),
                item.getEndAt(), item.getLocationName(), item.getSubLocationName(), item.getImage(),
                item.getStatus(), item.getPermissionId(), item.getIsPermission(), item.getCreatedAt()
        );
    }

    public OvertimeGantiDTO convertOvertimeGantiDTO(OvertimeModel item) {
        return new OvertimeGantiDTO(
                item.getOvertimeId(), item.getEmployeeId(), item.getEmployeeReplaceId(), item.getCreatedByEmployeeId(),
                item.getIdDetailEmployeeProject(), item.getProjectId(), item.getTitle(), item.getDescription(),
                item.getPlottingId(), item.getShiftId(), item.getAtDate(), item.getImage(), item.getStatus(),
                item.getProcessAt(), item.getPermissionId(), item.getIsPermission(), item.getCreatedAt(), item.getUpdatedAt()
        );
    }

    public ProfileDTO convertProfileDTO(InsysEmployeeModel item) {
        return new ProfileDTO(
                item.getIdEmployee(), item.getEmployeeCode(), item.getEmployeeName(), item.getEmployeePhone(),
                item.getEmployeeEmail(), item.getEmployeeNik(), item.getEmployeePhotoProfile()
        );
    }

    public EmployeeDetailListProjectDTO convertEmployeeDetailListProjectDTO(EmployeeDetailListProjectModel item) {
        Optional<ShiftModel> shift = shiftRepository.findById(item.getIdShift());

        return new EmployeeDetailListProjectDTO(
                item.getIdDetailEmployeeProject(), item.getIdProject(), item.getIdLocation(), item.getIdPlotting(),
                item.getIdShift(), shift.get().getShiftDescription(), item.getIdEmployee(), item.getDate(), item.getMonth(), item.getYear(),
                item.getScheduleType(), item.getIsOff(), item.getStatusAttendance(), item.getAssignBy(),
                item.getStatus(), item.getChecklistByEmployee(), item.getIsDone(), item.getDoneAt()
        );
    }
}
