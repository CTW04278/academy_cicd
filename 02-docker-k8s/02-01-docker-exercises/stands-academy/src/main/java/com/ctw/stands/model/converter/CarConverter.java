package com.ctw.stands.model.converter;


import com.ctw.stands.model.dto.CarDTO;
import com.ctw.stands.model.entity.Car;
import java.util.ArrayList;
import java.util.List;

public class CarConverter {

    public static CarDTO toDTO(Car carEntity) {
        CarDTO carDTO = new CarDTO();
        carDTO.setVin(carEntity.getVin());
        carDTO.setBrand(carEntity.getBrand());
        carDTO.setModel(carEntity.getModel());
        carDTO.setLicensePlate(carEntity.getLicensePlate());
        carDTO.setFuel(carEntity.getFuel());
        carDTO.setSalesDescription(carEntity.getBrand() + " " + carEntity.getModel());
        return carDTO;
    }

    public static Car toEntity(CarDTO carDTO) {
        Car carEntity = new Car();
        carEntity.setVin(carDTO.getVin());
        carEntity.setBrand(carDTO.getBrand());
        carEntity.setModel(carDTO.getModel());
        carEntity.setLicensePlate(carDTO.getLicensePlate());
        carEntity.setFuel(carDTO.getFuel());
        if (carDTO.getStand() != null && carDTO.getStand().getId() != null) {
            carEntity.setStandId(carDTO.getStand().getId());
        }
        return carEntity;
    }

    public static List<CarDTO> toDTOs(List<Car> carEntities) {
        List<CarDTO> carDTOs = new ArrayList<>();
        carEntities.forEach(carEntity -> carDTOs.add(toDTO(carEntity)));
        return carDTOs;
    }

    public static List<Car> toEntities(List<CarDTO> carDTOs) {
        List<Car> carEntities = new ArrayList<>();
        carDTOs.forEach(standDTO -> carEntities.add(toEntity(standDTO)));
        return carEntities;
    }
}
