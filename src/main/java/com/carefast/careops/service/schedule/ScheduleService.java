package com.carefast.careops.service.schedule;

import com.carefast.careops.model.project.ProjectShiftModel;
import com.carefast.careops.model.schedule.ScheduleModel;
import com.carefast.careops.repository.project.ProjectShiftRepository;
import com.carefast.careops.repository.schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ScheduleService {
    public int dayOff = 4;
    public int morningShiftId = 1;
    public int afternoonShiftId = 2;
    public int nightShiftId = 3;

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ProjectShiftRepository projectShiftRepository;

    public boolean createEmployeeSchedule(String projectCode) {

        Optional<ProjectShiftModel> morningShift = projectShiftRepository.getDetailShiftByProjectId(projectCode, morningShiftId);
        Optional<ProjectShiftModel> afternoonShift = projectShiftRepository.getDetailShiftByProjectId(projectCode, afternoonShiftId);
        Optional<ProjectShiftModel> nightShift = projectShiftRepository.getDetailShiftByProjectId(projectCode, nightShiftId);

        int totalManPower = projectShiftRepository.getTotalEmployeeInProject(projectCode);

        int morningShiftManPower = morningShift.isPresent() ? morningShift.get().getPeopleTotal() : 0;
        int afternoonShiftManPower = afternoonShift.isPresent() ? afternoonShift.get().getPeopleTotal() : 0;
        int nightShiftManPower = nightShift.isPresent() ? nightShift.get().getPeopleTotal() : 0;


        int getYear = LocalDate.now().getMonth() == Month.DECEMBER ? LocalDate.now().plusYears(1).getYear() : LocalDate.now().getYear();
        Month getMonth = LocalDate.now().getMonth().plus(1);

        int month = getMonth.getValue();

        YearMonth yearMonth = YearMonth.of(getYear, getMonth);

        for (var i = 1; i <= totalManPower; i++) {

            var x = i % 7;
            if (yearMonth.lengthOfMonth() == 28) {
                if (i == 1 || x == 1) {

                    // FIRST ROW
                    if (i <= morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelFirstRowTypeOne(projectCode, month, getYear, morningShiftId));
                    } else if (i > morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelFirstRowTypeOne(projectCode, month, getYear, afternoonShiftId));
                    }
                } else {
                    ScheduleModel scheduleExist = scheduleRepository.getLastSchedule();

                    if (i <= morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelTypeOne(scheduleExist, projectCode, month, getYear, morningShiftId));
                    } else if (i <= (afternoonShiftManPower + morningShiftManPower)) {
                        scheduleRepository.save(saveScheduleModelTypeOne(scheduleExist, projectCode, month, getYear, afternoonShiftId));
                    } else if (i <= (nightShiftManPower + afternoonShiftManPower + morningShiftManPower)) {
                        scheduleRepository.save(saveScheduleModelTypeOne(scheduleExist, projectCode, month, getYear, nightShiftId));
                    }
                }
            } else if (yearMonth.lengthOfMonth() == 29) {
                if (i == 1 || x == 1) {

                    // FIRST ROW
                    if (i <= morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelFirstRowTypeTwo(projectCode, month, getYear, morningShiftId));
                    } else if (i > morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelFirstRowTypeTwo(projectCode, month, getYear, afternoonShiftId));
                    }
                } else {
                    ScheduleModel scheduleExist = scheduleRepository.getLastSchedule();

                    if (i <= morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelTypeTwo(scheduleExist, projectCode, month, getYear, morningShiftId));
                    } else if (i > morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelTypeTwo(scheduleExist, projectCode, month, getYear, afternoonShiftId));
                    }
                }
            } else if (yearMonth.lengthOfMonth() == 30) {
                if (i == 1 || x == 1) {

                    // FIRST ROW
                    if (i <= morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelFirstRowTypeThree(projectCode, month, getYear, morningShiftId));
                    } else if (i > morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelFirstRowTypeThree(projectCode, month, getYear, afternoonShiftId));
                    }
                } else {
                    ScheduleModel scheduleExist = scheduleRepository.getLastSchedule();

                    if (i <= morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelTypeThree(scheduleExist, projectCode, month, getYear, morningShiftId));
                    } else if (i > morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelTypeThree(scheduleExist, projectCode, month, getYear, afternoonShiftId));
                    }
                }
            } else {
                if (i == 1 || x == 1) {

                    // FIRST ROW
                    if (i <= morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelFirstRowTypeFour(projectCode, month, getYear, morningShiftId));
                    } else if (i > morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelFirstRowTypeFour(projectCode, month, getYear, afternoonShiftId));
                    }
                } else {
                    ScheduleModel scheduleExist = scheduleRepository.getLastSchedule();

                    if (i <= morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelTypeFour(scheduleExist, projectCode, month, getYear, morningShiftId));
                    } else if (i > morningShiftManPower) {
                        scheduleRepository.save(saveScheduleModelTypeFour(scheduleExist, projectCode, month, getYear, afternoonShiftId));
                    }
                }
            }
        }

        return true;
    }


    // TOTAL OF DATE = 28
    public ScheduleModel saveScheduleModelTypeOne(ScheduleModel item, String projectCode, int month, int year, int shiftId) {
        return new ScheduleModel(
                projectCode,
                month,
                year,
                shiftId,
                item.getDateOne() == dayOff ? dayOff : shiftId,
                item.getDateTwo() == dayOff ? dayOff : shiftId,
                item.getDateThree() == dayOff ? dayOff : shiftId,
                item.getDateFourth() == dayOff ? dayOff : shiftId,
                item.getDateFive() == dayOff ? dayOff : shiftId,
                item.getDateSix() == dayOff ? dayOff : shiftId,
                item.getDateSeven() == dayOff ? dayOff : shiftId,
                item.getDateEight() == dayOff ? dayOff : shiftId,
                item.getDateNine() == dayOff ? dayOff : shiftId,
                item.getDateTen() == dayOff ? dayOff : shiftId,
                item.getDateEleven() == dayOff ? dayOff : shiftId,
                item.getDateTwelve() == dayOff ? dayOff : shiftId,
                item.getDateThirteen() == dayOff ? dayOff : shiftId,
                item.getDateFourteen() == dayOff ? dayOff : shiftId,
                item.getDateFifteen() == dayOff ? dayOff : shiftId,
                item.getDateSixteen() == dayOff ? dayOff : shiftId,
                item.getDateSeventeen() == dayOff ? dayOff : shiftId,
                item.getDateEighteen() == dayOff ? dayOff : shiftId,
                item.getDateNineteen() == dayOff ? dayOff : shiftId,
                item.getDateTwenty() == dayOff ? dayOff : shiftId,
                item.getDateTwentyOne() == dayOff ? dayOff : shiftId,
                item.getDateTwentyTwo() == dayOff ? dayOff : shiftId,
                item.getDateTwentyThree() == dayOff ? dayOff : shiftId,
                item.getDateTwentyFour() == dayOff ? dayOff : shiftId,
                item.getDateTwentyFive() == dayOff ? dayOff : shiftId,
                item.getDateTwentySix() == dayOff ? dayOff : shiftId,
                item.getDateTwentySeven() == dayOff ? dayOff : shiftId,
                LocalDateTime.now()
        );
    }

    // TOTAL OF DATE = 29
    public ScheduleModel saveScheduleModelTypeTwo(ScheduleModel item, String projectCode, int month, int year, int shiftId) {
        return new ScheduleModel(
                projectCode,
                month,
                year,
                shiftId,
                item.getDateOne() == dayOff ? dayOff : shiftId,
                item.getDateTwo() == dayOff ? dayOff : shiftId,
                item.getDateThree() == dayOff ? dayOff : shiftId,
                item.getDateFourth() == dayOff ? dayOff : shiftId,
                item.getDateFive() == dayOff ? dayOff : shiftId,
                item.getDateSix() == dayOff ? dayOff : shiftId,
                item.getDateSeven() == dayOff ? dayOff : shiftId,
                item.getDateEight() == dayOff ? dayOff : shiftId,
                item.getDateNine() == dayOff ? dayOff : shiftId,
                item.getDateTen() == dayOff ? dayOff : shiftId,
                item.getDateEleven() == dayOff ? dayOff : shiftId,
                item.getDateTwelve() == dayOff ? dayOff : shiftId,
                item.getDateThirteen() == dayOff ? dayOff : shiftId,
                item.getDateFourteen() == dayOff ? dayOff : shiftId,
                item.getDateFifteen() == dayOff ? dayOff : shiftId,
                item.getDateSixteen() == dayOff ? dayOff : shiftId,
                item.getDateSeventeen() == dayOff ? dayOff : shiftId,
                item.getDateEighteen() == dayOff ? dayOff : shiftId,
                item.getDateNineteen() == dayOff ? dayOff : shiftId,
                item.getDateTwenty() == dayOff ? dayOff : shiftId,
                item.getDateTwentyOne() == dayOff ? dayOff : shiftId,
                item.getDateTwentyTwo() == dayOff ? dayOff : shiftId,
                item.getDateTwentyThree() == dayOff ? dayOff : shiftId,
                item.getDateTwentyFour() == dayOff ? dayOff : shiftId,
                item.getDateTwentyFive() == dayOff ? dayOff : shiftId,
                item.getDateTwentySix() == dayOff ? dayOff : shiftId,
                item.getDateTwentySeven() == dayOff ? dayOff : shiftId,
                item.getDateTwentyEight() == dayOff ? dayOff : shiftId,
                LocalDateTime.now()
        );
    }

    // TOTAL OF DATE = 30
    public ScheduleModel saveScheduleModelTypeThree(ScheduleModel item, String projectCode, int month, int year, int shiftId) {
        return new ScheduleModel(
                projectCode,
                month,
                year,
                shiftId,
                item.getDateOne() == dayOff ? dayOff : shiftId,
                item.getDateTwo() == dayOff ? dayOff : shiftId,
                item.getDateThree() == dayOff ? dayOff : shiftId,
                item.getDateFourth() == dayOff ? dayOff : shiftId,
                item.getDateFive() == dayOff ? dayOff : shiftId,
                item.getDateSix() == dayOff ? dayOff : shiftId,
                item.getDateSeven() == dayOff ? dayOff : shiftId,
                item.getDateEight() == dayOff ? dayOff : shiftId,
                item.getDateNine() == dayOff ? dayOff : shiftId,
                item.getDateTen() == dayOff ? dayOff : shiftId,
                item.getDateEleven() == dayOff ? dayOff : shiftId,
                item.getDateTwelve() == dayOff ? dayOff : shiftId,
                item.getDateThirteen() == dayOff ? dayOff : shiftId,
                item.getDateFourteen() == dayOff ? dayOff : shiftId,
                item.getDateFifteen() == dayOff ? dayOff : shiftId,
                item.getDateSixteen() == dayOff ? dayOff : shiftId,
                item.getDateSeventeen() == dayOff ? dayOff : shiftId,
                item.getDateEighteen() == dayOff ? dayOff : shiftId,
                item.getDateNineteen() == dayOff ? dayOff : shiftId,
                item.getDateTwenty() == dayOff ? dayOff : shiftId,
                item.getDateTwentyOne() == dayOff ? dayOff : shiftId,
                item.getDateTwentyTwo() == dayOff ? dayOff : shiftId,
                item.getDateTwentyThree() == dayOff ? dayOff : shiftId,
                item.getDateTwentyFour() == dayOff ? dayOff : shiftId,
                item.getDateTwentyFive() == dayOff ? dayOff : shiftId,
                item.getDateTwentySix() == dayOff ? dayOff : shiftId,
                item.getDateTwentySeven() == dayOff ? dayOff : shiftId,
                item.getDateTwentyEight() == dayOff ? dayOff : shiftId,
                item.getDateTwentyNine() == dayOff ? dayOff : shiftId,
                LocalDateTime.now()
        );
    }

    // TOTAL OF DATE = 31
    public ScheduleModel saveScheduleModelTypeFour(ScheduleModel item, String projectCode, int month, int year, int shiftId) {
        return new ScheduleModel(
                projectCode,
                month,
                year,
                shiftId,
                item.getDateOne() == dayOff ? dayOff : shiftId,
                item.getDateTwo() == dayOff ? dayOff : shiftId,
                item.getDateThree() == dayOff ? dayOff : shiftId,
                item.getDateFourth() == dayOff ? dayOff : shiftId,
                item.getDateFive() == dayOff ? dayOff : shiftId,
                item.getDateSix() == dayOff ? dayOff : shiftId,
                item.getDateSeven() == dayOff ? dayOff : shiftId,
                item.getDateEight() == dayOff ? dayOff : shiftId,
                item.getDateNine() == dayOff ? dayOff : shiftId,
                item.getDateTen() == dayOff ? dayOff : shiftId,
                item.getDateEleven() == dayOff ? dayOff : shiftId,
                item.getDateTwelve() == dayOff ? dayOff : shiftId,
                item.getDateThirteen() == dayOff ? dayOff : shiftId,
                item.getDateFourteen() == dayOff ? dayOff : shiftId,
                item.getDateFifteen() == dayOff ? dayOff : shiftId,
                item.getDateSixteen() == dayOff ? dayOff : shiftId,
                item.getDateSeventeen() == dayOff ? dayOff : shiftId,
                item.getDateEighteen() == dayOff ? dayOff : shiftId,
                item.getDateNineteen() == dayOff ? dayOff : shiftId,
                item.getDateTwenty() == dayOff ? dayOff : shiftId,
                item.getDateTwentyOne() == dayOff ? dayOff : shiftId,
                item.getDateTwentyTwo() == dayOff ? dayOff : shiftId,
                item.getDateTwentyThree() == dayOff ? dayOff : shiftId,
                item.getDateTwentyFour() == dayOff ? dayOff : shiftId,
                item.getDateTwentyFive() == dayOff ? dayOff : shiftId,
                item.getDateTwentySix() == dayOff ? dayOff : shiftId,
                item.getDateTwentySeven() == dayOff ? dayOff : shiftId,
                item.getDateTwentyEight() == dayOff ? dayOff : shiftId,
                item.getDateTwentyNine() == dayOff ? dayOff : shiftId,
                item.getDateThirty() == dayOff ? dayOff : shiftId,
                LocalDateTime.now()
        );
    }

    // TOTAL OF DATE = 28 ROW1
    public ScheduleModel saveScheduleModelFirstRowTypeOne(String projectCode, int month, int year, int shiftId) {
        return new ScheduleModel(
                projectCode,
                month,
                year,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                LocalDateTime.now()
        );
    }

    // TOTAL OF DATE = 29 ROW1
    public ScheduleModel saveScheduleModelFirstRowTypeTwo(String projectCode, int month, int year, int shiftId) {
        return new ScheduleModel(
                projectCode,
                month,
                year,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                LocalDateTime.now()
        );
    }

    // TOTAL OF DATE = 30 ROW1
    public ScheduleModel saveScheduleModelFirstRowTypeThree(String projectCode, int month, int year, int shiftId) {
        return new ScheduleModel(
                projectCode,
                month,
                year,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                LocalDateTime.now()
        );
    }

    // TOTAL OF DATE = 31 ROW1
    public ScheduleModel saveScheduleModelFirstRowTypeFour(String projectCode, int month, int year, int shiftId) {
        return new ScheduleModel(
                projectCode,
                month,
                year,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                shiftId,
                dayOff,
                shiftId,
                shiftId,
                LocalDateTime.now()
        );
    }

}
