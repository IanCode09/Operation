package com.carefast.careops.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalTime;

public class ProjectShiftDTO {

    private int idDetailShift;
    private String idProject;
    private ShiftDTO shift;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalTime startAt;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "hh:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalTime endAt;

    public ProjectShiftDTO() {
    }

    public ProjectShiftDTO(int idDetailShift, String idProject, ShiftDTO shift, LocalTime startAt, LocalTime endAt) {
        this.idDetailShift = idDetailShift;
        this.idProject = idProject;
        this.shift = shift;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public int getIdDetailShift() {
        return idDetailShift;
    }

    public void setIdDetailShift(int idDetailShift) {
        this.idDetailShift = idDetailShift;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public ShiftDTO getShift() {
        return shift;
    }

    public void setShift(ShiftDTO shift) {
        this.shift = shift;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public LocalTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalTime endAt) {
        this.endAt = endAt;
    }
}
