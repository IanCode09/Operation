package com.carefast.careops.service.client;

import com.carefast.careops.dto.schedule.CustomEmployeeDetailListProjectDTO;
import com.carefast.careops.model.client.ClientModel;
import com.carefast.careops.model.project.CustomEmployeeDetailListProjectModel;
import com.carefast.careops.repository.client.ClientRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClientNotificationService {
    public static final int NOTIF_COUNT = 10;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    public Slice<CustomEmployeeDetailListProjectDTO> getNotification(int clientId, int page) {
        Pageable pagination = PageRequest.of(page, NOTIF_COUNT);
        Optional<ClientModel> client = clientRepository.findById(clientId);

        return employeeProjectRepository.getEmployeeScheduleIsDone(client.get().getProjectCode(), pagination)
                .map(this::convertCustomEmployeeDetailListProjectDTO);
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
}
