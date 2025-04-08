package com.internode_studios.spring_boot_application.basicinformation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "basic_information")
public class BasicInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String whatsApp;
    private String email;
    private Long cnicNumber;
    private String cnicFront;
    private String cnicBack;
    private String profilePicture;
    private String referralCode;
    private Boolean acceptTerm;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCnicNumber() {
        return cnicNumber;
    }

    public void setCnicNumber(Long cnicNumber) {
        this.cnicNumber = cnicNumber;
    }

    public String getCnicFront() {
        return cnicFront;
    }

    public void setCnicFront(String cnicFront) {
        this.cnicFront = cnicFront;
    }

    public String getCnicBack() {
        return cnicBack;
    }

    public void setCnicBack(String cnicBack) {
        this.cnicBack = cnicBack;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Boolean getAcceptTerm() {
        return acceptTerm;
    }

    public void setAcceptTerm(Boolean acceptTerm) {
        this.acceptTerm = acceptTerm;
    }
}
