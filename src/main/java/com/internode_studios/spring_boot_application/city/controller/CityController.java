package com.internode_studios.spring_boot_application.city.controller;

import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    // Create City
    @PostMapping("/create")
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City createdCity = cityService.createCity(city);
        return ResponseEntity.ok(createdCity);
    }

    // Get All Cities
    @GetMapping("/getAll")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    // Get City by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCityById(@PathVariable Long id) {
        Optional<City> city = cityService.getCityById(id);
        return city.map(c -> ResponseEntity.ok((Object) c)) // Cast city object to Object
                .orElseGet(() -> ResponseEntity.status(404).body("City not found"));
    }

    // Update City
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Long id, @RequestBody City cityDetails) {
        Optional<City> existingCity = cityService.getCityById(id);
        if (existingCity.isPresent()) {
            City updatedCity = cityService.updateCity(id, cityDetails);
            return ResponseEntity.ok(updatedCity);
        }
        return ResponseEntity.status(404).body("City not found");
    }

    // Delete City
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) {
        Optional<City> city = cityService.getCityById(id);
        if (city.isPresent()) {
            cityService.deleteCity(id);
            return ResponseEntity.ok("City deleted successfully");
        }
        return ResponseEntity.status(404).body("City not found");
    }
}
