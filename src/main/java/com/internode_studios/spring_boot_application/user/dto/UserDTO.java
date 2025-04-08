package com.internode_studios.spring_boot_application.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;

    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;  // New field

    @Pattern(regexp = "\\d{13}", message = "CNIC must be exactly 13 digits")
    private String cnicNumber;

    private String email;

    private String name;

    private String gender;

    private String otp;

    private String role;

    private Long basicInformationId;

    private Long licenseId;

    private Long vehicleId;

    private String status;

    private String rememberToken;

    private String deviceToken;

    private Boolean isOtpVerified;

    @Column(name = "deleted") // This will indicate if the user is soft deleted
    private Boolean deleted = false;

    @Column(name = "deleted_at") // Timestamp for soft deletion
    private LocalDateTime deletedAt;

    private Boolean licenseVerified = false;

    private Boolean vehicleVerified = false;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public @Pattern(regexp = "\\d{13}", message = "CNIC must be exactly 13 digits") String getCnicNumber() {
        return cnicNumber;
    }

    public void setCnicNumber(@Pattern(regexp = "\\d{13}", message = "CNIC must be exactly 13 digits") String cnicNumber) {
        this.cnicNumber = cnicNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getOtpVerified() {
        return isOtpVerified;
    }

    public void setOtpVerified(Boolean otpVerified) {
        isOtpVerified = otpVerified;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getBasicInformationId() {
        return basicInformationId;
    }

    public void setBasicInformationId(Long basicInformationId) {
        this.basicInformationId = basicInformationId;
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Boolean getIsOtpVerified() {
        return isOtpVerified;
    }

    public void setIsOtpVerified(Boolean isOtpVerified) {
        this.isOtpVerified = isOtpVerified;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean getLicenseVerified() {
        return licenseVerified;
    }

    public void setLicenseVerified(Boolean licenseVerified) {
        this.licenseVerified = licenseVerified;
    }

    public Boolean getVehicleVerified() {
        return vehicleVerified;
    }

    public void setVehicleVerified(Boolean vehicleVerified) {
        this.vehicleVerified = vehicleVerified;
    }
}
