package com.carefast.careops.dto.otoritas;

public class OtoritasDTO {
    private int idOtoritas;
    private String jobCodeOtoritas;
    private String projectCodeOtoritas;
    private TypeSetupDTO typeSetup;
    private String namaModule;

    public OtoritasDTO() {
    }

    public OtoritasDTO(int idOtoritas, String jobCodeOtoritas, String projectCodeOtoritas, TypeSetupDTO typeSetup, String namaModule) {
        this.idOtoritas = idOtoritas;
        this.jobCodeOtoritas = jobCodeOtoritas;
        this.projectCodeOtoritas = projectCodeOtoritas;
        this.typeSetup = typeSetup;
        this.namaModule = namaModule;
    }

    public int getIdOtoritas() {
        return idOtoritas;
    }

    public void setIdOtoritas(int idOtoritas) {
        this.idOtoritas = idOtoritas;
    }

    public String getJobCodeOtoritas() {
        return jobCodeOtoritas;
    }

    public void setJobCodeOtoritas(String jobCodeOtoritas) {
        this.jobCodeOtoritas = jobCodeOtoritas;
    }

    public String getProjectCodeOtoritas() {
        return projectCodeOtoritas;
    }

    public void setProjectCodeOtoritas(String projectCodeOtoritas) {
        this.projectCodeOtoritas = projectCodeOtoritas;
    }

    public TypeSetupDTO getTypeSetup() {
        return typeSetup;
    }

    public void setTypeSetup(TypeSetupDTO typeSetup) {
        this.typeSetup = typeSetup;
    }

    public String getNamaModule() {
        return namaModule;
    }

    public void setNamaModule(String namaModule) {
        this.namaModule = namaModule;
    }
}
