package com.carefast.careops.model.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "tab_detail_shift_project")
public class ProjectShiftModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_detail_shift")
    private int idDetailShift;
    @Column(name = "id_project")
    private String idProject;
    @Column(name = "id_shift")
    private int idShift;
    @Column(name = "people_total")
    private int peopleTotal;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "start_at")
    private LocalTime startAt;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "end_at")
    private LocalTime endAt;
    @Column(name = "interval_set")
    private int intervalSet;
    @Column(name = "toleransi_time")
    private int toleransiTime;

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

    public int getIdShift() {
        return idShift;
    }

    public void setIdShift(int idShift) {
        this.idShift = idShift;
    }

    public int getPeopleTotal() {
        return peopleTotal;
    }

    public void setPeopleTotal(int peopleTotal) {
        this.peopleTotal = peopleTotal;
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

    public int getIntervalSet() {
        return intervalSet;
    }

    public void setIntervalSet(int intervalSet) {
        this.intervalSet = intervalSet;
    }

    public int getToleransiTime() {
        return toleransiTime;
    }

    public void setToleransiTime(int toleransiTime) {
        this.toleransiTime = toleransiTime;
    }
}
