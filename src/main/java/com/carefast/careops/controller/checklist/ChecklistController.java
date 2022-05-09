package com.carefast.careops.controller.checklist;

import com.carefast.careops.dto.checklist.ChecklistDTO;
import com.carefast.careops.dto.checklist.ObjectChecklistDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.checklist.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/checklist", produces = {"application/json"})
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;

    @GetMapping("/activity/object")
    public void getObjectActivity(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam int activityId,
                                  @RequestParam String projectId) throws IOException {

        ObjectChecklistDTO objectChecklistDTO = checklistService.getObjectActivity(activityId, projectId);

        if (objectChecklistDTO != null) {
            DataResponse<ObjectChecklistDTO> data = new DataResponse<>();
            data.setData(objectChecklistDTO);

            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something Wrong");
        }
    }

    @PostMapping("/activity/object/submit")
    public void submitEmployeeChecklistDAC(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam int employeeId,
                                  @RequestParam String projectId,
                                  @RequestParam int activityId,
                                  @RequestParam int submitBy,
                                  @RequestParam int plottingId,
                                  @RequestParam String objectFirst,
                                  @RequestParam String objectSecond,
                                  @RequestParam String objectThird,
                                  @RequestParam String objectFourth,
                                  @RequestParam String objectFifth,
                                  @RequestParam String objectFirstValue,
                                  @RequestParam String objectSecondValue,
                                  @RequestParam String objectThirdValue,
                                  @RequestParam String objectFourthValue,
                                  @RequestParam String objectFifthValue,
                                  @RequestParam String notes,
                                  @RequestParam("file") MultipartFile file) throws IOException {

        ChecklistDTO checklistDTO = checklistService.submitEmployeeChecklistDAC(employeeId, projectId, activityId,
                submitBy, plottingId, objectFirst, objectSecond, objectThird, objectFourth, objectFifth,
                objectFirstValue, objectSecondValue, objectThirdValue, objectFourthValue,
                objectFifthValue, notes, file);

        if (checklistDTO != null) {
            DataResponse<ChecklistDTO> data = new DataResponse<>();
            data.setData(checklistDTO);

            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something Wrong");
        }
    }

    @GetMapping("/activity/employee")
    public void getActivityEmployeeChecklist(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam int employeeId, @RequestParam String projectId,
                                             @RequestParam int plottingId, @RequestParam int activityId) throws IOException {

        ChecklistDTO checklistDTO = checklistService.getActivityEmployeeChecklist(employeeId, projectId, plottingId, activityId);

        if (checklistDTO != null) {
            DataResponse<ChecklistDTO> data = new DataResponse<>();
            data.setData(checklistDTO);

            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Not Found");
        }
    }

    @GetMapping("/filter/location")
    public void filterByShiftAndPlace(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam int employeeId,
                                      @RequestParam String projectId,
                                      @RequestParam int shiftId) throws IOException {

    }
}
