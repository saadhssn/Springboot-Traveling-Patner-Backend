package com.internode_studios.spring_boot_application.vehicle.dto;

import jakarta.validation.constraints.NotBlank;

public class VehicleDTO {

    private Long id;

    private Long userId;

    @NotBlank(message = "Model number is required")
    private String modelNo;

    private String vehicleColor;

    private String vehicleBrand;

    @NotBlank(message = "Registration number is required")
    private String registrationNo;

    private String registrationFront;

    private String registrationBack;

    private String outdoorImages;  // Comma-separated URLs for outdoor images
    private String indoorImages;   // Comma-separated URLs for indoor images

    private String vehicleType;

    private boolean ac;

    private boolean isPetsAllowed;

    private boolean isSmokingAllowed;

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

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getRegistrationFront() {
        return registrationFront;
    }

    public void setRegistrationFront(String registrationFront) {
        this.registrationFront = registrationFront;
    }

    public String getRegistrationBack() {
        return registrationBack;
    }

    public void setRegistrationBack(String registrationBack) {
        this.registrationBack = registrationBack;
    }

    public String getOutdoorImages() {
        return outdoorImages;
    }

    public void setOutdoorImages(String outdoorImages) {
        this.outdoorImages = outdoorImages;
    }

    public String getIndoorImages() {
        return indoorImages;
    }

    public void setIndoorImages(String indoorImages) {
        this.indoorImages = indoorImages;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public boolean isPetsAllowed() {
        return isPetsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        isPetsAllowed = petsAllowed;
    }

    public boolean isSmokingAllowed() {
        return isSmokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        isSmokingAllowed = smokingAllowed;
    }
}
