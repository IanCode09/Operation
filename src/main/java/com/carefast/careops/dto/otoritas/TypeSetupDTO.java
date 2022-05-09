package com.carefast.careops.dto.otoritas;

public class TypeSetupDTO {
    private int idTypeSetupe;
    private String namaSetup;
    private String namaModule;

    public TypeSetupDTO() {
    }

    public TypeSetupDTO(int idTypeSetupe, String namaSetup, String namaModule) {
        this.idTypeSetupe = idTypeSetupe;
        this.namaSetup = namaSetup;
        this.namaModule = namaModule;
    }

    public int getIdTypeSetupe() {
        return idTypeSetupe;
    }

    public void setIdTypeSetupe(int idTypeSetupe) {
        this.idTypeSetupe = idTypeSetupe;
    }

    public String getNamaSetup() {
        return namaSetup;
    }

    public void setNamaSetup(String namaSetup) {
        this.namaSetup = namaSetup;
    }

    public String getNamaModule() {
        return namaModule;
    }

    public void setNamaModule(String namaModule) {
        this.namaModule = namaModule;
    }
}
