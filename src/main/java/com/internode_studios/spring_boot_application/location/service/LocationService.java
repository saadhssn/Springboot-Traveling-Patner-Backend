package com.internode_studios.spring_boot_application.location.service;

import com.internode_studios.spring_boot_application.location.model.Location;
import com.internode_studios.spring_boot_application.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    // Create a new Location
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    // Get all Location
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // Get Location by ID
    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    // Update Location by ID
    public Location updateLocation(Long id, Location updatedLocation) {
        return locationRepository.findById(id).map(detail -> {
            detail.setAddress(updatedLocation.getAddress());
            detail.setArea(updatedLocation.getArea());
            detail.setCity(updatedLocation.getCity());
            return locationRepository.save(detail);
        }).orElseThrow(() -> new RuntimeException("Location not found"));
    }

    // Delete Location by ID
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
