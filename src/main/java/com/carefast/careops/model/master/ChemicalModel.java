package com.carefast.careops.model.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_master_chemical")
public class ChemicalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chemical_id ")
    private int chemicalId;
    @Column(name = "chemical_name")
    private String chemicalName;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at_chemical_m")
    private LocalDateTime createdAtChemical;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at_chemical_m")
    private LocalDateTime updatedAtChemical;

    public int getChemicalId() {
        return chemicalId;
    }

    public void setChemicalId(int chemicalId) {
        this.chemicalId = chemicalId;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    public LocalDateTime getCreatedAtChemical() {
        return createdAtChemical;
    }

    public void setCreatedAtChemical(LocalDateTime createdAtChemical) {
        this.createdAtChemical = createdAtChemical;
    }

    public LocalDateTime getUpdatedAtChemical() {
        return updatedAtChemical;
    }

    public void setUpdatedAtChemical(LocalDateTime updatedAtChemical) {
        this.updatedAtChemical = updatedAtChemical;
    }
}
