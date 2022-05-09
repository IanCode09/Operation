package com.carefast.careops.controller.employee;

import com.carefast.careops.dto.INSYS.response.InsysEmployeeDTO;
import com.carefast.careops.dto.employee.request.EmployeeCreatePasswordRequest;
import com.carefast.careops.dto.employee.request.ForgotPasswordRequestDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.employee.EmployeeOTPService;
import com.carefast.careops.service.employee.EmployeeService;
import com.carefast.careops.utils.EmailSubjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping(value = "/api/v1/employee", produces = {"application/json"})
public class EmployeeRegisterController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeOTPService employeeOTPService;

    @GetMapping("/{nuc}/{nik}")
    public void verifyNucNik(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable String nuc, @PathVariable String nik) throws IOException {

        InsysEmployeeDTO insysEmployeeDTO = employeeService.getEmployeeByNucNik(nuc, nik);

        if (insysEmployeeDTO != null) {
            DataResponse<InsysEmployeeDTO> data = new DataResponse<>();
            data.setData(insysEmployeeDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "User not found");
        }
    }

    @PostMapping("/register/send-otp")
    public void sendOtpToEmailEmployee(HttpServletRequest request, HttpServletResponse response, @RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) throws IOException {

        int sendEmailSuccess = employeeOTPService.sendOTPRegister(forgotPasswordRequestDTO.getEmail(),
                forgotPasswordRequestDTO.getNuc(), EmailSubjectUtils.TYPE_EMAIL_OTP_REGISTER);

        if (sendEmailSuccess == EmployeeOTPService.SEND_OTP_SUCCESS) {
            HandlerResponse.responseSuccessOK(response, "Success Send OTP");
        } else if (sendEmailSuccess == EmployeeOTPService.FAILED_SEND_OTP){
            HandlerResponse.responseBadRequest(response, "01", "Failed Send OTP");
        } else if(sendEmailSuccess == EmployeeOTPService.USER_NOT_FOUND) {
            HandlerResponse.responseBadRequest(response, "02", "User not found");
        } else if (sendEmailSuccess == EmployeeOTPService.FORMAT_EMAIL_INVALID) {
            HandlerResponse.responseBadRequest(response, "400", "Email not valid");
        } else if (sendEmailSuccess == EmployeeOTPService.EMAIL_EXISTS) {
            HandlerResponse.responseBadRequest(response, "300", "Email exists");
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong");
        }
    }

    @PostMapping("/register/resend-otp")
    public void resendOtpToEmailEmployee(HttpServletRequest request, HttpServletResponse response, @RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) throws IOException {

        int sendEmailSuccess = employeeOTPService.resendOTPRegister(forgotPasswordRequestDTO.getEmail(),
                forgotPasswordRequestDTO.getNuc(), EmailSubjectUtils.TYPE_EMAIL_OTP_REGISTER);

        if (sendEmailSuccess == EmployeeOTPService.SEND_OTP_SUCCESS) {
            HandlerResponse.responseSuccessOK(response, "Success Send OTP");
        } else if (sendEmailSuccess == EmployeeOTPService.FAILED_SEND_OTP){
            HandlerResponse.responseBadRequest(response, "01", "Failed Send OTP");
        } else if(sendEmailSuccess == EmployeeOTPService.USER_NOT_FOUND) {
            HandlerResponse.responseBadRequest(response, "02", "User not found");
        } else if (sendEmailSuccess == EmployeeOTPService.FORMAT_EMAIL_INVALID) {
            HandlerResponse.responseBadRequest(response, "400", "Email not valid");
        } else if (sendEmailSuccess == EmployeeOTPService.EMAIL_EXISTS) {
            HandlerResponse.responseBadRequest(response, "300", "Email exists");
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something wrong");
        }
    }

    @PutMapping("/create-password")
    public void createPassword(HttpServletRequest request, HttpServletResponse response, @RequestBody EmployeeCreatePasswordRequest createPasswordRequest) throws IOException {

        int changePassword = employeeService.createPassword(createPasswordRequest.getEmail(), createPasswordRequest.getNuc(), createPasswordRequest.getPassword(), createPasswordRequest.getConfirmPassword());

        if (changePassword == EmployeeService.CHANGE_PASSWORD_SUCCESS) {
            HandlerResponse.responseSuccessOK(response, "Success Change Password");
        } else if (changePassword == EmployeeService.PASSWORD_UN_MATCH){
            HandlerResponse.responseBadRequest(response, "01", "Your password incorrect");
        } else if (changePassword == EmployeeService.USER_NOT_FOUND){
            HandlerResponse.responseBadRequest(response, "02", "User not found");
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Something Wrong");
        }
    }
}
