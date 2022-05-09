package com.carefast.careops.model.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_schedule_pengawas")
public class SchedulePengawasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule_pengawas ")
    private int idSchedulePengawas;
    @Column(name = "id_employee")
    private int idEmployee;
    @Column(name = "project_code")
    private String projectCode;
    @Column(name = "month_pengawas")
    private int monthPengawas;
    @Column(name = "year_pengawas")
    private int yearPengawas;
    @Column(name = "date_1_pengawas")
    private int dateOne;
    @Column(name = "date_2_pengawas")
    private int dateTwo;
    @Column(name = "date_3_pengawas")
    private int dateThree;
    @Column(name = "date_4_pengawas")
    private int dateFourth;
    @Column(name = "date_5_pengawas")
    private int dateFive;
    @Column(name = "date_6_pengawas")
    private int dateSix;
    @Column(name = "date_7_pengawas")
    private int dateSeven;
    @Column(name = "date_8_pengawas")
    private int dateEight;
    @Column(name = "date_9_pengawas")
    private int dateNine;
    @Column(name = "date_10_pengawas")
    private int dateTen;
    @Column(name = "date_11_pengawas")
    private int dateEleven;
    @Column(name = "date_12_pengawas")
    private int dateTwelve;
    @Column(name = "date_13_pengawas")
    private int dateThirteen;
    @Column(name = "date_14_pengawas")
    private int dateFourteen;
    @Column(name = "date_15_pengawas")
    private int dateFifteen;
    @Column(name = "date_16_pengawas")
    private int dateSixteen;
    @Column(name = "date_17_pengawas")
    private int dateSeventeen;
    @Column(name = "date_18_pengawas")
    private int dateEighteen;
    @Column(name = "date_19_pengawas")
    private int dateNineteen;
    @Column(name = "date_20_pengawas")
    private int dateTwenty;
    @Column(name = "date_21_pengawas")
    private int dateTwentyOne;
    @Column(name = "date_22_pengawas")
    private int dateTwentyTwo;
    @Column(name = "date_23_pengawas")
    private int dateTwentyThree;
    @Column(name = "date_24_pengawas")
    private int dateTwentyFour;
    @Column(name = "date_25_pengawas")
    private int dateTwentyFive;
    @Column(name = "date_26_pengawas")
    private int dateTwentySix;
    @Column(name = "date_27_pengawas")
    private int dateTwentySeven;
    @Column(name = "date_28_pengawas")
    private int dateTwentyEight;
    @Column(name = "date_29_pengawas", nullable = true)
    private Integer dateTwentyNine = null;
    @Column(name = "date_30_pengawas", nullable = true)
    private Integer dateThirty = null;
    @Column(name = "date_31_pengawas", nullable = true)
    private Integer dateThirtyOne = null;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at_pengawas")
    private LocalDateTime createdAtPengawas;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at_pengawas")
    private LocalDateTime updatedAtPengawas;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "updated_by_admin")
    private Integer updatedByAdmin;

    public int getIdSchedulePengawas() {
        return idSchedulePengawas;
    }

    public void setIdSchedulePengawas(int idSchedulePengawas) {
        this.idSchedulePengawas = idSchedulePengawas;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public int getMonthPengawas() {
        return monthPengawas;
    }

    public void setMonthPengawas(int monthPengawas) {
        this.monthPengawas = monthPengawas;
    }

    public int getYearPengawas() {
        return yearPengawas;
    }

    public void setYearPengawas(int yearPengawas) {
        this.yearPengawas = yearPengawas;
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
        this.dateTwentyNine = dateTwentyNine;
    }

    public Integer getDateThirty() {
        return dateThirty;
    }

    public void setDateThirty(Integer dateThirty) {
        this.dateThirty = dateThirty;
    }

    public Integer getDateThirtyOne() {
        return dateThirtyOne;
    }

    public void setDateThirtyOne(Integer dateThirtyOne) {
        this.dateThirtyOne = dateThirtyOne;
    }

    public LocalDateTime getCreatedAtPengawas() {
        return createdAtPengawas;
    }

    public void setCreatedAtPengawas(LocalDateTime createdAtPengawas) {
        this.createdAtPengawas = createdAtPengawas;
    }

    public LocalDateTime getUpdatedAtPengawas() {
        return updatedAtPengawas;
    }

    public void setUpdatedAtPengawas(LocalDateTime updatedAtPengawas) {
        this.updatedAtPengawas = updatedAtPengawas;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getUpdatedByAdmin() {
        return updatedByAdmin;
    }

    public void setUpdatedByAdmin(Integer updatedByAdmin) {
        this.updatedByAdmin = updatedByAdmin;
    }
}
