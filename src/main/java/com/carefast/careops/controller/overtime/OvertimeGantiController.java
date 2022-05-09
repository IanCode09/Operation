package com.carefast.careops.controller.overtime;

import com.carefast.careops.dto.employee.response.ProfileDTO;
import com.carefast.careops.dto.overtime.CustomOvertimeGantiDTO;
import com.carefast.careops.dto.overtime.OvertimeGantiDTO;
import com.carefast.careops.dto.project.EmployeeDetailListProjectDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.overtime.OvertimeGantiService;
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
@RequestMapping(value = "/api/v1/overtime")
public class OvertimeGantiController {

    @Autowired
    private OvertimeGantiService overtimeGantiService;

    @GetMapping("/ganti")
    public void getAllOvertimeGanti(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam int employeeId,
                                    @RequestParam String projectId,
                                    @RequestParam("page") int page) throws IOException {

        Slice<CustomOvertimeGantiDTO> customOvertimeGantiDTO = overtimeGantiService.getAllOvertimeGanti(employeeId, projectId, page);

        DataResponse<Slice<CustomOvertimeGantiDTO>> data = new DataResponse<>();
        data.setData(customOvertimeGantiDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/ganti/{overtimeId}")
    public void getOvertimeGantiDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable int overtimeId) throws IOException {

        CustomOvertimeGantiDTO customOvertimeGantiDTO = overtimeGantiService.getOvertimeGantiDetail(overtimeId);

        if (customOvertimeGantiDTO != null) {
            DataResponse<CustomOvertimeGantiDTO> data = new DataResponse<>();
            data.setData(customOvertimeGantiDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "404", "Something wrong or overtime not found");
        }
    }

    @PostMapping("/ganti/create")
    public void createOvertimeGanti(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam int createdById,
                                    @RequestParam int employeeId,
                                    @RequestParam int employeeReplaceId,
                                    @RequestParam String projectId,
                                    @RequestParam String title,
                                    @RequestParam String description,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    @RequestParam int shiftId) throws IOException {

        OvertimeGantiDTO overtimeGantiDTO = overtimeGantiService.createOvertimeGanti(createdById, employeeId, employeeReplaceId, projectId, title, description, date, shiftId);

        if (overtimeGantiDTO != null) {
            DataResponse<OvertimeGantiDTO> data = new DataResponse<>();
            data.setData(overtimeGantiDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Failed to create overtime");
        }
    }

    @GetMapping("/ganti/select/employee")
    public void getEmployeeGanti(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam String projectId,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws IOException {

        List<ProfileDTO> profileDTO = overtimeGantiService.getEmployeeGanti(projectId, date);
        DataResponse<List<ProfileDTO>> data = new DataResponse<>();
        data.setData(profileDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/ganti/select/shift")
    public void getShift(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam String projectId, @RequestParam int employeeId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws IOException {

        EmployeeDetailListProjectDTO employeeDetailListProjectDTO = overtimeGantiService.getShift(projectId, employeeId, date);

        if (employeeDetailListProjectDTO != null) {
            DataResponse<EmployeeDetailListProjectDTO> data = new DataResponse<>();
            data.setData(employeeDetailListProjectDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "404", "Data not found");
        }
    }

    @GetMapping("/ganti/select/employee-replace")
    public void getEmployeeReplace(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String projectId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws IOException {

        List<ProfileDTO> profileDTO = overtimeGantiService.getEmployeeReplace(projectId, date);
        DataResponse<List<ProfileDTO>> data = new DataResponse<>();
        data.setData(profileDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }
}
