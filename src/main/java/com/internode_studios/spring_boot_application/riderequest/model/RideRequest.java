package com.internode_studios.spring_boot_application.riderequest.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ride_requests")
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id", nullable = true)
    private Long driverId;

    @Column(name = "partner_id", nullable = true)
    private Long partnerId;

    @Column(name = "ride_type_id", nullable = false)
    private Long rideTypeId;

    @Column(name = "ride_plan_id", nullable = false)
    private Long ridePlanId;

    @Column(name = "ride_status", nullable = false)
    private String rideStatus;

    @Column(name = "date", nullable = true)
    private String date; // Changed to String for flexible date format

    @Column(name = "time", nullable = true)
    private String time; // Changed to String for flexible time format

    @Column(name = "fare", nullable = false)
    private String fare;

    @Column(name = "pick_up_location", nullable = false)
    private String pickUpLocation;

    @Column(name = "drop_off_location", nullable = false)
    private String dropOffLocation;

    @Column(name = "seats_reserved", nullable = true)
    private int seatsReserved;

    @Column(name = "stuff", nullable = true)
    private int stuff;

    @Column(name = "bags_count", nullable = true)
    private int bagsCount;

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

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public int getSeatsReserved() {
        return seatsReserved;
    }

    public void setSeatsReserved(int seatsReserved) {
        this.seatsReserved = seatsReserved;
    }

    public int getStuff() {
        return stuff;
    }

    public void setStuff(int stuff) {
        this.stuff = stuff;
    }

    public int getBagsCount() {
        return bagsCount;
    }

    public void setBagsCount(int bagsCount) {
        this.bagsCount = bagsCount;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }
}
