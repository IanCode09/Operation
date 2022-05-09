package com.carefast.careops.controller.complaint;

import com.carefast.careops.dto.attendance.AttendanceDTO;
import com.carefast.careops.dto.complaint.ComplaintDTO;
import com.carefast.careops.dto.complaint.CustomComplaintDTO;
import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.project.LocationDTO;
import com.carefast.careops.dto.project.SubLocationDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.complaint.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/complaint", produces = {"application/json"})
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/create")
    public void createComplaint(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam int userId,
                                @RequestParam String projectId,
                                @RequestParam String title,
                                @RequestParam String description,
                                @RequestParam int locationId,
                                @RequestParam int subLocationId,
                                @RequestParam("file") MultipartFile file) throws IOException {

        ComplaintDTO complaintDTO = complaintService.createComplaint(userId, projectId, title, description, locationId, subLocationId, file);

        DataResponse<ComplaintDTO> data = new DataResponse<>();
        data.setData(complaintDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @PostMapping("/internal/create")
    public void carefastTeamCreateComplaint(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam int userId,
                                @RequestParam String projectId,
                                @RequestParam String title,
                                @RequestParam String description,
                                @RequestParam int locationId,
                                @RequestParam int subLocationId,
                                @RequestParam("file") MultipartFile file) throws IOException {

        ComplaintDTO complaintDTO = complaintService.carefastTeamcreateComplaint(userId, projectId, title, description, locationId, subLocationId, file);

        DataResponse<ComplaintDTO> data = new DataResponse<>();
        data.setData(complaintDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/location/{projectId}")
    public void getLocation(HttpServletRequest request, HttpServletResponse response, @PathVariable String projectId) throws IOException {

        List<LocationDTO> locationDTO = complaintService.getLocationProject(projectId);

        DataResponse<List<LocationDTO>> data = new DataResponse<>();
        data.setData(locationDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/sub-location/{projectId}/{locationId}")
    public void getSubLocation(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable String projectId, @PathVariable int locationId) throws IOException {

        List<SubLocationDTO> subLocationDTO = complaintService.getSubLocationProject(projectId, locationId);
        DataResponse<List<SubLocationDTO>> data = new DataResponse<>();
        data.setData(subLocationDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

//    @GetMapping("{clientId}/{projectId}")
//    public void getAllComplaint(HttpServletRequest request, HttpServletResponse response,
//                                @PathVariable int clientId, @PathVariable String projectId) throws IOException {
//
//        List<CustomComplaintDTO> complaintDetailDTO = complaintService.getAllComplaint(clientId, projectId);
//        DataResponse<List<CustomComplaintDTO>> data = new DataResponse<>();
//        data.setData(complaintDetailDTO);
//        HandlerResponse.responseSuccessWithData(response, data);
//    }

    @GetMapping("/{clientId}/{projectId}")
    public void getAllComplaint(HttpServletRequest request, HttpServletResponse response,
                                @PathVariable int clientId, @PathVariable String projectId,
                                @RequestParam("page") int page) throws IOException {

        Slice<CustomComplaintDTO> complaintDetailDTO = complaintService.getAllComplaint(clientId, projectId, page);
        DataResponse<Slice<CustomComplaintDTO>> data = new DataResponse<>();
        data.setData(complaintDetailDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/internal/{employeeId}/{projectId}")
    public void getAllComplaintByEmployeeId(HttpServletRequest request, HttpServletResponse response,
                                @PathVariable int employeeId, @PathVariable String projectId,
                                @RequestParam("page") int page) throws IOException {

        Slice<CustomComplaintDTO> complaintDetailDTO = complaintService.getAllComplaintByEmployeeId(employeeId, projectId, page);
        DataResponse<Slice<CustomComplaintDTO>> data = new DataResponse<>();
        data.setData(complaintDetailDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/{complaintId}")
    public void getDetailComplaint(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable int complaintId) throws IOException {

        CustomComplaintDTO complaintDTO = complaintService.getDetailComplaint(complaintId);

        if (complaintDTO != null) {
            DataResponse<CustomComplaintDTO> data = new DataResponse<>();
            data.setData(complaintDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Complaint not found");
        }
    }

    @GetMapping("/project")
    public void getComplaintByProjectCode(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam String projectCode, @RequestParam("page") int page) throws IOException {

        Slice<CustomComplaintDTO> complaint = complaintService.getComplaintByProjectCode(projectCode, page);
        DataResponse<Slice<CustomComplaintDTO>> data = new DataResponse<>();
        data.setData(complaint);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @PutMapping("/process")
    public void setComplaintProcess(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam int complaintId, @RequestParam int employeeId) throws IOException {

        ComplaintDTO complaintDTO = complaintService.setComplaintProcess(complaintId, employeeId);

        if (complaintDTO != null) {
            DataResponse<ComplaintDTO> data =  new DataResponse<>();
            data.setData(complaintDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Complaint not found or Something wrong");
        }
    }

    @GetMapping("/operator/cso/{projectCode}")
    public void getOperatorCleanerAttendanceIn(HttpServletRequest request, HttpServletResponse response,
                                              @PathVariable String projectCode) throws IOException {

        List<ProfileDTO> profileDTO = complaintService.getOperatorCleanerAttendanceIn(projectCode);

        DataResponse<List<ProfileDTO>> data = new DataResponse<>();
        data.setData(profileDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @PutMapping("/operator/cso/submit")
    public void submitOperatorCsoComplaint(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam int complaintId, @RequestParam int workerId) throws IOException {

        ComplaintDTO complaintDTO = complaintService.submitOperatorCsoComplaint(complaintId, workerId);

        if (complaintDTO != null) {
            DataResponse<ComplaintDTO> data =  new DataResponse<>();
            data.setData(complaintDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Complaint not found or Something wrong");
        }
    }
}
