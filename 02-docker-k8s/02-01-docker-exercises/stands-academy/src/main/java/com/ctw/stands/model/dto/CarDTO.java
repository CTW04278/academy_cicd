package com.ctw.stands.model.dto;


public class CarDTO {
    private String vin;
    private String brand;
    private String model;
    private String licensePlate;
    private String fuel;
    private StandDTO stand;
    private String salesDescription;

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

    public StandDTO getStand() {
        return stand;
    }

    public void setStand(StandDTO stand) {
        this.stand = stand;
    }

    public String getSalesDescription() {
        return salesDescription;
    }

    public void setSalesDescription(String salesDescription) {
        this.salesDescription = salesDescription;
    }
}
