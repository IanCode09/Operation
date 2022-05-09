package com.carefast.careops.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class BarcodeUtils {

    public static boolean generateQRCodeImage(String filePath, String fileName, String barcodeText) {
        Path root = Paths.get(filePath);

        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            QRCodeWriter barcodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            File imageFile =new File(root+"/"+fileName);
            ImageIO.write(bufferedImage, "png", imageFile);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String generateQRCodeName(String userName, int idEmployeeProject, int idProject) {
        LocalDate localDate = LocalDate.now();
        int currentDate = localDate.getDayOfMonth();
        int currentMonth = localDate.getMonthValue();
        int currentYear = localDate.getYear();

        String qrName = "COPS-"+userName.substring(0,5).toUpperCase()+"-"+idEmployeeProject+idProject+"-"+currentDate+currentMonth+currentYear+".png";
        return qrName;
    }

    public static String generateQRCodeSignature(int idEmployeeProject, String idProject) {
        LocalDate localDate = LocalDate.now();
        int currentDate = localDate.getDayOfMonth();
        int currentMonth = localDate.getMonthValue();
        int currentYear = localDate.getYear();

        String qrSignature = "QRID="+idEmployeeProject+"_"+currentDate+currentMonth+currentYear;
        return qrSignature;
    }
}
