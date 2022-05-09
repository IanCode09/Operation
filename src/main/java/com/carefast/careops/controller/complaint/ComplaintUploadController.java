package com.carefast.careops.controller.complaint;

import com.carefast.careops.dto.complaint.ComplaintDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.complaint.ComplaintUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/complaint", produces = {"application/json"})
public class ComplaintUploadController {

    @Autowired
    private ComplaintUploadService complaintUploadService;

    @PutMapping("/upload/before")
    public void uploadComplaintImageBefore(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam int complaintId,
                                           @RequestParam int employeeId,
                                           @RequestParam("file") MultipartFile file) throws IOException {

        ComplaintDTO complaintDTO = complaintUploadService.uploadComplaintProgress(complaintId, employeeId, file, complaintUploadService.BEFORE);

        if (complaintDTO != null) {
            DataResponse<ComplaintDTO> data =  new DataResponse<>();
            data.setData(complaintDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Complaint not found or Something wrong");
        }
    }

    @PutMapping("/upload/progress")
    public void uploadComplaintImageProgress(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam int complaintId,
                                           @RequestParam int employeeId,
                                           @RequestParam("file") MultipartFile file) throws IOException {

        ComplaintDTO complaintDTO = complaintUploadService.uploadComplaintProgress(complaintId, employeeId, file, complaintUploadService.PROGRESS);

        if (complaintDTO != null) {
            DataResponse<ComplaintDTO> data = new DataResponse<>();
            data.setData(complaintDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Complaint not found or Something wrong");
        }
    }

    @PutMapping("/upload/after")
    public void uploadComplaintImageAfter(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam int complaintId,
                                             @RequestParam int employeeId,
                                             @RequestParam("file") MultipartFile file) throws IOException {

        ComplaintDTO complaintDTO = complaintUploadService.uploadComplaintProgress(complaintId, employeeId, file, complaintUploadService.AFTER);

        if (complaintDTO != null) {
            DataResponse<ComplaintDTO> data = new DataResponse<>();
            data.setData(complaintDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Complaint not found or Something wrong");
        }
    }

    @PutMapping("/finish")
    public void complaintFinished(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam int complaintId,
                                  @RequestParam int employeeId) throws IOException {

        ComplaintDTO complaintDTO = complaintUploadService.complaintFinished(complaintId, employeeId);

        if (complaintDTO != null) {
            DataResponse<ComplaintDTO> data = new DataResponse<>();
            data.setData(complaintDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Complaint not found or Something wrong");
        }
    }
}
