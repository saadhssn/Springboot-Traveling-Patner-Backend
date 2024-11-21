package com.internode_studios.spring_boot_application.city.service;

import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.city.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    // Create a new City
    public City createCity(City city) {
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
        City city = cityRepository.findById(id).orElse(null);
        if (city != null) {
            // Update fields
            city.setName(cityDetails.getName());
            return cityRepository.save(city);
        }
        return null; // Or throw an exception
    }


    // Delete City by ID
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
