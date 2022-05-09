package com.carefast.careops.controller.schedule;

import com.carefast.careops.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/project", produces = {"application/json"})
public class GenerateScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/schedule")
    public void generateEmpSchedule(HttpServletRequest request, HttpServletResponse response, @RequestParam String projectCode) throws IOException {
        scheduleService.createEmployeeSchedule(projectCode);
    }
}
