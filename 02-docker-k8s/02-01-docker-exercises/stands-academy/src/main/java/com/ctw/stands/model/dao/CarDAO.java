package com.ctw.stands.model.dao;

import com.ctw.stands.model.converter.CarConverter;
import com.ctw.stands.model.converter.StandConverter;
import com.ctw.stands.model.dto.CarDTO;
import com.ctw.stands.model.entity.Car;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Transactional
public class CarDAO {

    @Inject
    EntityManager entityManager;

    public List<CarDTO> getAllCars() {
        List<Car> carEntityList = entityManager.createNamedQuery(Car.GET_ALL_CARS, Car.class).getResultList();
        List<CarDTO> carDTOList = new ArrayList<>();
        carEntityList.forEach(car -> {
            CarDTO carDTO = CarConverter.toDTO(car);
            if (car.getStand() != null) {
                carDTO.setStand(StandConverter.toDTO(car.getStand()));
            }
            carDTOList.add(carDTO);
        });
        return carDTOList;
    }

    public CarDTO create(CarDTO carDTO) {
        Car carEntity = CarConverter.toEntity(carDTO);
        entityManager.persist(carEntity);
        return CarConverter.toDTO(carEntity);
    }
}
