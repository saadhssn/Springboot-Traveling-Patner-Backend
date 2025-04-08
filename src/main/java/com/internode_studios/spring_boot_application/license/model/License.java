package com.internode_studios.spring_boot_application.license.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "licenses")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseNo;
    private Long userId;
    private String licenseFront;
    private String licenseBack;
    private Boolean licenseVerified = false;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLicenseFront() {
        return licenseFront;
    }

    public void setLicenseFront(String licenseFront) {
        this.licenseFront = licenseFront;
    }

    public String getLicenseBack() {
        return licenseBack;
    }

    public void setLicenseBack(String licenseBack) {
        this.licenseBack = licenseBack;
    }

    public Boolean getLicenseVerified() {
        return licenseVerified;
    }

    public void setLicenseVerified(Boolean licenseVerified) {
        this.licenseVerified = licenseVerified;
    }
}