package com.internode_studios.spring_boot_application.user.model;

import com.internode_studios.spring_boot_application.role.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "password")
    private String password;

//    @Column(name = "confirm_password")
//    private String confirmPassword;

    private String otp;

//        @ManyToOne
//        @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
//        private Role role; // This will be populated from the Role table.

    @Column(name = "role")
    private String role;

    @Column(name = "cnic_number", unique = true)
    private String cnicNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "basic_information_id")
    private Long basicInformationId;

    @Column(name = "license_id")
    private Long licenseId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "remember_token")
    private String rememberToken;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "is_otp_verified")
    private Boolean isOtpVerified = false;

    @Column(name = "deleted") // This will indicate if the user is soft deleted
    private Boolean deleted = false;

    @Column(name = "deleted_at") // Timestamp for soft deletion
    private LocalDateTime deletedAt;

    @Column(name = "license_verified") // Timestamp for soft deletion
    private Boolean licenseVerified = false;

    @Column(name = "vehicle_verified") // Timestamp for soft deletion
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

//        public Role getRole() {
//            return role;
//        }
//
//        public void setRole(Role role) {
//            this.role = role;
//        }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
