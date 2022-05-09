package com.carefast.careops.model.job;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_job_position")
public class JobModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job_position")
    private int jobPositionId;
    @Column(name = "code_position")
    private String codePosition;
    @Column(name = "nama_position")
    private String namaPosition;
    @Column(name = "level_position", columnDefinition = "enum('BASIC', 'INTERMEDIATE', 'ADVANCED', 'PROFESSIONAL')")
    private String levelPosition;
    @Type(type = "text")
    @Column(name = "position_image")
    private String positionImage;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public int getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(int jobPositionId) {
        this.jobPositionId = jobPositionId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
