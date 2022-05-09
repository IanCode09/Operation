package com.carefast.careops.service.schedule;

import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.model.schedule.EmployeeScheduleModel;
import com.carefast.careops.model.schedule.ScheduleModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.schedule.EmployeeScheduleRepository;
import com.carefast.careops.repository.schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.List;

@Service
public class AssignRandomScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private EmployeeScheduleRepository employeeScheduleRepostory;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;

    public boolean assignSchedule(String projectCode, int month, int year) {
        List<ScheduleModel> schedules = scheduleRepository.getAllSchedulePerMonth(projectCode, month, year);
        Collections.shuffle(schedules);
        List<InsysEmployeeModel> employees = insysEmployeeRepository.findEmployeeCleanerActive(projectCode);

        System.out.println("SCHEDULE SIZE " + schedules.size());
        System.out.println("EMPLOYEE SIZE " + employees.size());

        for (int i = 0 ; i < employees.size() ; i++) {
            EmployeeScheduleModel employeeScheduleModel = new EmployeeScheduleModel();
            employeeScheduleModel.setEmployeeId(employees.get(i).getIdEmployee());
            employeeScheduleModel.setProjectCode(projectCode);
            employeeScheduleModel.setScheduleId(schedules.get(i).getScheduleId());
            employeeScheduleModel.setMonth(month);
            employeeScheduleModel.setYear(year);
            employeeScheduleModel.setStartAt(LocalDate.now().withMonth(month).with(TemporalAdjusters.firstDayOfMonth()));
            employeeScheduleModel.setEndAt(LocalDate.now().withMonth(month).with(TemporalAdjusters.lastDayOfMonth()));
            employeeScheduleModel.setStatus("Y");

            employeeScheduleRepostory.save(employeeScheduleModel);
        }

        return true;
    }
}
