package com.carefast.careops.service.barcode;

import com.carefast.careops.dto.employee.response.EmployeeBarcodeDTO;
import com.carefast.careops.model.barcode.EmployeeBarcodeModel;
import com.carefast.careops.model.project.EmployeeDetailListProjectModel;
import com.carefast.careops.model.project.ProjectLocationModel;
import com.carefast.careops.model.project.ProjectShiftModel;
import com.carefast.careops.model.schedule.EmployeeScheduleModel;
import com.carefast.careops.model.schedule.ScheduleModel;
import com.carefast.careops.repository.barcode.EmployeeBarcodeRepository;
import com.carefast.careops.repository.employee.EmployeeProjectRepository;
import com.carefast.careops.repository.employee.EmployeeRepository;
import com.carefast.careops.repository.project.ProjectLocationRepository;
import com.carefast.careops.repository.project.ProjectShiftRepository;
import com.carefast.careops.repository.schedule.EmployeeScheduleRepository;
import com.carefast.careops.repository.schedule.ScheduleRepository;
import com.carefast.careops.utils.BarcodeUtils;
import com.carefast.careops.utils.ConvertDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BarcodeService {

    public static final int SUCCESS = 200;
    public static final int NOT_FOUND = 404;
    public static final int DAY_OFF = 4;

    @Autowired
    private EmployeeBarcodeRepository employeeBarcodeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private ProjectLocationRepository projectLocationRepository;
    @Autowired
    private ProjectShiftRepository projectShiftRepository;
    @Autowired
    private EmployeeScheduleRepository employeeScheduleRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ConvertDateUtils convertDateUtils;

    public int generateQRKey(int employeeId, String projectCode) {
        Optional<EmployeeScheduleModel> scheduleModel = employeeScheduleRepository.getEmployeeSchedule(employeeId, projectCode, LocalDate.now());

        if (scheduleModel.isPresent()) {
            Optional<ScheduleModel> scheduleExist = scheduleRepository.findByScheduleId(scheduleModel.get().getScheduleId());

            int shiftId = convertDateUtils.validateDate(scheduleExist.get());

            if (shiftId != 4) {
                Optional<ProjectShiftModel> projectShiftModel = projectShiftRepository.getDetailShiftByProjectId(projectCode, shiftId);

                String qrName = BarcodeUtils.generateQRCodeSignature(employeeId, projectCode);

                EmployeeBarcodeModel employeeBarcodeModel = new EmployeeBarcodeModel();
                employeeBarcodeModel.setEmployeeId(employeeId);
                employeeBarcodeModel.setProjectCode(projectCode);
                employeeBarcodeModel.setBarcodeKey(qrName);
                employeeBarcodeModel.setShiftId(shiftId);
                employeeBarcodeModel.setCheckIn(projectShiftModel.get().getStartAt());
                employeeBarcodeModel.setCheckOut(projectShiftModel.get().getEndAt());
                employeeBarcodeModel.setCreatedAt(LocalDateTime.now());

                employeeBarcodeRepository.save(employeeBarcodeModel);

                return SUCCESS;
            }

            return DAY_OFF;
        }

        return NOT_FOUND;
    }

    public EmployeeBarcodeDTO getEmployeeBarcode(int employeeId, String projectCode) {
        Optional<EmployeeBarcodeModel> employeeBarcodeModel = employeeBarcodeRepository.getEmployeeBarcode(employeeId, projectCode);

        if (employeeBarcodeModel.isEmpty()) {
            return null;
        }

        return convertEmployeeBarcodeDTO(employeeBarcodeModel.get());
    }

    public EmployeeBarcodeDTO convertEmployeeBarcodeDTO(EmployeeBarcodeModel item) {
        EmployeeBarcodeDTO employeeBarcodeDTO = new EmployeeBarcodeDTO(
                item.getIdBarcode(),
                item.getEmployeeId(),
                item.getProjectCode(),
                item.getBarcodeKey(),
                item.getCheckIn(),
                item.getCheckOut()
        );

        return employeeBarcodeDTO;
    }
}
