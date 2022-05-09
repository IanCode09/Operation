package com.carefast.careops.controller.permission;

import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.permission.CustomPermissionDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.permission.PermissionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/permission", produces = {"application/json"})
public class PermissionProcessController {

    @Autowired
    private PermissionProcessService permissionProcessService;

    @GetMapping("/process/all")
    public void getAllPermissionProcess(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam int employeeId,
                                         @RequestParam String projectId,
                                         @RequestParam("page") int page) throws IOException {

        Slice<CustomPermissionDTO> customPermissionDTO = permissionProcessService.getAllPermissionProcess(employeeId, projectId, page);
        DataResponse<Slice<CustomPermissionDTO>> data = new DataResponse<>();
        data.setData(customPermissionDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/process/employee/replace")
    public void getEmployeeReplace(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String projectId,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws IOException {

        List<ProfileDTO> profileDTO = permissionProcessService.getEmployeeReplace(projectId, date);
        DataResponse<List<ProfileDTO>> data = new DataResponse<>();
        data.setData(profileDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @PutMapping("/process/deny/{permissionId}")
    public void permissionRefuse(HttpServletRequest request, HttpServletResponse response, @PathVariable int permissionId) throws IOException {

        CustomPermissionDTO customPermissionDTO = permissionProcessService.permissionRefuse(permissionId);

        if (customPermissionDTO != null) {
            DataResponse<CustomPermissionDTO> data = new DataResponse<>();
            data.setData(customPermissionDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong or permission not found");
        }
    }

    @PutMapping("process/accept")
    public void permissionAccept(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam int permissionId,
                                 @RequestParam int employeeApproveId,
                                 @RequestParam int employeeReplaceId,
                                 @RequestParam String projectId,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws IOException {

        CustomPermissionDTO customPermissionDTO = permissionProcessService.permissionAccept(permissionId, employeeApproveId, employeeReplaceId, projectId, date);

        if (customPermissionDTO != null) {
            DataResponse<CustomPermissionDTO> data = new DataResponse<>();
            data.setData(customPermissionDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong or permission not found");
        }
    }
}
