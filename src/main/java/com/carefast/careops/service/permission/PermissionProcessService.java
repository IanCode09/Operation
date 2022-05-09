package com.carefast.careops.service.permission;

import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.permission.CustomPermissionDTO;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.overtime.OvertimeModel;
import com.carefast.careops.model.permission.CustomPermissionModel;
import com.carefast.careops.model.permission.PermissionModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.overtime.OvertimeRepository;
import com.carefast.careops.repository.permission.PermissionRepository;
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
public class PermissionProcessService {
    public static final int PERMISSION_COUNT = 10;

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private OvertimeRepository overtimeRepository;

    public Slice<CustomPermissionDTO> getAllPermissionProcess(int employeeId, String projectId, int page) {
        Pageable pagination = PageRequest.of(page, PERMISSION_COUNT);

        return permissionRepository.getAllPermissionProcessApproveByEmployeeId(employeeId, projectId, pagination)
                .map(this::convertCustomPermissionDTO);
    }

    public List<ProfileDTO> getEmployeeReplace(String projectId, LocalDate date) {
        List<EmployeeDetailListProjectModel> employee = employeeProjectRepository.getEmployeeReplace(projectId, date);
        List<ProfileDTO> profileDTO = new ArrayList<>();

        employee.forEach(e -> {
            profileDTO.addAll(insysEmployeeRepository.getDetailEmployeeCleaner(e.getIdEmployee()).stream().map(this::convertProfileDTO).collect(Collectors.toList()));
        });

        return profileDTO;
    }

    public CustomPermissionDTO permissionRefuse(int permissionId) {
        Optional<PermissionModel> permission = permissionRepository.findById(permissionId);

        if (permission.isPresent()) {
            permission.get().setStatusPermission("REFUSE");
            permission.get().setProcessAt(LocalDateTime.now());
            permissionRepository.save(permission.get());

            return convertCustomPermissionDTO(permissionRepository.getPermissionDetail(permissionId).get());
        } else {
            return null;
        }
    }

    public CustomPermissionDTO permissionAccept(int permissionId, int employeeApproveId, int employeeReplaceId, String projectId, LocalDate date) {
        Optional<PermissionModel> permission = permissionRepository.findById(permissionId);

        if (permission.isPresent()) {
            permission.get().setStatusPermission("ACCEPT");
            permission.get().setProcessAt(LocalDateTime.now());
            permission.get().setReplaceByEmployeeId(employeeReplaceId);
            permissionRepository.save(permission.get());

            Optional<EmployeeDetailListProjectModel> employee = employeeProjectRepository.findById(permission.get().getIdDetailEmployeeProject());
            employee.get().setStatusAttendance("IZIN");
            employeeProjectRepository.save(employee.get());

            Optional<EmployeeDetailListProjectModel> employeeReplace = employeeProjectRepository.getEmployeeScheduleLibur(employeeReplaceId, projectId, date);
            employeeReplace.get().setIdLocation(employee.get().getIdLocation());
            employeeReplace.get().setIdPlotting(employee.get().getIdPlotting());
            employeeReplace.get().setIdShift(employee.get().getIdShift());
            employeeReplace.get().setScheduleType("LEMBUR GANTI");
            employeeReplace.get().setIsOff("N");
            employeeReplace.get().setStatusAttendance("BELUM_ABSEN");
            employeeProjectRepository.save(employeeReplace.get());

            OvertimeModel overtimeModel = new OvertimeModel();
            overtimeModel.setEmployeeId(permission.get().getReplaceByEmployeeId());
            overtimeModel.setEmployeeReplaceId(permission.get().getRequestByEmployeeId());
            overtimeModel.setCreatedByEmployeeId(permission.get().getApproveByEmployeeId());
            overtimeModel.setIdDetailEmployeeProject(employeeReplace.get().getIdDetailEmployeeProject());
            overtimeModel.setIdDetailEmployeeProjectReplace(permission.get().getIdDetailEmployeeProject());
            overtimeModel.setProjectId(permission.get().getProjectId());
            overtimeModel.setTitle(permission.get().getTitle());
            overtimeModel.setDescription(permission.get().getDescription());
            overtimeModel.setPlottingId(permission.get().getPlottingId());
            overtimeModel.setShiftId(permission.get().getShiftId());
            overtimeModel.setAtDate(permission.get().getAtDate());
            overtimeModel.setImage(permission.get().getImage());
            overtimeModel.setStatus(permission.get().getStatusPermission());
            overtimeModel.setProcessAt(permission.get().getProcessAt());
            overtimeModel.setPermissionId(permission.get().getPermissionId());
            overtimeModel.setIsPermission("Y");
            overtimeModel.setCreatedAt(permission.get().getCreatedAt());
            overtimeRepository.save(overtimeModel);

            return convertCustomPermissionDTO(permissionRepository.getPermissionDetail(permissionId).get());
        } else {
            return null;
        }
    }

    public CustomPermissionDTO convertCustomPermissionDTO(CustomPermissionModel item) {
        String employeeCode = null;
        String employeeName = null;
        String employeePhotoProfile = null;

        if (item.getReplaceByEmployeeId() != null) {
            Optional<InsysEmployeeModel> employeeReplace = insysEmployeeRepository.findById(item.getReplaceByEmployeeId());
            employeeCode = employeeReplace.get().getEmployeeCode();
            employeeName = employeeReplace.get().getEmployeeName();
            employeePhotoProfile = employeeReplace.get().getEmployeePhotoProfile();
        }

        return new CustomPermissionDTO(
                item.getPermissionId(),
                item.getEmployeeId(),
                item.getEmployeeCode(),
                item.getEmployeeName(),
                item.getEmployeePhotoProfile(),
                item.getReplaceByEmployeeId(),
                employeeCode,
                employeeName,
                employeePhotoProfile,
                item.getApproveByEmployeeId(),
                item.getIdDetailEmployeeProject(),
                item.getProjectId(),
                item.getStatusPermission(),
                item.getTitle(),
                item.getDescription(),
                item.getPlottingId(),
                item.getAtDate(),
                item.getShiftDescription(),
                item.getStartAt(),
                item.getEndAt(),
                item.getLocationName(),
                item.getSubLocationName(),
                item.getImage(),
                item.getCreatedAt()
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
}
