package com.carefast.careops.controller.otoritas;

import com.carefast.careops.dto.otoritas.OtoritasDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.otoritas.OtoritasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/project", produces = {"application/json"})
public class OtoritasController {
    @Autowired
    private OtoritasService otoritasService;

    @GetMapping("/otoritas/checklist/{projectCode}")
    public void getOtoritasChecklist(HttpServletRequest request, HttpServletResponse response, @PathVariable String projectCode) throws IOException {

        List<OtoritasDTO> otoritasDTO = otoritasService.getOtoritasChecklist(projectCode);

        DataResponse<List<OtoritasDTO>> data = new DataResponse<>();
        data.setData(otoritasDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }
}
