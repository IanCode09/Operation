package com.carefast.careops.model.activity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "tab_master_activity")
public class ActivityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int activityId;
    @Column(name = "activity_name")
    private String activityName;
    @Column(name = "estimation")
    private int estimation;
    @Column(name = "frequency")
    private int frequency;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
