package com.carefast.careops.service.calendar;

import com.carefast.careops.dto.schedule.CustomEmployeeDetailListProjectDTO;
import com.carefast.careops.dto.schedule.CustomEmployeeScheduleLeaderDTO;
import com.carefast.careops.model.project.CustomEmployeeDetailListProjectModel;
import com.carefast.careops.model.project.CustomEmployeeScheduleLeaderModel;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.project.EmployeeScheduleLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CalendarService {
    public static final int CALENDAR_COUNT = 10;

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private EmployeeScheduleLeaderRepository employeeScheduleLeaderRepository;

    public Slice<CustomEmployeeDetailListProjectDTO> getEmployeeCalendar(int employeeId, LocalDate date, int page) {
        Pageable pagination = PageRequest.of(page, CALENDAR_COUNT);

        return employeeProjectRepository.getEmployeeCalendar(employeeId, date, pagination)
                .map(this::convertCustomEmployeeDetailListProjectDTO);
    }

    public Slice<CustomEmployeeScheduleLeaderDTO> getEmployeeNonOperatorCalendar(int employeeId, LocalDate date, int page) {
        Pageable pagination = PageRequest.of(page, CALENDAR_COUNT);

        return employeeScheduleLeaderRepository.getNonOperatorCalendar(employeeId, date, pagination)
                .map(this::convertCustomEmployeeScheduleLeaderDTO);
    }

    public CustomEmployeeDetailListProjectDTO convertCustomEmployeeDetailListProjectDTO(CustomEmployeeDetailListProjectModel item) {
        return new CustomEmployeeDetailListProjectDTO(
                item.getIdDetailEmployeeProject(),
                item.getIdEmployee(),
                item.getEmployeeName(),
                item.getIdProject(),
                item.getScheduleType(),
                item.getIdPlotting(),
                item.getcodePlottingArea(),
                item.getLocationId(),
                item.getLocationName(),
                item.getSubLocationId(),
                item.getSubLocationName(),
                item.getDate(),
                item.getDate(),
                item.getIdShift(),
                item.getShiftDescription(),
                item.getStartAt(),
                item.getEndAt(),
                item.getIsDone(),
                item.getDoneAt()
        );
    }

    public CustomEmployeeScheduleLeaderDTO convertCustomEmployeeScheduleLeaderDTO(CustomEmployeeScheduleLeaderModel item) {
        return new CustomEmployeeScheduleLeaderDTO(
                item.getIdDetailEmployeeProject(),
                item.getIdProject(),
                item.getIdEmployee(),
                item.getEmployeeName(),
                item.getIdShift(),
                item.getShiftDescription(),
                item.getStartAt(),
                item.getEndAt(),
                item.getDate(),
                item.getDate(),
                item.getScheduleType(),
                item.getIsOff(),
                item.getStatusAttendance()
        );
    }
}
