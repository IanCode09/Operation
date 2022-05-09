package com.carefast.careops.dto.employee.response;

import com.carefast.careops.dto.activity.ActivityDTO;

import java.util.List;

public class EmployeeDailyActivityDTO {
    List<ActivityDTO> dailyActivities;

    public List<ActivityDTO> getDailyActivities() {
        return dailyActivities;
    }

    public void setDailyActivities(List<ActivityDTO> dailyActivities) {
        this.dailyActivities = dailyActivities;
    }
}
