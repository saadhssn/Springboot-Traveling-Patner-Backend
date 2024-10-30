package com.internode_studios.spring_boot_application.availabletaxis.dto;

import com.internode_studios.spring_boot_application.availabletaxis.model.AvailableTaxi;
import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.user.model.User;
import jakarta.validation.constraints.NotNull;

public class AvailableTaxiDTO {

    private AvailableTaxi availableTaxi;
    private User driver;
    private User user;
    private City city;

    public AvailableTaxiDTO(AvailableTaxi availableTaxi, User driver, City city) {
        this.availableTaxi = availableTaxi;
        this.driver = driver;
        this.city = city;
    }

    // Getters and Setters
    public AvailableTaxi getAvailableTaxi() {
        return availableTaxi;
    }

    public void setAvailableTaxi(AvailableTaxi availableTaxi) {
        this.availableTaxi = availableTaxi;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
