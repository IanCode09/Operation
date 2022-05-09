package com.carefast.careops.utils;

import com.carefast.careops.model.master.ChemicalModel;
import com.carefast.careops.model.master.MachineModel;
import com.carefast.careops.model.master.ToolModel;
import com.carefast.careops.repository.master.ChemicalRepository;
import com.carefast.careops.repository.master.MachineRepository;
import com.carefast.careops.repository.master.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MasterSplitUtils {

    public static final String MACHINE = "MACHINE";
    public static final String TOOL = "TOOL";
    public static final String CHEMICAL = "CHEMICAL";

    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private ToolRepository toolRepository;
    @Autowired
    private ChemicalRepository chemicalRepository;


    public String master(String item, String type) {
        Pattern pattern = Pattern.compile(",");
        String[] itemId = pattern.split(item);

        List<String> result = new ArrayList<>();
        List<String> itemIdArray = Arrays.stream(itemId).collect(Collectors.toList());

        if (type.equals(MACHINE)) {
            itemIdArray.forEach(id -> {
                MachineModel machineModel = machineRepository.findById(Integer.parseInt(id)).get();
                result.add(machineModel.getMachineName());
            });

            return String.join(", ", result);
        } else if (type.equals(TOOL)) {
            itemIdArray.forEach(id -> {
                ToolModel toolModel = toolRepository.findById(Integer.parseInt(id)).get();
                result.add(toolModel.getToolsName());
            });

            return String.join(", ", result);
        } else if (type.equals(CHEMICAL)) {
            itemIdArray.forEach(id -> {
                ChemicalModel chemicalModel = chemicalRepository.findById(Integer.parseInt(id)).get();
                result.add(chemicalModel.getChemicalName());
            });

            return String.join(", ", result);
        }

        return null;
    }
}
