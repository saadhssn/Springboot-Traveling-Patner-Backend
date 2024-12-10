package com.internode_studios.spring_boot_application.city.service;

import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.city.repository.CityRepository;
import com.internode_studios.spring_boot_application.state.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    // Create a new City
    public City createCity(City city) {
        // Check if the stateId exists in the State table
        if (!stateRepository.existsById(city.getStateId())) {
            throw new IllegalArgumentException("State with ID " + city.getStateId() + " does not exist.");
        }

        return cityRepository.save(city);
    }

    // Get all Cities
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    // Get City by ID
    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    // Update City by ID
    public City updateCity(Long id, City cityDetails) {
        // Check if the City exists
        City city = cityRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("City with ID " + id + " does not exist."));

        // Check if the stateId exists in the State table
        if (!stateRepository.existsById(cityDetails.getStateId())) {
            throw new IllegalArgumentException("State with ID " + cityDetails.getStateId() + " does not exist.");
        }

        // Update fields
        city.setName(cityDetails.getName());
        city.setStateId(cityDetails.getStateId());

        return cityRepository.save(city);
    }

    // Delete City by ID
    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new IllegalArgumentException("City with ID " + id + " does not exist.");
        }
        cityRepository.deleteById(id);
    }
}
