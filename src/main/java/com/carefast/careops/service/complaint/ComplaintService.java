package com.carefast.careops.service.complaint;

import com.carefast.careops.dto.attendance.AttendanceDTO;
import com.carefast.careops.dto.complaint.ComplaintDTO;
import com.carefast.careops.dto.complaint.CustomComplaintDTO;
import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.project.LocationDTO;
import com.carefast.careops.dto.project.SubLocationDTO;
import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.complaint.ComplaintModel;
import com.carefast.careops.model.complaint.CustomComplaintModel;
import com.carefast.careops.model.employee.EmployeeAttendanceModel;
import com.carefast.careops.model.project.ProjectLocationModel;
import com.carefast.careops.model.project.ProjectSubLocationAreaModel;
import com.carefast.careops.model.project.ProjectSubLocationModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.attendance.EmployeeAttendanceRepository;
import com.carefast.careops.repository.complaint.ComplaintRepository;
import com.carefast.careops.repository.project.ProjectLocationRepository;
import com.carefast.careops.repository.project.ProjectSubLocationAreaRepository;
import com.carefast.careops.repository.project.ProjectSubLocationRepository;
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
public class ComplaintService {
    public static final int COMPLAINT_COUNT = 10;

    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private ProjectLocationRepository projectLocationRepository;
    @Autowired
    private ProjectSubLocationRepository projectSubLocationRepository;
    @Autowired
    private ProjectSubLocationAreaRepository projectSubLocationAreaRepository;
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;

    public ComplaintDTO createComplaint(int userId, String projectId, String title, String description, int locationId, int subLocationId, MultipartFile file) {
        ComplaintModel complaint = new ComplaintModel();
        String fileName = fileUtils.generateComplaintImageName(userId, projectId, file.getOriginalFilename(), "COMPLAINT");

        complaint.setClientId(userId);
        complaint.setProjectId(projectId);
        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setImage(fileName);
        complaint.setLocationId(locationId);
        complaint.setSubLocationId(subLocationId);
        complaint.setStatusComplaint("WAITING");
        complaint.setDate(LocalDate.now());
        complaint.setCreatedAt(LocalDateTime.now());

        fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_COMPLAINT);
        return convertDTO(complaintRepository.save(complaint));
    }

    public ComplaintDTO carefastTeamcreateComplaint(int userId, String projectId, String title, String description, int locationId, int subLocationId, MultipartFile file) {
        ComplaintModel complaint = new ComplaintModel();
        String fileName = fileUtils.generateComplaintImageName(userId, projectId, file.getOriginalFilename(), "COMPLAINT");

        complaint.setCreatedByEmployeeId(userId);
        complaint.setProjectId(projectId);
        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setImage(fileName);
        complaint.setLocationId(locationId);
        complaint.setSubLocationId(subLocationId);
        complaint.setStatusComplaint("WAITING");
        complaint.setDate(LocalDate.now());
        complaint.setCreatedAt(LocalDateTime.now());

        fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_COMPLAINT);
        return convertDTO(complaintRepository.save(complaint));
    }

    public List<LocationDTO> getLocationProject(String projectId) {
        return projectLocationRepository.getLocationByProjectId(projectId).stream().
                map(this::convertLocationDTO).collect(Collectors.toList());
    }

    public List<SubLocationDTO> getSubLocationProject(String projectId, int locationId) {
        List<ProjectSubLocationAreaModel> projectSubLocationArea = projectSubLocationAreaRepository.getSubLocationByProjectAndLocation(projectId, locationId);
        List<ProjectSubLocationModel> subLocation = new ArrayList<>();

        projectSubLocationArea.forEach(p -> {
            subLocation.add(projectSubLocationRepository.findById(p.getSubLocationId()).get());
        });

        return subLocation.stream().map(this::convertSubLocationDTO).collect(Collectors.toList());
    }

