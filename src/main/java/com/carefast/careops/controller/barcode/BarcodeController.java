package com.carefast.careops.controller.barcode;

import com.carefast.careops.dto.employee.response.EmployeeBarcodeDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.barcode.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/barcode", produces = {"application/json"})
public class BarcodeController {
    @Autowired
    private BarcodeService barcodeService;

    @PostMapping("/generate")
    public void generateBarcode(HttpServletRequest request, HttpServletResponse response, @RequestParam int employeeId, @RequestParam String projectId) throws IOException {

        int result = barcodeService.generateQRKey(employeeId, projectId);

        if (result == BarcodeService.SUCCESS) {
            HandlerResponse.responseSuccessCreated(response, "Success");
        } else if (result == BarcodeService.DAY_OFF) {
            HandlerResponse.responseSuccessOK(response, "DAY OFF");
        }else if (result == BarcodeService.NOT_FOUND){
            HandlerResponse.responseBadRequest(response, "01", "Something wrong");
        }
    }

    @GetMapping("/employee")
    public void getEmployeeBarcode(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam int employeeId,
                                   @RequestParam String projectCode) throws IOException {

        EmployeeBarcodeDTO employeeBarcode = barcodeService.getEmployeeBarcode(employeeId, projectCode);

        if (employeeBarcode != null) {
            DataResponse<EmployeeBarcodeDTO> data = new DataResponse<>();
            data.setData(employeeBarcode);

            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Something Wrong");
        }
    }
}
