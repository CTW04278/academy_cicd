package com.ctw.stands.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "Car")
@NamedQuery(name = Car.GET_ALL_CARS, query = "select c from Car c")
public class Car {
    public static final String GET_ALL_CARS = "Car.getAllCars";
    public static final String GET_BY_STAND = "Car.getByStand";
    public static final String DELETE_BY_VIN = "Car.deleteByStand";
    @Id
    @Column(name = "vin")
    private String vin;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "fuel")
    private String fuel;
    @Column(name = "stand_id")
    private Long standId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stand_id", updatable = false, insertable = false)
    private Stand stand;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Long getStandId() {
        return standId;
    }

    public void setStandId(Long standId) {
        this.standId = standId;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }
}
