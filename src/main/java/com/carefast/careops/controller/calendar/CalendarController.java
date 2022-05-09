package com.carefast.careops.controller.calendar;

import com.carefast.careops.dto.schedule.CustomEmployeeDetailListProjectDTO;
import com.carefast.careops.dto.schedule.CustomEmployeeScheduleLeaderDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.calendar.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/calendar", produces = {"application/json"})
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/employee")
    public void getEmployeeCalendar(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam int employeeId,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    @RequestParam("page") int page) throws IOException {

        Slice<CustomEmployeeDetailListProjectDTO> customEmployeeDetailListProjectDTO = calendarService.getEmployeeCalendar(employeeId, date, page);
        DataResponse<Slice<CustomEmployeeDetailListProjectDTO>> data = new DataResponse<>();
        data.setData(customEmployeeDetailListProjectDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/employee/non-operator")
    public void getEmployeeNonOperatorCalendar(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam int employeeId,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                               @RequestParam("page") int page) throws IOException { 

        Slice<CustomEmployeeScheduleLeaderDTO> customEmployeeScheduleLeaderDTO = calendarService.getEmployeeNonOperatorCalendar(employeeId, date, page);
        DataResponse<Slice<CustomEmployeeScheduleLeaderDTO>> data = new DataResponse<>();
        data.setData(customEmployeeScheduleLeaderDTO);
        HandlerResponse.responseSuccessWithData(response, data);
    }
}
