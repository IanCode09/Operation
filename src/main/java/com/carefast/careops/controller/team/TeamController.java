package com.carefast.careops.controller.team;

import com.carefast.careops.dto.team.TeamOperatorDTO;
import com.carefast.careops.dto.team.TeamDTO;
import com.carefast.careops.response.DataResponse;
import com.carefast.careops.response.HandlerResponse;
import com.carefast.careops.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/project", produces = {"application/json"})
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/team/operator")
    public void getProjectTeam(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam String projectId,
                                  @RequestParam int employeeId,
                                  @RequestParam int shiftId) throws IOException {

        List<TeamOperatorDTO> teamOperatorDTO = teamService.getEmployeeTeam(projectId, employeeId, shiftId);
        DataResponse<List<TeamOperatorDTO>> data = new DataResponse<>();
        data.setData(teamOperatorDTO);

        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/team/operator/date")
    public void getProjectTeamByDate(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam String projectId,
                                     @RequestParam int employeeId,
                                     @RequestParam int shiftId,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws IOException {

        List<TeamOperatorDTO> teamOperatorDTO = teamService.getEmployeeTeamByDate(projectId, employeeId, shiftId, date);
        DataResponse<List<TeamOperatorDTO>> data = new DataResponse<>();
        data.setData(teamOperatorDTO);

        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("/spv/team")
    public void getSpvTeam(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam String projectId,
                                     @RequestParam int employeeId, @RequestParam int shiftId) throws IOException {

        List<TeamDTO> teamDTO = teamService.getTeamSpv(projectId, employeeId, shiftId);
        DataResponse<List<TeamDTO>> data = new DataResponse<>();
        data.setData(teamDTO);

        HandlerResponse.responseSuccessWithData(response, data);
    }

    @GetMapping("chief-spv/team")
    public void getChiefSupervisorTeam(HttpServletRequest request, HttpServletResponse response, @RequestParam String projectId) throws IOException {

        List<TeamDTO> teamDTO = teamService.getTeamChiefSpv(projectId);
        DataResponse<List<TeamDTO>> data = new DataResponse<>();
        data.setData(teamDTO);

        HandlerResponse.responseSuccessWithData(response, data);
    }
}
