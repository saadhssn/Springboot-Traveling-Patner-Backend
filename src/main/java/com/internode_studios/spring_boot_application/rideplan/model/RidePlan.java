package com.internode_studios.spring_boot_application.rideplan.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ride_plans")
public class RidePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "ride_type_id", nullable = false)
    private Long rideTypeId;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = true)
    private Date date;

    @Column(name = "stuff", nullable = true)
    private int stuff;

    @Column(name = "bags_count", nullable = true)
    private int bagsCount;

    @Column(name = "pick_up_location", nullable = false)
    private String pickUpLocation;

    @Column(name = "drop_off_location", nullable = false)
    private String dropOffLocation;

    @Column(name = "city_id", nullable = false)
    private Long cityId;

    @Column(name = "tour_days", nullable = true)
    private int tourDays;

    @Column(name = "meal", nullable = true)
    private boolean meal;

    @Column(name = "seats", nullable = true)
    private int seats;

    @Column(name = "seats_available", nullable = true)
    private int seatsAvailable;

    @Column(name = "seats_reserved", nullable = true)
    private int seatsReserved;

    @Column(name = "visiting_points", nullable = true)
    private int visitingPoints;

    @Column(name = "pets", nullable = true)
    private boolean pets;

    @Column(name = "smoke", nullable = true)
    private boolean smoke;

    @Column(name = "ac", nullable = true)
    private boolean ac;

    @Column(name = "fare", nullable = true)
    private String fare;

    @Column(name = "driver_quoted_fare", nullable = true)
    private double driverQuotedFare;

    @Column(name = "partner_quoted_fare", nullable = true)
    private double partnerQuotedFare;

    @Column(name = "ride_status", nullable = true)
    private String rideStatus;

    @Column(name = "note")
    private String note;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getRideTypeId() {
        return rideTypeId;
    }

    public void setRideTypeId(Long rideTypeId) {
        this.rideTypeId = rideTypeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public int getTourDays() {
        return tourDays;
    }

    public void setTourDays(int tourDays) {
        this.tourDays = tourDays;
    }

    public boolean isMeal() {
        return meal;
    }

    public void setMeal(boolean meal) {
        this.meal = meal;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public int getSeatsReserved() {
        return seatsReserved;
    }

    public void setSeatsReserved(int seatsReserved) {
        this.seatsReserved = seatsReserved;
    }

    public int getVisitingPoints() {
        return visitingPoints;
    }

    public void setVisitingPoints(int visitingPoints) {
        this.visitingPoints = visitingPoints;
    }

    public boolean isPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public boolean isSmoke() {
        return smoke;
    }

    public void setSmoke(boolean smoke) {
        this.smoke = smoke;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public double getDriverQuotedFare() {
        return driverQuotedFare;
    }

    public void setDriverQuotedFare(double driverQuotedFare) {
        this.driverQuotedFare = driverQuotedFare;
    }

    public double getPartnerQuotedFare() {
        return partnerQuotedFare;
    }

    public void setPartnerQuotedFare(double partnerQuotedFare) {
        this.partnerQuotedFare = partnerQuotedFare;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
