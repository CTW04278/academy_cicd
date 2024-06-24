package com.ctw.stands.service;

import com.ctw.stands.model.dao.CarDAO;
import com.ctw.stands.model.dao.StandDAO;
import com.ctw.stands.model.dto.CarDTO;
import com.ctw.stands.model.dto.StandDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CarService {
    @Inject
    CarDAO carDAO;

    @Inject
    StandDAO standDAO;

    public List<CarDTO> getAllCars() {
        return carDAO.getAllCars();
    }

    public CarDTO create(CarDTO carDTO) {
        if (carDTO.getStand() != null) {
            if (carDTO.getStand().getName().isEmpty()) {
                carDTO.setStand(null);
            } else {
                StandDTO standDTO = standDAO.getStandByName(carDTO.getStand().getName());
                if (standDTO != null) {
                    carDTO.setStand(standDTO);
                } else {
                    carDTO.setStand(standDAO.create(carDTO.getStand()));
                }
            }
        }
        CarDTO updatedCarDTO = carDAO.create(carDTO);
        updatedCarDTO.setStand(carDTO.getStand());
        return updatedCarDTO;
    }
}
