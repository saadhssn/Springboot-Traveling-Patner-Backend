package com.internode_studios.spring_boot_application.vehicle.controller;

import com.internode_studios.spring_boot_application.vehicle.model.Vehicle;
import com.internode_studios.spring_boot_application.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Endpoint to create a new vehicle
    @PostMapping("/create")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        if (savedVehicle != null) {
            return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Endpoint to get all vehicles
    @GetMapping("/getAll")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Endpoint to get a vehicle by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            return ResponseEntity.ok(vehicle);
        } else {
            return ResponseEntity.status(404).body("Vehicle not found");
        }
    }

    // Endpoint to update an existing vehicle
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        Optional<Vehicle> existingVehicle = vehicleService.getVehicleById(id);
        if (existingVehicle.isPresent()) {
            Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDetails);
            return ResponseEntity.ok(updatedVehicle);
        }
        return ResponseEntity.status(404).body("Vehicle not found");
    }

    // Endpoint to delete a vehicle
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            vehicleService.deleteVehicle(id);
            return ResponseEntity.ok("Vehicle deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Vehicle not found");
        }
    }
}
