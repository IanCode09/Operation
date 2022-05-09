package com.carefast.careops.model.project;

import com.carefast.careops.model.master.TypeSetupModel;

import javax.persistence.*;

@Entity
@Table(name = "tab_otoritas")
public class OtoritasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_otoritas")
    private int idOtoritas;
    @Column(name = "job_code_otoritas")
    private String jobCodeOtoritas;
    @Column(name = "project_code_otoritas")
    private String projectCodeOtoritas;
    @OneToOne
    @JoinColumn(name = "id_type_setup")
    private TypeSetupModel typeSetup;
    @Column(name = "nama_module", columnDefinition = "enum('Checklist','Izin','Lembur','Tuker Jadwal')")
    private String namaModule;

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

    public TypeSetupModel getTypeSetup() {
        return typeSetup;
    }

    public void setTypeSetup(TypeSetupModel typeSetup) {
        this.typeSetup = typeSetup;
    }

    public String getNamaModule() {
        return namaModule;
    }

    public void setNamaModule(String namaModule) {
        this.namaModule = namaModule;
    }
}
