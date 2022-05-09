package com.carefast.careops.controller.permission;

import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.permission.CustomPermissionDTO;
import com.carefast.careops.dto.permission.PermissionDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/permission", produces = {"application/json"})
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/create")
    public void employeeCreatePermission(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam int requestBy,
                                   @RequestParam String projectId,
                                   @RequestParam String title,
                                   @RequestParam String description,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                   @RequestParam("file") MultipartFile file) throws IOException {

        PermissionDTO permissionDTO = permissionService.employeeCreatePermission(requestBy, projectId, title, description, date, file);

        if (permissionDTO != null) {
            DataResponse<PermissionDTO> data = new DataResponse<>();
            data.setData(permissionDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Failed to create izin");
        }
    }

    @GetMapping("/history")
    public void getAllPermission(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam int employeeId,
                                 @RequestParam String projectId,
                                 @RequestParam("page") int page) throws IOException {

        Slice<CustomPermissionDTO> customPermissionDTO = permissionService.getAllPermission(employeeId, projectId, page);
        DataResponse<Slice<CustomPermissionDTO>> data = new DataResponse<>();
        data.setData(customPermissionDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/{permissionId}")
    public void getPermissionDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable int permissionId) throws IOException {

        CustomPermissionDTO customPermissionDTO = permissionService.getPermissionDetail(permissionId);

        if (customPermissionDTO != null) {
            DataResponse<CustomPermissionDTO> data = new DataResponse<>();
            data.setData(customPermissionDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Permission not found");
        }
    }
}
