package com.carefast.careops.model.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_schedule")
public class ScheduleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private int scheduleId;
    @Column(name = "project_code")
    private String projectCode;
    @Column(name = "month")
    private int month;
    @Column(name = "year")
    private int year;
    @Column(name = "date_1")
    private int dateOne;
    @Column(name = "date_2")
    private int dateTwo;
    @Column(name = "date_3")
    private int dateThree;
    @Column(name = "date_4")
    private int dateFourth;
    @Column(name = "date_5")
    private int dateFive;
    @Column(name = "date_6")
    private int dateSix;
    @Column(name = "date_7")
    private int dateSeven;
    @Column(name = "date_8")
    private int dateEight;
    @Column(name = "date_9")
    private int dateNine;
    @Column(name = "date_10")
    private int dateTen;
    @Column(name = "date_11")
    private int dateEleven;
    @Column(name = "date_12")
    private int dateTwelve;
    @Column(name = "date_13")
    private int dateThirteen;
    @Column(name = "date_14")
    private int dateFourteen;
    @Column(name = "date_15")
    private int dateFifteen;
    @Column(name = "date_16")
    private int dateSixteen;
    @Column(name = "date_17")
    private int dateSeventeen;
    @Column(name = "date_18")
    private int dateEighteen;
    @Column(name = "date_19")
    private int dateNineteen;
    @Column(name = "date_20")
    private int dateTwenty;
    @Column(name = "date_21")
    private int dateTwentyOne;
    @Column(name = "date_22")
    private int dateTwentyTwo;
    @Column(name = "date_23")
    private int dateTwentyThree;
    @Column(name = "date_24")
    private int dateTwentyFour;
    @Column(name = "date_25")
    private int dateTwentyFive;
    @Column(name = "date_26")
    private int dateTwentySix;
    @Column(name = "date_27")
    private int dateTwentySeven;
    @Column(name = "date_28")
    private int dateTwentyEight;
    @Column(name = "date_29", nullable = true)
    private Integer dateTwentyNine = null;
    @Column(name = "date_30", nullable = true)
    private Integer dateThirty = null;
    @Column(name = "date_31", nullable = true)
    private Integer dateThirtyOne = null;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ScheduleModel() {
    }

    public ScheduleModel(String projectCode, int month, int year, int dateOne, int dateTwo, int dateThree, int dateFourth, int dateFive, int dateSix, int dateSeven, int dateEight, int dateNine, int dateTen, int dateEleven, int dateTwelve, int dateThirteen, int dateFourteen, int dateFifteen, int dateSixteen, int dateSeventeen, int dateEighteen, int dateNineteen, int dateTwenty, int dateTwentyOne, int dateTwentyTwo, int dateTwentyThree, int dateTwentyFour, int dateTwentyFive, int dateTwentySix, int dateTwentySeven, int dateTwentyEight, LocalDateTime createdAt) {
        this.projectCode = projectCode;
        this.month = month;
        this.year = year;
        this.dateOne = dateOne;
        this.dateTwo = dateTwo;
        this.dateThree = dateThree;
        this.dateFourth = dateFourth;
        this.dateFive = dateFive;
        this.dateSix = dateSix;
        this.dateSeven = dateSeven;
        this.dateEight = dateEight;
        this.dateNine = dateNine;
        this.dateTen = dateTen;
        this.dateEleven = dateEleven;
        this.dateTwelve = dateTwelve;
        this.dateThirteen = dateThirteen;
        this.dateFourteen = dateFourteen;
        this.dateFifteen = dateFifteen;
        this.dateSixteen = dateSixteen;
        this.dateSeventeen = dateSeventeen;
        this.dateEighteen = dateEighteen;
        this.dateNineteen = dateNineteen;
        this.dateTwenty = dateTwenty;
        this.dateTwentyOne = dateTwentyOne;
        this.dateTwentyTwo = dateTwentyTwo;
        this.dateTwentyThree = dateTwentyThree;
        this.dateTwentyFour = dateTwentyFour;
        this.dateTwentyFive = dateTwentyFive;
        this.dateTwentySix = dateTwentySix;
        this.dateTwentySeven = dateTwentySeven;
        this.dateTwentyEight = dateTwentyEight;
        this.createdAt = createdAt;
    }

    public ScheduleModel(String projectCode, int month, int year, int dateOne, int dateTwo, int dateThree, int dateFourth, int dateFive, int dateSix, int dateSeven, int dateEight, int dateNine, int dateTen, int dateEleven, int dateTwelve, int dateThirteen, int dateFourteen, int dateFifteen, int dateSixteen, int dateSeventeen, int dateEighteen, int dateNineteen, int dateTwenty, int dateTwentyOne, int dateTwentyTwo, int dateTwentyThree, int dateTwentyFour, int dateTwentyFive, int dateTwentySix, int dateTwentySeven, int dateTwentyEight, Integer dateTwentyNine, LocalDateTime createdAt) {
        this.projectCode = projectCode;
        this.month = month;
        this.year = year;
        this.dateOne = dateOne;
        this.dateTwo = dateTwo;
        this.dateThree = dateThree;
        this.dateFourth = dateFourth;
        this.dateFive = dateFive;
        this.dateSix = dateSix;
        this.dateSeven = dateSeven;
        this.dateEight = dateEight;
        this.dateNine = dateNine;
        this.dateTen = dateTen;
        this.dateEleven = dateEleven;
        this.dateTwelve = dateTwelve;
        this.dateThirteen = dateThirteen;
        this.dateFourteen = dateFourteen;
        this.dateFifteen = dateFifteen;
        this.dateSixteen = dateSixteen;
        this.dateSeventeen = dateSeventeen;
        this.dateEighteen = dateEighteen;
        this.dateNineteen = dateNineteen;
        this.dateTwenty = dateTwenty;
        this.dateTwentyOne = dateTwentyOne;
        this.dateTwentyTwo = dateTwentyTwo;
        this.dateTwentyThree = dateTwentyThree;
        this.dateTwentyFour = dateTwentyFour;
        this.dateTwentyFive = dateTwentyFive;
        this.dateTwentySix = dateTwentySix;
        this.dateTwentySeven = dateTwentySeven;
        this.dateTwentyEight = dateTwentyEight;
        this.dateTwentyNine = dateTwentyNine;
        this.createdAt = createdAt;
    }

    public ScheduleModel(String projectCode, int month, int year, int dateOne, int dateTwo, int dateThree, int dateFourth, int dateFive, int dateSix, int dateSeven, int dateEight, int dateNine, int dateTen, int dateEleven, int dateTwelve, int dateThirteen, int dateFourteen, int dateFifteen, int dateSixteen, int dateSeventeen, int dateEighteen, int dateNineteen, int dateTwenty, int dateTwentyOne, int dateTwentyTwo, int dateTwentyThree, int dateTwentyFour, int dateTwentyFive, int dateTwentySix, int dateTwentySeven, int dateTwentyEight, Integer dateTwentyNine, Integer dateThirty, LocalDateTime createdAt) {
        this.projectCode = projectCode;
        this.month = month;
        this.year = year;
        this.dateOne = dateOne;
        this.dateTwo = dateTwo;
        this.dateThree = dateThree;
        this.dateFourth = dateFourth;
        this.dateFive = dateFive;
        this.dateSix = dateSix;
        this.dateSeven = dateSeven;
        this.dateEight = dateEight;
        this.dateNine = dateNine;
        this.dateTen = dateTen;
        this.dateEleven = dateEleven;
        this.dateTwelve = dateTwelve;
        this.dateThirteen = dateThirteen;
        this.dateFourteen = dateFourteen;
        this.dateFifteen = dateFifteen;
        this.dateSixteen = dateSixteen;
        this.dateSeventeen = dateSeventeen;
        this.dateEighteen = dateEighteen;
        this.dateNineteen = dateNineteen;
        this.dateTwenty = dateTwenty;
        this.dateTwentyOne = dateTwentyOne;
        this.dateTwentyTwo = dateTwentyTwo;
        this.dateTwentyThree = dateTwentyThree;
        this.dateTwentyFour = dateTwentyFour;
        this.dateTwentyFive = dateTwentyFive;
        this.dateTwentySix = dateTwentySix;
        this.dateTwentySeven = dateTwentySeven;
        this.dateTwentyEight = dateTwentyEight;
        this.dateTwentyNine = dateTwentyNine;
        this.dateThirty = dateThirty;
        this.createdAt = createdAt;
    }

    public ScheduleModel(String projectCode, int month, int year, int dateOne, int dateTwo, int dateThree, int dateFourth, int dateFive, int dateSix, int dateSeven, int dateEight, int dateNine, int dateTen, int dateEleven, int dateTwelve, int dateThirteen, int dateFourteen, int dateFifteen, int dateSixteen, int dateSeventeen, int dateEighteen, int dateNineteen, int dateTwenty, int dateTwentyOne, int dateTwentyTwo, int dateTwentyThree, int dateTwentyFour, int dateTwentyFive, int dateTwentySix, int dateTwentySeven, int dateTwentyEight, Integer dateTwentyNine, Integer dateThirty, Integer dateThirtyOne, LocalDateTime createdAt) {
        this.projectCode = projectCode;
        this.month = month;
        this.year = year;
        this.dateOne = dateOne;
        this.dateTwo = dateTwo;
        this.dateThree = dateThree;
        this.dateFourth = dateFourth;
        this.dateFive = dateFive;
        this.dateSix = dateSix;
        this.dateSeven = dateSeven;
        this.dateEight = dateEight;
        this.dateNine = dateNine;
        this.dateTen = dateTen;
        this.dateEleven = dateEleven;
        this.dateTwelve = dateTwelve;
        this.dateThirteen = dateThirteen;
        this.dateFourteen = dateFourteen;
        this.dateFifteen = dateFifteen;
        this.dateSixteen = dateSixteen;
        this.dateSeventeen = dateSeventeen;
        this.dateEighteen = dateEighteen;
        this.dateNineteen = dateNineteen;
        this.dateTwenty = dateTwenty;
        this.dateTwentyOne = dateTwentyOne;
        this.dateTwentyTwo = dateTwentyTwo;
        this.dateTwentyThree = dateTwentyThree;
        this.dateTwentyFour = dateTwentyFour;
        this.dateTwentyFive = dateTwentyFive;
        this.dateTwentySix = dateTwentySix;
        this.dateTwentySeven = dateTwentySeven;
        this.dateTwentyEight = dateTwentyEight;
        this.dateTwentyNine = dateTwentyNine;
        this.dateThirty = dateThirty;
        this.dateThirtyOne = dateThirtyOne;
        this.createdAt = createdAt;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDateOne() {
        return dateOne;
    }

    public void setDateOne(int dateOne) {
        this.dateOne = dateOne;
    }

    public int getDateTwo() {
        return dateTwo;
    }

    public void setDateTwo(int dateTwo) {
        this.dateTwo = dateTwo;
    }

    public int getDateThree() {
        return dateThree;
    }

    public void setDateThree(int dateThree) {
        this.dateThree = dateThree;
    }

    public int getDateFourth() {
        return dateFourth;
    }

    public void setDateFourth(int dateFourth) {
        this.dateFourth = dateFourth;
    }

    public int getDateFive() {
        return dateFive;
    }

    public void setDateFive(int dateFive) {
        this.dateFive = dateFive;
    }

    public int getDateSix() {
        return dateSix;
    }

    public void setDateSix(int dateSix) {
        this.dateSix = dateSix;
    }

    public int getDateSeven() {
        return dateSeven;
    }

    public void setDateSeven(int dateSeven) {
        this.dateSeven = dateSeven;
    }

    public int getDateEight() {
        return dateEight;
    }

    public void setDateEight(int dateEight) {
        this.dateEight = dateEight;
    }

    public int getDateNine() {
        return dateNine;
    }

    public void setDateNine(int dateNine) {
        this.dateNine = dateNine;
    }

    public int getDateTen() {
        return dateTen;
    }

    public void setDateTen(int dateTen) {
        this.dateTen = dateTen;
    }

    public int getDateEleven() {
        return dateEleven;
    }

    public void setDateEleven(int dateEleven) {
        this.dateEleven = dateEleven;
    }

    public int getDateTwelve() {
        return dateTwelve;
    }

    public void setDateTwelve(int dateTwelve) {
        this.dateTwelve = dateTwelve;
    }

    public int getDateThirteen() {
        return dateThirteen;
    }

    public void setDateThirteen(int dateThirteen) {
        this.dateThirteen = dateThirteen;
    }

    public int getDateFourteen() {
        return dateFourteen;
    }

    public void setDateFourteen(int dateFourteen) {
        this.dateFourteen = dateFourteen;
    }

    public int getDateFifteen() {
        return dateFifteen;
    }

    public void setDateFifteen(int dateFifteen) {
        this.dateFifteen = dateFifteen;
    }

    public int getDateSixteen() {
        return dateSixteen;
    }

    public void setDateSixteen(int dateSixteen) {
        this.dateSixteen = dateSixteen;
    }

    public int getDateSeventeen() {
        return dateSeventeen;
    }

    public void setDateSeventeen(int dateSeventeen) {
        this.dateSeventeen = dateSeventeen;
    }

    public int getDateEighteen() {
        return dateEighteen;
    }

    public void setDateEighteen(int dateEighteen) {
        this.dateEighteen = dateEighteen;
    }

    public int getDateNineteen() {
        return dateNineteen;
    }

    public void setDateNineteen(int dateNineteen) {
        this.dateNineteen = dateNineteen;
    }

    public int getDateTwenty() {
        return dateTwenty;
    }

    public void setDateTwenty(int dateTwenty) {
        this.dateTwenty = dateTwenty;
    }

    public int getDateTwentyOne() {
        return dateTwentyOne;
    }

    public void setDateTwentyOne(int dateTwentyOne) {
        this.dateTwentyOne = dateTwentyOne;
    }

    public int getDateTwentyTwo() {
        return dateTwentyTwo;
    }

    public void setDateTwentyTwo(int dateTwentyTwo) {
        this.dateTwentyTwo = dateTwentyTwo;
    }

    public int getDateTwentyThree() {
        return dateTwentyThree;
    }

    public void setDateTwentyThree(int dateTwentyThree) {
        this.dateTwentyThree = dateTwentyThree;
    }

    public int getDateTwentyFour() {
        return dateTwentyFour;
    }

    public void setDateTwentyFour(int dateTwentyFour) {
        this.dateTwentyFour = dateTwentyFour;
    }

    public int getDateTwentyFive() {
        return dateTwentyFive;
    }

    public void setDateTwentyFive(int dateTwentyFive) {
        this.dateTwentyFive = dateTwentyFive;
    }

    public int getDateTwentySix() {
        return dateTwentySix;
    }

    public void setDateTwentySix(int dateTwentySix) {
        this.dateTwentySix = dateTwentySix;
    }

    public int getDateTwentySeven() {
        return dateTwentySeven;
    }

    public void setDateTwentySeven(int dateTwentySeven) {
        this.dateTwentySeven = dateTwentySeven;
    }

    public int getDateTwentyEight() {
        return dateTwentyEight;
    }

    public void setDateTwentyEight(int dateTwentyEight) {
        this.dateTwentyEight = dateTwentyEight;
    }

    public Integer getDateTwentyNine() {
        return dateTwentyNine;
    }

    public void setDateTwentyNine(Integer dateTwentyNine) {
        this.dateTwentyNine = dateTwentyNine == 0 ? null : dateTwentyNine;
    }

    public Integer getDateThirty() {
        return dateThirty;
    }

    public void setDateThirty(Integer dateThirty) {
        this.dateThirty = dateThirty == 0 ? null : dateThirty;
    }

    public Integer getDateThirtyOne() {
        return dateThirtyOne;
    }

    public void setDateThirtyOne(Integer dateThirtyOne) {
        this.dateThirtyOne = dateThirtyOne == 0 ? null : dateThirtyOne;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
