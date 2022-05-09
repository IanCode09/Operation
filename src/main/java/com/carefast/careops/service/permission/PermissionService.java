package com.carefast.careops.service.permission;

import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.permission.CustomPermissionDTO;
import com.carefast.careops.dto.permission.PermissionDTO;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.permission.CustomPermissionModel;
import com.carefast.careops.model.permission.PermissionModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.permission.PermissionRepository;
import com.carefast.careops.repository.pengawas.PengawasPlottingRepository;
import com.carefast.careops.utils.FileUtils;
import com.carefast.careops.utils.PathPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    public static final int PERMISSION_COUNT = 10;

    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PengawasPlottingRepository pengawasPlottingRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;

    public PermissionDTO employeeCreatePermission(int requestBy, String projectId, String title, String description, LocalDate date, MultipartFile file) {
        Optional<EmployeeDetailListProjectModel> schedule = employeeProjectRepository.getActualScheduleByDate(requestBy, projectId, date);
        Integer pengawasId = pengawasPlottingRepository.getEmployeePengawas(schedule.get().getIdPlotting(), projectId, LocalDate.now()).get().getEmployeeId();

        String fileName = fileUtils.generateImageName(requestBy, projectId, file.getOriginalFilename(), "IZIN");

        PermissionModel permission = new PermissionModel();
        permission.setRequestByEmployeeId(requestBy);
        permission.setApproveByEmployeeId(pengawasId);
        permission.setIdDetailEmployeeProject(schedule.get().getIdDetailEmployeeProject());
        permission.setProjectId(projectId);
        permission.setTitle(title);
        permission.setDescription(description);
        permission.setPlottingId(schedule.get().getIdPlotting());
        permission.setShiftId(schedule.get().getIdShift());
        permission.setAtDate(date);
        permission.setImage(fileName);
        permission.setStatusPermission("WAITING");
        permission.setCreatedAt(LocalDateTime.now());

        fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_PERMISSION);
        return convertPermissionDTO(permissionRepository.save(permission));
    }

    public Slice<CustomPermissionDTO> getAllPermission(int employeeId, String projectId, int page) {
        Pageable pagination = PageRequest.of(page, PERMISSION_COUNT);

        return permissionRepository.getAllPermission(employeeId, projectId, pagination)
                .map(this::convertCustomPermissionDTO);
    }

    public CustomPermissionDTO getPermissionDetail(int permissionId) {
        Optional<CustomPermissionModel> permission = permissionRepository.getPermissionDetail(permissionId);

        if (permission.isPresent()) {
            return convertCustomPermissionDTO(permission.get());
        } else {
            return null;
        }
    }

    public PermissionDTO convertPermissionDTO(PermissionModel item) {
        return new PermissionDTO(
                item.getPermissionId(),
                item.getRequestByEmployeeId(),
                item.getReplaceByEmployeeId(),
                item.getApproveByEmployeeId(),
                item.getIdDetailEmployeeProject(),
                item.getProjectId(),
                item.getTitle(),
                item.getDescription(),
                item.getPlottingId(),
                item.getShiftId(),
                item.getAtDate(),
                item.getImage(),
                item.getStatusPermission(),
                item.getProcessAt(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
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
}
