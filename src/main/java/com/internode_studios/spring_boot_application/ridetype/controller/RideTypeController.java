package com.internode_studios.spring_boot_application.ridetype.controller;

import com.internode_studios.spring_boot_application.ridetype.model.RideType;
import com.internode_studios.spring_boot_application.ridetype.service.RideTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rideTypes")
public class RideTypeController {

    @Autowired
    private RideTypeService rideTypeService;

    // Create RideType
    @PostMapping("/create")
    public ResponseEntity<RideType> createRideType(@RequestBody RideType rideType) {
        RideType createdRideType = rideTypeService.createRideType(rideType);
        return ResponseEntity.ok(createdRideType);
    }

    // Get All RideTypes
    @GetMapping("/getAll")
    public ResponseEntity<List<RideType>> getAllRideTypes() {
        List<RideType> rideTypes = rideTypeService.getAllRideTypes();
        return ResponseEntity.ok(rideTypes);
    }

    // Get RideType by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getRideTypeById(@PathVariable Long id) {
        Optional<RideType> rideType = rideTypeService.getRideTypeById(id);
        return rideType.map(r -> ResponseEntity.ok((Object) r)) // Cast RideType object to Object
                .orElseGet(() -> ResponseEntity.status(404).body("RideType not found"));
    }

    // Update RideType
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRideType(@PathVariable Long id, @RequestBody RideType rideTypeDetails) {
        Optional<RideType> existingRideType = rideTypeService.getRideTypeById(id);
        if (existingRideType.isPresent()) {
            RideType updatedRideType = rideTypeService.updateRideType(id, rideTypeDetails);
            return ResponseEntity.ok(updatedRideType);
        }
        return ResponseEntity.status(404).body("RideType not found");
    }

    // Delete RideType
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRideType(@PathVariable Long id) {
        Optional<RideType> rideType = rideTypeService.getRideTypeById(id);
        if (rideType.isPresent()) {
            rideTypeService.deleteRideType(id);
            return ResponseEntity.ok("RideType deleted successfully");
        }
        return ResponseEntity.status(404).body("RideType not found");
    }
}