//    public List<CustomComplaintDTO> getAllComplaint(int clientId, String projectId) {
//        return complaintRepository.getAllUserComplaint(clientId, projectId).
//                stream().map(this::convertCustomComplaintDTO).collect(Collectors.toList());
//    }

    public Slice<CustomComplaintDTO> getAllComplaint(int clientId, String projectId, int page) {
        Pageable pagination = PageRequest.of(page, COMPLAINT_COUNT);

        return complaintRepository.getAllUserComplaint(clientId, projectId, pagination).map(this::convertCustomComplaintDTO);
    }

    public Slice<CustomComplaintDTO> getAllComplaintByEmployeeId(int clientId, String projectId, int page) {
        Pageable pagination = PageRequest.of(page, COMPLAINT_COUNT);

        return complaintRepository.getAllComplaintByEmployeeId(clientId, projectId, pagination).map(this::convertCustomComplaintDTO);
    }

    public CustomComplaintDTO getDetailComplaint(int complaintId) {
        Optional<CustomComplaintModel> complaint = complaintRepository.getDetailComplaint(complaintId);

        if (complaint.isPresent()) {
            return convertCustomComplaintDTO(complaint.get());
        } else {
            return null;
        }
    }

    public Slice<CustomComplaintDTO> getComplaintByProjectCode(String projectCode, int page) {
        Pageable pagination = PageRequest.of(page, COMPLAINT_COUNT);

        return complaintRepository.getComplaintByProjectCode(projectCode, pagination).map(this::convertCustomComplaintDTO);
    }

    public ComplaintDTO setComplaintProcess(int complaintId, int employeeId) {
        Optional<ComplaintModel> complaint = complaintRepository.findById(complaintId);

        if (complaint.isPresent()) {
            complaint.get().setProcessBy(employeeId);
            complaint.get().setStatusComplaint("ON PROGRESS");

            return convertDTO(complaintRepository.save(complaint.get()));
        } else {
            return null;
        }
    }

    public List<ProfileDTO> getOperatorCleanerAttendanceIn(String projectCode) {
        List<EmployeeAttendanceModel> employees = employeeAttendanceRepository.getEmployeeAttendanceStatusIn(projectCode, LocalDate.now());
        List<InsysEmployeeModel> insysEmployeeModel = new ArrayList<>();

        employees.forEach(employee -> {
            insysEmployeeModel.add(insysEmployeeRepository.findById(employee.getEmployeeId()).get());
        });

        return insysEmployeeModel.stream().map(this::convertProfileDTO).collect(Collectors.toList());
    }

    public ComplaintDTO submitOperatorCsoComplaint(int complaintId, int workerId) {
        Optional<ComplaintModel> complaint = complaintRepository.findById(complaintId);

        if (complaint.isPresent()) {
            complaint.get().setWorkerId(workerId);

            return convertDTO(complaintRepository.save(complaint.get()));
        } else {
            return null;
        }
    }

    public ComplaintDTO convertDTO(ComplaintModel item) {
        return new ComplaintDTO(
                item.getComplaintId(),
                item.getClientId(),
                item.getCreatedByEmployeeId(),
                item.getProjectId(),
                item.getTitle(),
                item.getDescription(),
                item.getImage(),
                item.getLocationId(),
                item.getSubLocationId(),
                item.getProcessBy(),
                item.getWorkerId(),
                item.getBeforeImage(),
                item.getProcessImage(),
                item.getAfterImage(),
                item.getStatusComplaint(),
                item.getCreatedAt()
        );
    }

    public LocationDTO convertLocationDTO(ProjectLocationModel item) {
        return new LocationDTO(item.getLocationId(), item.getProjectId(), item.getLocationName());
    }

    public SubLocationDTO convertSubLocationDTO(ProjectSubLocationModel item) {
        return new SubLocationDTO(item.getSubLocationId(), item.getSubLocationName());
    }

    public CustomComplaintDTO convertCustomComplaintDTO(CustomComplaintModel item) {
        CustomComplaintDTO customComplaintDTO = new CustomComplaintDTO();

        if (item.getWorkerId() != null) {
            Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findById(item.getWorkerId());

            customComplaintDTO.setWorker(convertProfileDTO(employee.get()));
        } else {
            customComplaintDTO.setWorker(null);
        }

        customComplaintDTO.setComplaintId(item.getComplaintId());
        customComplaintDTO.setClientId(item.getClientId());
        customComplaintDTO.setClientName(item.getClientName());
        customComplaintDTO.setCreatedByEmployeeId(item.getCreatedByEmployeeId());
        customComplaintDTO.setCreatedByEmployeeName(item.getCreatedByEmployeeName());
        customComplaintDTO.setProjectId(item.getProjectId());
        customComplaintDTO.setTitle(item.getTitle());
        customComplaintDTO.setDescription(item.getDescription());
        customComplaintDTO.setImage(item.getImage());
        customComplaintDTO.setLocationId(item.getLocationId());
        customComplaintDTO.setLocationName(item.getLocationName());
        customComplaintDTO.setProcessBy(item.getProcessBy());
        customComplaintDTO.setWorkerId(item.getWorkerId());
        customComplaintDTO.setBeforeImage(item.getBeforeImage());
        customComplaintDTO.setProcessImage(item.getProcessImage());
        customComplaintDTO.setAfterImage(item.getAfterImage());
        customComplaintDTO.setStatusComplaint(item.getStatusComplaint());
        customComplaintDTO.setSubLocationId(item.getSubLocationId());
        customComplaintDTO.setSubLocationName(item.getSubLocationName());
        customComplaintDTO.setCreatedAt(item.getCreatedAt());
        customComplaintDTO.setDate(item.getDate());
        customComplaintDTO.setTime(item.getTime());

        return customComplaintDTO;
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

    public ProfileDTO convertProfileDTO(InsysEmployeeModel item) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setIdEmployee(item.getIdEmployee());
        profileDTO.setEmployeeNuc(item.getEmployeeCode());
        profileDTO.setEmployeeName(item.getEmployeeName());
        profileDTO.setEmployeePhoneNumber(item.getEmployeePhone());
        profileDTO.setEmployeeEmail(item.getEmployeeEmail());
        profileDTO.setEmployeeNik(item.getEmployeeNik());
        profileDTO.setEmployeePhotoProfile(item.getEmployeePhotoProfile());

        return profileDTO;
    }
}
