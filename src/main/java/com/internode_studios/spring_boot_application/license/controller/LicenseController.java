package com.internode_studios.spring_boot_application.license.controller;

import com.internode_studios.spring_boot_application.license.model.License;
import com.internode_studios.spring_boot_application.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/" +
        "")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    // Register a new License
    @PostMapping
    public ResponseEntity<License> registerLicense(@RequestBody License license) {
        License newLicense = licenseService.registerLicense(license);
        if (newLicense != null) {
            return ResponseEntity.ok(newLicense);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Get a License by ID
    @GetMapping("/{id}")
    public ResponseEntity<License> getLicenseById(@PathVariable Long id) {
        License license = licenseService.getLicenseById(id);
        if (license != null) {
            return ResponseEntity.ok(license);
        }
        return ResponseEntity.notFound().build();
    }

    // Get all Licenses
    @GetMapping
    public ResponseEntity<List<License>> getAllLicenses() {
        List<License> licenses = licenseService.getAllLicenses();
        return ResponseEntity.ok(licenses);
    }

    // Update a License by ID
    @PutMapping("/{id}")
    public ResponseEntity<License> updateLicense(@PathVariable Long id, @RequestBody License updatedLicense) {
        License license = licenseService.updateLicense(id, updatedLicense);
        if (license != null) {
            return ResponseEntity.ok(license);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a License by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable Long id) {
        licenseService.deleteLicense(id);
        return ResponseEntity.noContent().build();
    }
}
