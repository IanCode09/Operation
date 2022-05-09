package com.carefast.careops.utils;

import org.springframework.stereotype.Service;

@Service
public class JobLevelValidate {

    public String OPERATOR = "Operator";
    public String TEAM_LEADER = "Team Leader";
    public String SUPERVISOR = "Supervisor";
    public String CHIEF_SUPERVISOR = "Chief Supervisor";

    public String jobLevel(String jobName) {
        String level = "";

        switch (jobName) {
            case "CC-CALL-CENTER":
                level = OPERATOR;
                break;
            case "CAR_WASH":
                level = OPERATOR;
                break;
            case "CS-CLEANER-COV19":
                level = OPERATOR;
                break;
            case "CS_GARBAGE_TEAM":
                level = OPERATOR;
                break;
            case "CS_GREASTRAP":
                level = OPERATOR;
                break;
            case "CS_POOL_ATTENDENT":
                level = OPERATOR;
                break;
            case "CS_PUBLIC_AREA":
                level = OPERATOR;
                break;
            case "CS-RELIVER":
                level = OPERATOR;
                break;
            case "CS_SENIOR_GARDENER":
                level = OPERATOR;
                break;
            case "CS_TEAM_MALAM":
                level = OPERATOR;
                break;
            case "CS-ADMIN":
                level = OPERATOR;
                break;
            case "CLEANER":
                level = OPERATOR;
                break;
            case "CS-GARDENER":
                level = OPERATOR;
                break;
            case "CS-GONDOLA":
                level = OPERATOR;
                break;
            case "CS-JR-TOILET_ATTENDANT":
                level = OPERATOR;
                break;
            case "CS-LOGISTIC":
                level = OPERATOR;
                break;
            case "CS-MARBOT":
                level = OPERATOR;
                break;
            case "CS-MESSENGER":
                level = OPERATOR;
                break;
            case "CS_NURSE":
                level = OPERATOR;
                break;
            case "CS-OFFICE_BOY/GIRL":
                level = OPERATOR;
                break;
            case "CS-ROOM_ATTENDENT":
                level = OPERATOR;
                break;
            case "CS-TEAM_KHUSUS":
                level = OPERATOR;
                break;
            case "CS-TOILET_ATTENDANT":
                level = OPERATOR;
                break;
            case "HR_ESTATE_COORDINATOR":
                level = OPERATOR;
                break;
            case "FIELDSERVICE":
                level = OPERATOR;
                break;
            case "CS_HANDY_MAN":
                level = OPERATOR;
                break;
            case "HLP_HELPER":
                level = OPERATOR;
                break;
            case "HR_GENERAL_SERVICE":
                level = OPERATOR;
                break;
            case "HR_MESSENGER":
                level = OPERATOR;
                break;
            case "HR-STAFF":
                level = OPERATOR;
                break;
            case "HR_QUALITY_CONTROL":
                level = OPERATOR;
                break;
            case "HR_TALLYMAN":
                level = OPERATOR;
                break;
            case "HR-DRIVER":
                level = OPERATOR;
                break;
            case "HR-RECEPTIONIST":
                level = OPERATOR;
                break;
            case "HR-TECHNICIAN":
                level = OPERATOR;
                break;
            case "CS-JR_CLEANER":
                level = OPERATOR;
                break;
            case "KERNET_DISTRIBUSI":
                level = OPERATOR;
                break;
            case "UMUM":
                level = OPERATOR;
                break;
            case "OA-OFFICE-ATTENDANT":
                level = OPERATOR;
                break;
            case "HR-OFFICE_SUPORT":
                level = OPERATOR;
                break;
            case "OPR_ASP":
                level = OPERATOR;
                break;
            case "OPS-DISINFECTANT":
                level = OPERATOR;
                break;
            case "OPR_DRY_ICE":
                level = OPERATOR;
                break;
            case "OPR_LISTRIK":
                level = OPERATOR;
                break;
            case "OPR_PENGISIAN":
                level = OPERATOR;
                break;
            case "OPR_PRODUKSI":
                level = OPERATOR;
                break;
            case "OPR_PRO_PLASTIK":
                level = OPERATOR;
                break;
            case "OT_OPERATOR_TELEPON":
                level = OPERATOR;
                break;
            case "PARAMEDIS":
                level = OPERATOR;
                break;
            case "CS_PEST_CONTROL":
                level = OPERATOR;
                break;
            case "CS-SUPPORT":
                level = OPERATOR;
                break;
            case "CS_ROPE_ACCES":
                level = OPERATOR;
                break;
            case "RN-RUNNER":
                level = OPERATOR;
                break;
            case "SC_KOORDINATOR":
                level = OPERATOR;
                break;
            case "SC_SECURITY":
                level = OPERATOR;
                break;
            case "SOPIR_DISTRIBUSI":
                level = OPERATOR;
                break;
            case "TEKNISI":
                level = OPERATOR;
                break;
            case "CS-LEADER":
                level = TEAM_LEADER;
                break;
            case "CS-TL-GONDOLA":
                level = TEAM_LEADER;
                break;
            case "TL_PEST_CONTROL":
                level = TEAM_LEADER;
                break;
            case "CS-SUPERVISOR":
                level = SUPERVISOR;
                break;
            case "CS-CHIEF_SUPERVISOR":
                level = CHIEF_SUPERVISOR;
                break;
            default:
                level = null;
        }

        return level;
    }
}
