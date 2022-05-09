package com.carefast.careops.service.employee;

import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.employee.EmployeeOTPModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.employee.EmployeeOTPRepository;
import com.carefast.careops.repository.employee.EmployeeRepository;
import com.carefast.careops.utils.EmailUtils;
import com.carefast.careops.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmployeeOTPService {

    public static final int SEND_OTP_SUCCESS = 1;
    public static final int FAILED_SEND_OTP = 2;
    public static final int USER_NOT_FOUND = 3;
    public static final int FORMAT_EMAIL_INVALID = 4;
    public static final int EMAIL_EXISTS = 5;

    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private EmployeeOTPRepository employeeOTPRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ValidationUtils validationUtils;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;

    public int sendOTP(String email, String typeEmail) {
        Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findByEmail(email);

        if (validationUtils.emailValidation(email)) {
            if (employee.isPresent()) {
                boolean response = emailUtils.sendEmailOTP(email, typeEmail);

                if (response) {
                    return SEND_OTP_SUCCESS;
                } else {
                    return FAILED_SEND_OTP;
                }
            }

            return USER_NOT_FOUND;
        } else {
            return FORMAT_EMAIL_INVALID;
        }

    }

    public boolean verifyOTP(String email, String codeOTP) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Optional<EmployeeOTPModel> employeeOTPModel = employeeOTPRepository.findCodeOTP(email, codeOTP);

        if (employeeOTPModel.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public int sendOTPRegister(String email, String nuc, String typeEmail) {
        Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findByNuc(nuc);
        Optional<InsysEmployeeModel> emailExists = insysEmployeeRepository.findByEmail(email);

        if (employee.isPresent()) {
            if (emailExists.isPresent()) {
                return EMAIL_EXISTS;
            } else {
                if (validationUtils.emailValidation(email)) {
                    boolean response = emailUtils.sendEmailOTP(email, typeEmail);

                    if (response) {
                        employee.get().setEmployeeEmail(email);
                        insysEmployeeRepository.save(employee.get());

                        return SEND_OTP_SUCCESS;
                    } else {
                        return FAILED_SEND_OTP;
                    }
                } else {
                    return FORMAT_EMAIL_INVALID;
                }
            }
        } else {
            return USER_NOT_FOUND;
        }
    }

    public int resendOTPRegister(String email, String nuc, String typeEmail) {
        Optional<InsysEmployeeModel> employee = insysEmployeeRepository.findByNuc(nuc);

        if (employee.isPresent()) {
            if (validationUtils.emailValidation(email)) {
                boolean response = emailUtils.sendEmailOTP(email, typeEmail);

                if (response) {
                    employee.get().setEmployeeEmail(email);
                    insysEmployeeRepository.save(employee.get());

                    return SEND_OTP_SUCCESS;
                } else {
                    return FAILED_SEND_OTP;
                }
            } else {
                return FORMAT_EMAIL_INVALID;
            }
        } else {
            return USER_NOT_FOUND;
        }
    }
}
