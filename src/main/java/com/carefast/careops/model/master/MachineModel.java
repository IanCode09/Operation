package com.carefast.careops.model.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_master_machine")
public class MachineModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id ")
    private int machineId;
    @Column(name = "machine_name")
    private String machineName;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at_machine_m")
    private LocalDateTime createdAtMachine;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at_machine_m")
    private LocalDateTime updatedAtMachine;

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public LocalDateTime getCreatedAtMachine() {
        return createdAtMachine;
    }

    public void setCreatedAtMachine(LocalDateTime createdAtMachine) {
        this.createdAtMachine = createdAtMachine;
    }

    public LocalDateTime getUpdatedAtMachine() {
        return updatedAtMachine;
    }

    public void setUpdatedAtMachine(LocalDateTime updatedAtMachine) {
        this.updatedAtMachine = updatedAtMachine;
    }
}
