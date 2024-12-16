package com.internode_studios.spring_boot_application.location.service;

import com.internode_studios.spring_boot_application.area.repository.AreaRepository;
import com.internode_studios.spring_boot_application.city.repository.CityRepository;
import com.internode_studios.spring_boot_application.location.model.Location;
import com.internode_studios.spring_boot_application.location.repository.LocationRepository;
import com.internode_studios.spring_boot_application.state.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private StateRepository stateRepository;

    // Create a new Location
    public Location createLocation(Location location) {

        // Check if the stateId exists in the State table
        if (!stateRepository.existsById(location.getStateId())) {
            throw new IllegalArgumentException("State with ID " + location.getStateId() + " does not exist.");
        }

        // Check if the cityId exists in the City table
        if (!cityRepository.existsById(location.getCityId())) {
            throw new IllegalArgumentException("City with ID " + location.getCityId() + " does not exist.");
        }

        // Check if the areaId exists in the Area table
        if (!areaRepository.existsById(location.getAreaId())) {
            throw new IllegalArgumentException("Area with ID " + location.getAreaId() + " does not exist.");
        }
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

    public Location updateLocation(Long id, Location updatedLocation) {
        // Check if the Location exists
        Location location = locationRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Location with ID " + id + " does not exist."));

        // Validate stateId if it's provided in the payload
        if (updatedLocation.getStateId() != null && !stateRepository.existsById(updatedLocation.getStateId())) {
            throw new IllegalArgumentException("State with ID " + updatedLocation.getStateId() + " does not exist.");
        }

        // Validate cityId if it's provided in the payload
        if (updatedLocation.getCityId() != null && !cityRepository.existsById(updatedLocation.getCityId())) {
            throw new IllegalArgumentException("City with ID " + updatedLocation.getCityId() + " does not exist.");
        }

        // Validate areaId if it's provided in the payload
        if (updatedLocation.getAreaId() != null && !areaRepository.existsById(updatedLocation.getAreaId())) {
            throw new IllegalArgumentException("Area with ID " + updatedLocation.getAreaId() + " does not exist.");
        }

        // Update only the non-null fields
        if (updatedLocation.getName() != null) {
            location.setName(updatedLocation.getName());
        }
        if (updatedLocation.getLatitude() != null) {
            location.setLatitude(updatedLocation.getLatitude());
        }
        if (updatedLocation.getLongitude() != null) {
            location.setLongitude(updatedLocation.getLongitude());
        }
        if (updatedLocation.getAreaId() != null) {
            location.setAreaId(updatedLocation.getAreaId());
        }
        if (updatedLocation.getCityId() != null) {
            location.setCityId(updatedLocation.getCityId());
        }
        if (updatedLocation.getStateId() != null) {
            location.setStateId(updatedLocation.getStateId());
        }

        return locationRepository.save(location);
    }

    // Delete Location by ID
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
