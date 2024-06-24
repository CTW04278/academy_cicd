package com.ctw.stands.model.converter;

import com.ctw.stands.model.dto.StandDTO;
import com.ctw.stands.model.entity.Stand;
import java.util.ArrayList;
import java.util.List;

public class StandConverter {

    public static StandDTO toDTO(Stand standEntity) {
        StandDTO standDTO = new StandDTO();
        standDTO.setId(standEntity.getId());
        standDTO.setName(standEntity.getName());
        standDTO.setAddress(standEntity.getAddress());
        return standDTO;
    }

    public static Stand toEntity(StandDTO standDTO) {
        Stand standEntity = new Stand();
        standEntity.setId(standDTO.getId());
        standEntity.setName(standDTO.getName());
        standEntity.setAddress(standDTO.getAddress());
        return standEntity;
    }

    public static List<StandDTO> toDTOs(List<Stand> standEntities) {
        List<StandDTO> standDTOS = new ArrayList<>();
        standEntities.forEach(standEntity -> standDTOS.add(toDTO(standEntity)));
        return standDTOS;
    }

    public static List<Stand> toEntities(List<StandDTO> standDTOS) {
        List<Stand> standEntities = new ArrayList<>();
        standDTOS.forEach(standDTO -> standEntities.add(toEntity(standDTO)));
        return standEntities;
    }
}