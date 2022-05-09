package com.carefast.careops.controller.employee;

import com.carefast.careops.dto.employee.request.EmployeeVerifyOTPRequestDTO;
import com.carefast.careops.dto.employee.request.ForgotPasswordRequestDTO;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.employee.EmployeeOTPService;
import com.carefast.careops.utils.EmailSubjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/employee", produces = {"application/json"})
public class EmployeeOTPController {

    @Autowired
    private EmployeeOTPService employeeOTPService;

    @PostMapping("/send-otp")
    public void sendEmailToEmployee(HttpServletRequest request, HttpServletResponse response, @RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) throws IOException {

        int sendEmailSuccess = employeeOTPService.sendOTP(forgotPasswordRequestDTO.getEmail(), EmailSubjectUtils.TYPE_EMAIL_FORGOT_PASSWORD);

        if (sendEmailSuccess == EmployeeOTPService.SEND_OTP_SUCCESS) {
            HandlerResponse.responseSuccessOK(response, "Success Send OTP");
        } else if (sendEmailSuccess == EmployeeOTPService.FAILED_SEND_OTP){
            HandlerResponse.responseBadRequest(response, "01", "Failed Send OTP");
        } else if(sendEmailSuccess == EmployeeOTPService.USER_NOT_FOUND) {
            HandlerResponse.responseBadRequest(response, "02", "User not found");
        } else if (sendEmailSuccess == EmployeeOTPService.FORMAT_EMAIL_INVALID) {
            HandlerResponse.responseBadRequest(response, "400", "Email not valid");
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong");
        }
    }

    @PostMapping("/verify-otp")
    public void verifyOTP(HttpServletRequest request, HttpServletResponse response, @RequestBody EmployeeVerifyOTPRequestDTO employeeVerifyOTPRequestDTO) throws IOException {
        boolean checkCodeOTP = employeeOTPService.verifyOTP(employeeVerifyOTPRequestDTO.getEmail(), employeeVerifyOTPRequestDTO.getCodeOtp());

        if (checkCodeOTP) {
            HandlerResponse.responseSuccessOK(response, "Verify code OTP Success");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Code OTP invalid");
        }
    }
}
