package com.carefast.careops.utils;

import com.carefast.careops.model.schedule.SchedulePengawasModel;
import com.carefast.careops.model.schedule.ScheduleModel;
import com.carefast.careops.repository.schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConvertDateUtils {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public int validateDate(ScheduleModel scheduleExist) {
        Integer result = null;
        int dayOfMonth = LocalDate.now().getDayOfMonth();

        switch (dayOfMonth) {
            case 1:
                result = scheduleExist.getDateOne();
                break;
            case 2:
                result = scheduleExist.getDateTwo();
                break;
            case 3:
                result = scheduleExist.getDateThree();
                break;
            case 4:
                result = scheduleExist.getDateFourth();
                break;
            case 5:
                result = scheduleExist.getDateFive();
                break;
            case 6:
                result = scheduleExist.getDateSix();
                break;
            case 7:
                result = scheduleExist.getDateSeven();
                break;
            case 8:
                result = scheduleExist.getDateEight();
                break;
            case 9:
                result = scheduleExist.getDateNine();
                break;
            case 10:
                result = scheduleExist.getDateTen();
                break;
            case 11:
                result = scheduleExist.getDateEleven();
                break;
            case 12:
                result = scheduleExist.getDateTwelve();
                break;
            case 13:
                result = scheduleExist.getDateThirteen();
                break;
            case 14:
                result = scheduleExist.getDateFourteen();
                break;
            case 15:
                result = scheduleExist.getDateFifteen();
                break;
            case 16:
                result = scheduleExist.getDateSixteen();
                break;
            case 17:
                result = scheduleExist.getDateSeventeen();
                break;
            case 18:
                result = scheduleExist.getDateEighteen();
                break;
            case 19:
                result = scheduleExist.getDateNineteen();
                break;
            case 20:
                result = scheduleExist.getDateTwenty();
                break;
            case 21:
                result = scheduleExist.getDateTwentyOne();
                break;
            case 22:
                result = scheduleExist.getDateTwentyTwo();
                break;
            case 23:
                result = scheduleExist.getDateTwentyThree();
                break;
            case 24:
                result = scheduleExist.getDateTwentyFour();
                break;
            case 25:
                result = scheduleExist.getDateTwentyFive();
                break;
            case 26:
                result = scheduleExist.getDateTwentySix();
                break;
            case 27:
                result = scheduleExist.getDateTwentySeven();
                break;
            case 28:
                result = scheduleExist.getDateTwentyEight();
                break;
            case 29:
                result = scheduleExist.getDateTwentyNine();
                break;
            case 30:
                result = scheduleExist.getDateThirty();
                break;
            case 31:
                result = scheduleExist.getDateThirtyOne();
                break;
        }

        return result;
    }

    public int validateDate(SchedulePengawasModel scheduleExist) {
        Integer result = null;
        int dayOfMonth = LocalDate.now().getDayOfMonth();

        switch (dayOfMonth) {
            case 1:
                result = scheduleExist.getDateOne();
                break;
            case 2:
                result = scheduleExist.getDateTwo();
                break;
            case 3:
                result = scheduleExist.getDateThree();
                break;
            case 4:
                result = scheduleExist.getDateFourth();
                break;
            case 5:
                result = scheduleExist.getDateFive();
                break;
            case 6:
                result = scheduleExist.getDateSix();
                break;
            case 7:
                result = scheduleExist.getDateSeven();
                break;
            case 8:
                result = scheduleExist.getDateEight();
                break;
            case 9:
                result = scheduleExist.getDateNine();
                break;
            case 10:
                result = scheduleExist.getDateTen();
                break;
            case 11:
                result = scheduleExist.getDateEleven();
                break;
            case 12:
                result = scheduleExist.getDateTwelve();
                break;
            case 13:
                result = scheduleExist.getDateThirteen();
                break;
            case 14:
                result = scheduleExist.getDateFourteen();
                break;
            case 15:
                result = scheduleExist.getDateFifteen();
                break;
            case 16:
                result = scheduleExist.getDateSixteen();
                break;
            case 17:
                result = scheduleExist.getDateSeventeen();
                break;
            case 18:
                result = scheduleExist.getDateEighteen();
                break;
            case 19:
                result = scheduleExist.getDateNineteen();
                break;
            case 20:
                result = scheduleExist.getDateTwenty();
                break;
            case 21:
                result = scheduleExist.getDateTwentyOne();
                break;
            case 22:
                result = scheduleExist.getDateTwentyTwo();
                break;
            case 23:
                result = scheduleExist.getDateTwentyThree();
                break;
            case 24:
                result = scheduleExist.getDateTwentyFour();
                break;
            case 25:
                result = scheduleExist.getDateTwentyFive();
                break;
            case 26:
                result = scheduleExist.getDateTwentySix();
                break;
            case 27:
                result = scheduleExist.getDateTwentySeven();
                break;
            case 28:
                result = scheduleExist.getDateTwentyEight();
                break;
            case 29:
                result = scheduleExist.getDateTwentyNine();
                break;
            case 30:
                result = scheduleExist.getDateThirty();
                break;
            case 31:
                result = scheduleExist.getDateThirtyOne();
                break;
        }

        return result;
    }
}
