package com.ctw.stands.model.dao;


import com.ctw.stands.model.converter.CarConverter;
import com.ctw.stands.model.converter.StandConverter;
import com.ctw.stands.model.dto.StandDTO;
import com.ctw.stands.model.entity.Stand;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Transactional
public class StandDAO {

    @Inject
    EntityManager entityManager;

    public List<StandDTO> getAllStands() {
        List<Stand> standEntityList = entityManager.createNamedQuery(Stand.GET_ALL_STANDS, Stand.class).getResultList();
        List<StandDTO> standDTOList = new ArrayList<>();
        standEntityList.forEach(standEntity -> {
            StandDTO standDTO = StandConverter.toDTO(standEntity);
            standDTO.setCars(CarConverter.toDTOs(standEntity.getCars()));
            standDTOList.add(standDTO);
        });
        return standDTOList;
    }

    public String getStandAddressByName(String standName) {
        Stand standEntity = entityManager.createNamedQuery(Stand.GET_BY_NAME, Stand.class)
                .setParameter("standName", standName)
                .getSingleResult();
        StandDTO standDTO = StandConverter.toDTO(standEntity);
        return standDTO.getAddress();
    }

    public StandDTO getStandByName(String standName) {
        List<Stand> standEntityList = entityManager.createNamedQuery(Stand.GET_BY_NAME, Stand.class)
                .setParameter("standName", standName)
                .getResultList();

        if (standEntityList.size() > 1) {
            throw new IllegalStateException("There is more than one stand with the name " + standName);
        }

        return !standEntityList.isEmpty() ? StandConverter.toDTO(standEntityList.get(0)) : null;
    }

    public StandDTO create(StandDTO standDTO) {
        Stand standEntity = StandConverter.toEntity(standDTO);
        entityManager.persist(standEntity);
        return StandConverter.toDTO(standEntity);
    }
}