package com.carefast.careops.model.master;

import javax.persistence.*;

@Entity
@Table(name = "tab_master_type_setup")
public class TypeSetupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_setup")
    private int idTypeSetup;
    @Column(name = "nama_setup")
    private String namaSetup;
    @Column(name = "nama_module", columnDefinition = "enum('Checklist','Izin','Lembur','Tuker Jadwal')")
    private String namaModule;

    public int getIdTypeSetup() {
        return idTypeSetup;
    }

    public void setIdTypeSetup(int idTypeSetup) {
        this.idTypeSetup = idTypeSetup;
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
