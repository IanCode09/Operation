package com.carefast.careops.service.checklist;

import com.carefast.careops.dto.checklist.ChecklistDTO;
import com.carefast.careops.dto.checklist.ObjectChecklistDTO;
import com.carefast.careops.model.checklist.ChecklistModel;
import com.carefast.careops.model.project.ProjectSubLocationActivityModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.checklist.ChecklistRepository;
import com.carefast.careops.repository.project.ProjectSubLocationActivityRepository;
import com.carefast.careops.utils.FileUtils;
import com.carefast.careops.utils.PathPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChecklistService {

    @Autowired
    private ProjectSubLocationActivityRepository projectSubLocationActivityRepository;
    @Autowired
    private ChecklistRepository checklistRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private FileUtils fileUtils;

    public ObjectChecklistDTO getObjectActivity(int activityId, String projectId) {

        Optional<ProjectSubLocationActivityModel> activity = projectSubLocationActivityRepository.findById(activityId);

        if (activity.isPresent()) {
           return convertObjectChecklistDTO(activity.get());
        } else {
            return null;
        }
    }

    public ChecklistDTO submitEmployeeChecklistDAC(int employeeId, String projectId, int activityId,
                                                   int submitBy, int plottingId, String objectFirst, String objectSecond,
                                                   String objectThird, String objectFourth, String objectFifth,
                                                   String objectFirstValue, String objectSecondValue,
                                                   String objectThirdValue, String objectFourthValue,
                                                   String objectFifthValue, String notes, MultipartFile file) {

        ChecklistModel checklistModel = new ChecklistModel();
        checklistModel.setSubmitBy(submitBy);
        checklistModel.setEmployeeId(employeeId);
        checklistModel.setProjectId(projectId);
        checklistModel.setPlottingId(plottingId);
        checklistModel.setActivityId(activityId);
        checklistModel.setObjectId(objectFirst);
        checklistModel.setObjectIdValue(objectFirstValue);
        checklistModel.setObjectIdSecond(objectSecond);
        checklistModel.setObjectIdSecondValue(objectSecondValue);
        checklistModel.setObjectIdThird(objectThird);
        checklistModel.setObjectIdThirdValue(objectThirdValue);
        checklistModel.setObjectIdFour(objectFourth);
        checklistModel.setObjectIdFourValue(objectFourthValue);
        checklistModel.setObjectIdFive(objectFifth);
        checklistModel.setObjectIdFiveValue(objectFifthValue);
        checklistModel.setNote(notes);
        checklistModel.setCreatedAt(LocalDateTime.now());

        String employeeName = insysEmployeeRepository.findById(employeeId).get().getEmployeeName();
        String fileName = fileUtils.generateImageActvityChecklistName(employeeName, employeeId, projectId, activityId, file.getOriginalFilename(), "CHECKLIST");
        fileUtils.saveImage(file, fileName, PathPhoto.PATH_PHOTO_CHECKLIST);

        checklistModel.setImage(fileName);

        return convertChecklistDTO(checklistRepository.save(checklistModel));
    }

    public ChecklistDTO getActivityEmployeeChecklist(int employeeId, String projectId, int plottingId, int activityId) {

        Optional<ChecklistModel> activityChecklist = checklistRepository.getActivityEmployeeChecklist(employeeId, projectId, plottingId, activityId, LocalDate.now());

        if (activityChecklist.isPresent()) {
            return convertChecklistDTO(activityChecklist.get());
        } else {
            return null;
        }
    }

    public ObjectChecklistDTO convertObjectChecklistDTO(ProjectSubLocationActivityModel item) {
        ObjectChecklistDTO objectChecklistDTO = new ObjectChecklistDTO();
        objectChecklistDTO.setIdSublocationActivity(item.getIdSubLocationActivity());
        objectChecklistDTO.setProjectId(item.getProjectId());
        objectChecklistDTO.setObjectId(item.getObjectId());
        objectChecklistDTO.setObjectIdSecond(item.getObjectIdSecond());
        objectChecklistDTO.setObjectIdThird(item.getObjectIdThird());
        objectChecklistDTO.setObjectIdFour(item.getObjectIdFour());
        objectChecklistDTO.setObjectIdFive(item.getObjectIdFive());

        return objectChecklistDTO;
    }

    public ChecklistDTO convertChecklistDTO(ChecklistModel item) {
        ChecklistDTO checklistDTO = new ChecklistDTO();
        checklistDTO.setChecklistId(item.getChecklistId());
        checklistDTO.setSubmitBy(item.getSubmitBy());
        checklistDTO.setEmployeeId(item.getEmployeeId());
        checklistDTO.setProjectId(item.getProjectId());
        checklistDTO.setPlottingId(item.getPlottingId());
        checklistDTO.setActivityId(item.getActivityId());
        checklistDTO.setObjectId(item.getObjectId());
        checklistDTO.setObjectIdValue(item.getObjectIdValue());
        checklistDTO.setObjectIdSecond(item.getObjectIdSecond());
        checklistDTO.setObjectIdSecondValue(item.getObjectIdSecondValue());
        checklistDTO.setObjectIdThird(item.getObjectIdThird());
        checklistDTO.setObjectIdThirdValue(item.getObjectIdThirdValue());
        checklistDTO.setObjectIdFour(item.getObjectIdFour());
        checklistDTO.setObjectIdFourValue(item.getObjectIdFourValue());
        checklistDTO.setObjectIdFive(item.getObjectIdFive());
        checklistDTO.setObjectIdFiveValue(item.getObjectIdFiveValue());
        checklistDTO.setImage(item.getImage());
        checklistDTO.setNote(item.getNote());
        checklistDTO.setCreatedAt(item.getCreatedAt());

        return checklistDTO;
    }
}
