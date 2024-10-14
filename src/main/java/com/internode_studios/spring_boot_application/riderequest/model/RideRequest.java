package com.internode_studios.spring_boot_application.riderequest.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ride_requests")
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    @Column(name = "ride_type_id", nullable = false)
    private Long rideTypeId;

    @Column(name = "ride_plan_id", nullable = false)
    private Long ridePlanId;

    @Column(name = "ride_status", nullable = false)
    private String rideStatus;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getRideTypeId() {
        return rideTypeId;
    }

    public void setRideTypeId(Long rideTypeId) {
        this.rideTypeId = rideTypeId;
    }

    public Long getRidePlanId() {
        return ridePlanId;
    }

    public void setRidePlanId(Long ridePlanId) {
        this.ridePlanId = ridePlanId;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }
}
