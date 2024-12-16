package com.internode_studios.spring_boot_application.license.service;

import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.license.model.License;
import com.internode_studios.spring_boot_application.license.repository.LicenseRepository;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private UserRepository userRepository;

    // Register License
    @Transactional
    public License registerLicense(License license) {
        User user = userRepository.findById(license.getUserId()).orElse(null);
        if (user != null) {
            license = licenseRepository.save(license);

            System.out.println("License saved with ID: " + license.getId());

            user.setLicenseId(license.getId());
            userRepository.save(user);

            System.out.println("User updated with new licenseId: " + user.getLicenseId());

            return license;
        }
        return null;
    }

    // Get License by ID
    public License getLicenseById(Long id) {
        Optional<License> license = licenseRepository.findById(id);
        if (license.isPresent()) {
            return license.get();
        }
        System.out.println("License not found with ID: " + id);
        return null;
    }

    // Get all licenses
    public List<License> getAllLicenses() {
        return licenseRepository.findAll();
    }

    // Update License
    @Transactional
    public License updateLicense(Long id, License updatedLicense) {
        // Find the existing license
        License existingLicense = licenseRepository.findById(id).orElse(null);
        if (existingLicense != null) {
            // Update fields only if they are non-null
            if (updatedLicense.getLicenseNo() != null) {
                existingLicense.setLicenseNo(updatedLicense.getLicenseNo());
            }
            if (updatedLicense.getLicenseFront() != null) {
                existingLicense.setLicenseFront(updatedLicense.getLicenseFront());
            }
            if (updatedLicense.getLicenseBack() != null) {
                existingLicense.setLicenseBack(updatedLicense.getLicenseBack());
            }
            // Add checks for other fields as needed...

            // Save the updated license
            License savedLicense = licenseRepository.save(existingLicense);
            System.out.println("License updated with ID: " + savedLicense.getId());
            return savedLicense;
        }

        // Handle case where the license is not found
        System.out.println("License not found with ID: " + id);
        return null;
    }

    // Delete License by ID
    @Transactional
    public void deleteLicense(Long id) {
        License license = licenseRepository.findById(id).orElse(null);
        if (license != null) {
            // First, remove the reference from the user
            User user = userRepository.findById(license.getUserId()).orElse(null);
            if (user != null) {
                user.setLicenseId(null);
                userRepository.save(user);
                System.out.println("User's licenseId set to null.");
            }

            // Now delete the license
            licenseRepository.deleteById(id);
            System.out.println("License deleted with ID: " + id);
        } else {
            System.out.println("License not found with ID: " + id);
        }
    }
}