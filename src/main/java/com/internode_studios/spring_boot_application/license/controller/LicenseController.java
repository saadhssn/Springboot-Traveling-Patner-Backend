package com.internode_studios.spring_boot_application.license.controller;

import com.internode_studios.spring_boot_application.license.model.License;
import com.internode_studios.spring_boot_application.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licenses")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    // Register a new License
    @PostMapping("/create")
    public ResponseEntity<License> registerLicense(@RequestBody License license) {
        License newLicense = licenseService.registerLicense(license);
        if (newLicense != null) {
            return ResponseEntity.ok(newLicense);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Get all Licenses
    @GetMapping("/getAll")
    public ResponseEntity<List<License>> getAllLicenses() {
        List<License> licenses = licenseService.getAllLicenses();
        return ResponseEntity.ok(licenses);
    }

    // Get a License by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getLicenseById(@PathVariable Long id) {
        License license = licenseService.getLicenseById(id);
        if (license != null) {
            return ResponseEntity.ok(license);
        } else {
            return ResponseEntity.status(404).body("License not found");
        }
    }

    // Update a License by ID
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateLicense(@PathVariable Long id, @RequestBody License updatedLicense) {
        Optional<License> existingLicense = Optional.ofNullable(licenseService.getLicenseById(id)); // Assuming you have a method to fetch by ID
        if (existingLicense.isPresent()) {
            License updated = licenseService.updateLicense(id, updatedLicense);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(404).body("License not found");
    }

    // Delete a License by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLicense(@PathVariable Long id) {
        Optional<License> license = Optional.ofNullable(licenseService.getLicenseById(id)); // Assuming you have a method to fetch by ID
        if (license.isPresent()) {
            licenseService.deleteLicense(id);
            return ResponseEntity.ok("License deleted successfully");
        }
        return ResponseEntity.status(404).body("License not found");
    }

}