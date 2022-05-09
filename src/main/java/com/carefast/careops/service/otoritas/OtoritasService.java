package com.carefast.careops.service.otoritas;

import com.carefast.careops.dto.otoritas.OtoritasDTO;
import com.carefast.careops.dto.otoritas.TypeSetupDTO;
import com.carefast.careops.model.master.TypeSetupModel;
import com.carefast.careops.model.project.OtoritasModel;
import com.carefast.careops.repository.otoritas.OtoritasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OtoritasService {
    @Autowired
    private OtoritasRepository otoritasRepository;

    public List<OtoritasDTO> getOtoritasChecklist(String projectCode) {
        return otoritasRepository.getOtoritasProjectChecklist(projectCode).
                stream().map(this::convertOtoritasDTO).collect(Collectors.toList());
    }

    public OtoritasDTO convertOtoritasDTO(OtoritasModel item) {
        OtoritasDTO otoritasDTO = new OtoritasDTO();
        otoritasDTO.setIdOtoritas(item.getIdOtoritas());
        otoritasDTO.setJobCodeOtoritas(item.getJobCodeOtoritas());
        otoritasDTO.setProjectCodeOtoritas(item.getProjectCodeOtoritas());
        otoritasDTO.setTypeSetup(convertTypeSetupDTO(item.getTypeSetup()));
        otoritasDTO.setNamaModule(item.getNamaModule());

        return otoritasDTO;
    }

    public TypeSetupDTO convertTypeSetupDTO(TypeSetupModel item) {
        return new TypeSetupDTO(item.getIdTypeSetup(), item.getNamaSetup(), item.getNamaModule());
    }
}
