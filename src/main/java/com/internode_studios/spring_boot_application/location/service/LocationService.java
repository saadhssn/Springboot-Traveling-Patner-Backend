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

    // Update Location by ID
    public Location updateLocation(Long id, Location updatedLocation) {
        // Check if the Area exists
        Location location = locationRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Area with ID " + id + " does not exist."));

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

        // Update fields
        location.setName(updatedLocation.getName());
        location.setLatitude(updatedLocation.getLatitude());
        location.setLongitude(updatedLocation.getLongitude());
        location.setAreaId(updatedLocation.getAreaId());
        location.setCityId(updatedLocation.getCityId());
        location.setStateId(updatedLocation.getStateId());

        return locationRepository.save(location);
    }

    // Delete Location by ID
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
