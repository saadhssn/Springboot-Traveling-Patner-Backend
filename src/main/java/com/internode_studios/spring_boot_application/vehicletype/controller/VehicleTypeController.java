package com.internode_studios.spring_boot_application.vehicletype.controller;


import com.internode_studios.spring_boot_application.vehicletype.model.VehicleType;
import com.internode_studios.spring_boot_application.vehicletype.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicleTypes")
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    // Endpoint to create a new vehicle type
    @PostMapping("/create")
    public ResponseEntity<VehicleType> createVehicleType(@RequestBody VehicleType vehicleType) {
        VehicleType savedVehicleType = vehicleTypeService.saveVehicleType(vehicleType);
        if (savedVehicleType != null) {
            return new ResponseEntity<>(savedVehicleType, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Endpoint to get all vehicle types
    @GetMapping("/getAll")
    public ResponseEntity<List<VehicleType>> getAllVehicleTypes() {
        List<VehicleType> vehicleTypes = vehicleTypeService.getAllVehicleTypes();
        return new ResponseEntity<>(vehicleTypes, HttpStatus.OK);
    }

    // Endpoint to get a vehicle type by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getVehicleTypeById(@PathVariable Long id) {
        Optional<VehicleType> vehicleType = vehicleTypeService.getVehicleTypeById(id);
        if (vehicleType.isPresent()) {
            return ResponseEntity.ok(vehicleType);
        } else {
            return ResponseEntity.status(404).body("Vehicle Type not found");
        }
    }

    // Endpoint to update an existing vehicle type
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehicleType(@PathVariable Long id, @RequestBody VehicleType vehicleTypeDetails) {
        VehicleType updatedVehicleType = vehicleTypeService.updateVehicleType(id, vehicleTypeDetails);
        if (updatedVehicleType != null) {
            return ResponseEntity.ok(updatedVehicleType);
        }
        return ResponseEntity.status(404).body("Vehicle Type not found");
    }

    // Endpoint to delete a vehicle type
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicleType(@PathVariable Long id) {
        Optional<VehicleType> vehicleType = vehicleTypeService.getVehicleTypeById(id);
        if (vehicleType.isPresent()) {
            vehicleTypeService.deleteVehicleType(id);
            return ResponseEntity.ok("Vehicle Type deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Vehicle Type not found");
        }
    }
}
