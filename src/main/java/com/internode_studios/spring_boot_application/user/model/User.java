package com.internode_studios.spring_boot_application.user.model;

import com.internode_studios.spring_boot_application.role.model.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private String otp;

//        @ManyToOne
//        @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
//        private Role role; // This will be populated from the Role table.

    @Column(name = "role")
    private String role;

    @Column(name = "basic_information_id")
    private Long basicInformationId;

    @Column(name = "license_id")
    private Long licenseId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    private Boolean status;

    @Column(name = "remember_token")
    private String rememberToken;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "is_otp_verified")
    private Boolean isOtpVerified = false;

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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
}
