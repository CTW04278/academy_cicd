package com.ctw.stands.controller;

import com.ctw.stands.model.dto.CarDTO;
import com.ctw.stands.service.CarService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cars")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarController {
    private final CarService carService;
    public CarController(final CarService carService) {
        this.carService = carService;
    }

    @GET
    public Response getAllCars() {
        final List<CarDTO> cars = this.carService.getAllCars();
        return Response.status(Response.Status.OK).entity(cars).build();
    }

    @POST
    public Response create(final CarDTO carDTO) {
        CarDTO car = this.carService.create(carDTO);
        return Response.status(Response.Status.CREATED).entity(car).build();
    }
}
