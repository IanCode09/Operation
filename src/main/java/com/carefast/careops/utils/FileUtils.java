package com.carefast.careops.utils;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class FileUtils {

    private final static String TYPE_SCAN_OUT = "out";
    private final static String TYPE_SCAN_IN = "in";

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_hhmmss");

    public String getExtension(String filename) {
        String extension = "";
        String[] arr = filename.split("\\.");
        if(arr.length > 0) {
            extension = arr[arr.length - 1];
            return "."+extension;
        }else {
            return extension;
        }
    }

    public boolean saveImage(MultipartFile file, String fileName, Path pathPhotoSelfie) {
        try {
            if (!Files.exists(pathPhotoSelfie)) {
                Files.createDirectories(pathPhotoSelfie);
            }

            Files.copy(file.getInputStream(), pathPhotoSelfie.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generateAttendanceImageName(int employeeId, String projectCode, String fileName, String type) {
        String typeScan = "";

        switch (type) {
            case TYPE_SCAN_IN:
                typeScan = "IN";
                break;
            case TYPE_SCAN_OUT:
                typeScan = "OUT";
                break;
        }

        String extension = this.getExtension(fileName);
        String firstName = "";
        firstName = employeeId + "_" + projectCode + "_" + convertDateTime() + "_" + typeScan + extension;

        return firstName;
    }

    public String generateImageActvityChecklistName(String userName, int employeeId, String projectId, int activityId, String fileName, String typeImage) {

        LocalDate localDate = LocalDate.now();
        int currentDate = localDate.getDayOfMonth();
        int currentMonth = localDate.getMonthValue();
        int currentYear = localDate.getYear();

        String extension = this.getExtension(fileName);

        String firstName = "";
        String[] arr = userName.split(" ");
        if (arr.length > 0) {
            return firstName = arr[0] + "_" + projectId + "_" + employeeId + "_" + activityId + "_" + currentDate + currentMonth + currentYear + "_" + typeImage + extension;
        } else {
            return firstName = userName + "_" + projectId + "_" + employeeId + "_" + activityId + "_" + currentDate + currentMonth + currentYear + "_" + typeImage + extension;
        }
    }

    public String generateImageProfile(String userName, String employeeCode, String fileName) {

        String extension = this.getExtension(fileName);

        String firstName = "";
        String[] arr = userName.split(" ");
        if (arr.length > 0) {
            return firstName = arr[0] + "_" + employeeCode + extension;
        } else {
            return firstName = userName + "_" + employeeCode + extension;
        }
    }

    public String generateImageName(int employeeId, String projectId, String fileName, String typeImage) {
        String extension = this.getExtension(fileName);
        String firstName = employeeId + "_" + projectId + "_" + convertDateTime() + "_" + typeImage + extension;

        return firstName;
    }

    public String generateComplaintImageName(int userId, String projectId, String fileName, String typeImage) {

        String extension = this.getExtension(fileName);
        String name = projectId + "_" + userId + "_" + convertDateTime() + "_" + typeImage + extension;

        return name;
    }

    public String generateComplaintProcessName(int userId, String projectId, String fileName) {

        String extension = this.getExtension(fileName);
        String name = projectId + "_" + userId + "_" + convertDateTime() + extension;

        return name;
    }

    public String convertDateTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");

        return dateFormat.format(date);
    }
}
