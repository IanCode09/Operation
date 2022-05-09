package com.carefast.careops.service.complaint;

import com.carefast.careops.dto.complaint.ComplaintDTO;
import com.carefast.careops.model.complaint.ComplaintModel;
import com.carefast.careops.repository.complaint.ComplaintRepository;
import com.carefast.careops.utils.FileUtils;
import com.carefast.careops.utils.PathPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ComplaintUploadService {
    public final String BEFORE = "BEFORE";
    public final String PROGRESS = "PROGRESS";
    public final String AFTER = "AFTER";

    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private FileUtils fileUtils;

    public ComplaintDTO uploadComplaintProgress(int complaintId, int employeeId, MultipartFile file, String type) {

        Optional<ComplaintModel> complaint = complaintRepository.findById(complaintId);
        String fileName = fileUtils.generateComplaintProcessName(employeeId, complaint.get().getProjectId(), file.getOriginalFilename());

        if (complaint.isPresent()) {

            if (type == BEFORE) {
                complaint.get().setBeforeImage(fileName);
            } else if (type == PROGRESS) {
                complaint.get().setProcessImage(fileName);
            } else if (type == AFTER) {
                complaint.get().setAfterImage(fileName);
            }

            fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_COMPLAINT);
            return convertComplaintDTO(complaintRepository.save(complaint.get()));
        } else {
            return null;
        }
    };

    public ComplaintDTO complaintFinished(int complaintId, int employeeId) {
        Optional<ComplaintModel> complaint = complaintRepository.findById(complaintId);

        if (complaint.isPresent()) {
            complaint.get().setStatusComplaint("DONE");
            return convertComplaintDTO(complaintRepository.save(complaint.get()));
        } else {
            return null;
        }
    }

    public ComplaintDTO convertComplaintDTO(ComplaintModel item) {
        ComplaintDTO complaintDTO = new ComplaintDTO();
        complaintDTO.setComplaintId(item.getComplaintId());
        complaintDTO.setClientId(item.getClientId());
        complaintDTO.setCreatedByEmployeeId(item.getCreatedByEmployeeId());
        complaintDTO.setProjectId(item.getProjectId());
        complaintDTO.setTitle(item.getTitle());
        complaintDTO.setDescription(item.getDescription());
        complaintDTO.setImage(item.getImage());
        complaintDTO.setLocationId(item.getLocationId());
        complaintDTO.setSubLocationId(item.getSubLocationId());
        complaintDTO.setProcessBy(item.getProcessBy());
        complaintDTO.setWorkerId(item.getWorkerId());
        complaintDTO.setBeforeImage(item.getBeforeImage());
        complaintDTO.setProcessImage(item.getProcessImage());
        complaintDTO.setAfterImage(item.getAfterImage());
        complaintDTO.setStatusComplaint(item.getStatusComplaint());
        complaintDTO.setCreatedAt(item.getCreatedAt());

        return complaintDTO;
    }
}
