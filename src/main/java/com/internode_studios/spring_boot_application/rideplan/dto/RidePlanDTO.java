package com.internode_studios.spring_boot_application.rideplan.dto;

import com.internode_studios.spring_boot_application.availabletaxis.model.AvailableTaxi;
import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.rideplan.model.RidePlan;
import com.internode_studios.spring_boot_application.ridetype.model.RideType;
import com.internode_studios.spring_boot_application.user.model.User;

public class RidePlanDTO {

    private RidePlan ridePlan;
    private User partner;
    private User driver;
    private City city;
    private RideType rideType;

    public RidePlan getRidePlan() {
        return ridePlan;
    }

    public void setRidePlan(RidePlan ridePlan) {
        this.ridePlan = ridePlan;
    }

    public User getPartner() {
        return partner;
    }

    public void setPartner(User partner) {
        this.partner = partner;
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

    public RideType getRideType() {
        return rideType;
    }

    public void setRideType(RideType rideType) {
        this.rideType = rideType;
    }

    public RidePlanDTO(RidePlan ridePlan, User partner, User driver, City city, RideType rideType) {
        this.ridePlan = ridePlan;
        this.partner = partner;
        this.driver = driver;
        this.city = city;
        this.rideType = rideType;
    }

}
