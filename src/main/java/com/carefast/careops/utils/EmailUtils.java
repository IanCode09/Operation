package com.carefast.careops.utils;

import com.carefast.careops.model.employee.EmployeeOTPModel;
import com.carefast.careops.repository.employee.EmployeeOTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class EmailUtils {

    @Autowired
    EmployeeOTPRepository employeeOTPRepository;

    public boolean sendEmailOTP(String emailTo, String typeEmail) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "m007.dapurhosting.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(CarefastEmailUtils.USERNAME, CarefastEmailUtils.PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(CarefastEmailUtils.EMAIL_FROM));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailTo)
            );

            String subjectEmail = "";
            String otp = generateOTP();

            switch (typeEmail) {
                case EmailSubjectUtils.TYPE_EMAIL_FORGOT_PASSWORD:
                    subjectEmail = EmailSubjectUtils.SUBJECT_EMAIL_FORGOT_PASSWORD;
                    break;
                case EmailSubjectUtils.TYPE_EMAIL_OTP_REGISTER:
                    subjectEmail = EmailSubjectUtils.SUBJET_EMAIL_OTP_REGISTER;
                    break;
                default:
                    subjectEmail = "";
            }

            message.setSubject(subjectEmail);
            message.setText(otp);
            Transport.send(message);

            Optional<EmployeeOTPModel> employeeOTP = employeeOTPRepository.findByEmail(emailTo);
            if (employeeOTP.isEmpty()) {
                EmployeeOTPModel employeeOTPModel = new EmployeeOTPModel();
                employeeOTPModel.setEmployeeEmail(emailTo);
                employeeOTPModel.setOtpCode(otp);
                employeeOTPModel.setCreatedAt(LocalDateTime.now());
                employeeOTPModel.setExpiredAt(LocalDateTime.now().plusSeconds(60));

                employeeOTPRepository.save(employeeOTPModel);
            } else {
                employeeOTP.get().setOtpCode(otp);

                employeeOTPRepository.save(employeeOTP.get());
            }

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();

            return false;
        }
    }

    public String generateOTP() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999);

        return String.format("%05d", number);
    }
}
