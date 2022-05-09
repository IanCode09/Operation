package com.carefast.careops.dto.job;

public class JobPositionDTO {
    private int idJobPosition;
    private String codePosition;
    private String namaPosition;
    private String levelPosition;
    private String positionImage;

    public JobPositionDTO(int idJobPosition, String codePosition, String namaPosition, String levelPosition, String positionImage) {
        this.idJobPosition = idJobPosition;
        this.codePosition = codePosition;
        this.namaPosition = namaPosition;
        this.levelPosition = levelPosition;
        this.positionImage = positionImage;
    }

    public int getIdJobPosition() {
        return idJobPosition;
    }

    public void setIdJobPosition(int idJobPosition) {
        this.idJobPosition = idJobPosition;
    }

    public String getCodePosition() {
        return codePosition;
    }

    public void setCodePosition(String codePosition) {
        this.codePosition = codePosition;
    }

    public String getNamaPosition() {
        return namaPosition;
    }

    public void setNamaPosition(String namaPosition) {
        this.namaPosition = namaPosition;
    }

    public String getLevelPosition() {
        return levelPosition;
    }

    public void setLevelPosition(String levelPosition) {
        this.levelPosition = levelPosition;
    }

    public String getPositionImage() {
        return positionImage;
    }

    public void setPositionImage(String positionImage) {
        this.positionImage = positionImage;
    }
}
