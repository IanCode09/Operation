package com.carefast.careops.model.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_master_tools")
public class ToolModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tools_id ")
    private int toolsId;
    @Column(name = "tools_name")
    private String toolsName;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at_tools_m")
    private LocalDateTime createdAtTools;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at_tools_m")
    private LocalDateTime updatedAtTools;

    public int getToolsId() {
        return toolsId;
    }

    public void setToolsId(int toolsId) {
        this.toolsId = toolsId;
    }

    public String getToolsName() {
        return toolsName;
    }

    public void setToolsName(String toolsName) {
        this.toolsName = toolsName;
    }

    public LocalDateTime getCreatedAtTools() {
        return createdAtTools;
    }

    public void setCreatedAtTools(LocalDateTime createdAtTools) {
        this.createdAtTools = createdAtTools;
    }

    public LocalDateTime getUpdatedAtTools() {
        return updatedAtTools;
    }

    public void setUpdatedAtTools(LocalDateTime updatedAtTools) {
        this.updatedAtTools = updatedAtTools;
    }
}
