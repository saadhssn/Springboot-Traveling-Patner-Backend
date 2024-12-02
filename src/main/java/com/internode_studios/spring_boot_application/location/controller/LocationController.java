package com.internode_studios.spring_boot_application.location.controller;

import com.internode_studios.spring_boot_application.blog.model.Blog;
import com.internode_studios.spring_boot_application.blog.service.BlogService;
import com.internode_studios.spring_boot_application.location.model.Location;
import com.internode_studios.spring_boot_application.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // Create Location
    @PostMapping("/create")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdDetail = locationService.createLocation(location);
        return ResponseEntity.ok(createdDetail);
    }

    // Get All Locations
    @GetMapping("/getAll")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> details = locationService.getAllLocations();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable Long id) {
        Optional<Location> location = locationService.getLocationById(id);
        return location.map(c -> ResponseEntity.ok((Object) c)) // Cast city object to Object
                .orElseGet(() -> ResponseEntity.status(404).body("Location not found"));
    }

    // Update Location
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable Long id, @RequestBody Location updatedLocation) {
        try {
            Location detail = locationService.updateLocation(id, updatedLocation);
            return ResponseEntity.ok(detail);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Delete Blog
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) {
        Optional<Location> detail = locationService.getLocationById(id);
        if (detail.isPresent()) {
            locationService.deleteLocation(id);
            return ResponseEntity.ok("Location deleted successfully");
        }
        return ResponseEntity.status(404).body("Location not found");
    }
}
