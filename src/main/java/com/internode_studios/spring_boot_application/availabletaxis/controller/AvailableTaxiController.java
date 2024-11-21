package com.internode_studios.spring_boot_application.availabletaxis.controller;

import com.internode_studios.spring_boot_application.availabletaxis.dto.AvailableTaxiDTO;
import com.internode_studios.spring_boot_application.availabletaxis.model.AvailableTaxi;
import com.internode_studios.spring_boot_application.availabletaxis.service.AvailableTaxiService;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/availableTaxis")
public class AvailableTaxiController {

    @Autowired
    private AvailableTaxiService availableTaxiService;

    @Autowired
    private UserRepository userRepository;

    // Create AvailableTaxi
    @PostMapping("/create")
    public ResponseEntity<?> createAvailableTaxi(@RequestBody AvailableTaxi availableTaxi) {
        try {
            AvailableTaxi createdAvailableTaxi = availableTaxiService.createAvailableTaxi(availableTaxi);
            return ResponseEntity.ok(createdAvailableTaxi);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Get All AvailableTaxis (Without filters)
    @GetMapping("/getAll")
    public ResponseEntity<List<AvailableTaxi>> getAllAvailableTaxis() {
        List<AvailableTaxi> availableTaxis = availableTaxiService.getAllAvailableTaxis();
        return ResponseEntity.ok(availableTaxis);
    }

    // Get AvailableTaxi by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAvailableTaxiById(@PathVariable Long id, @RequestParam(value = "userData", required = false) boolean userData) {
        Optional<AvailableTaxi> availableTaxi = availableTaxiService.getAvailableTaxiById(id);
        return availableTaxi.map(at -> ResponseEntity.ok((Object) at))
                .orElseGet(() -> ResponseEntity.status(404).body("AvailableTaxi not found"));
    }

    // Update RideStatus AvailableTaxi
    @PutMapping("/update/rideStatus/{id}")
    public ResponseEntity<?> updateRideStatusAvailableTaxi(@PathVariable Long id, @RequestBody AvailableTaxi availableTaxiDetails) {
        try {
            AvailableTaxi updatedAvailableTaxi = availableTaxiService.updateRideStatusAvailableTaxi(id, availableTaxiDetails);
            if (updatedAvailableTaxi != null) {
                return ResponseEntity.ok(updatedAvailableTaxi);
            }
            return ResponseEntity.status(404).body("AvailableTaxi not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Update AvailableTaxi
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAvailableTaxi(@PathVariable Long id, @RequestBody AvailableTaxi availableTaxiDetails) {
        try {
            AvailableTaxi updatedAvailableTaxi = availableTaxiService.updateAvailableTaxi(id, availableTaxiDetails);
            if (updatedAvailableTaxi != null) {
                return ResponseEntity.ok(updatedAvailableTaxi);
            }
            return ResponseEntity.status(404).body("AvailableTaxi not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Delete AvailableTaxi
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAvailableTaxi(@PathVariable Long id) {
        Optional<AvailableTaxi> availableTaxi = availableTaxiService.getAvailableTaxiById(id);
        if (availableTaxi.isPresent()) {
            availableTaxiService.deleteAvailableTaxi(id);
            return ResponseEntity.ok("AvailableTaxi deleted successfully");
        }
        return ResponseEntity.status(404).body("AvailableTaxi not found");
    }

    // Get all AvailableTaxis with rideStatus 'online'
//    @GetMapping("/getAllOnline")
//    public ResponseEntity<?> getAllAvailableTaxisOnline(
//            @RequestParam String gender,
//            @RequestParam String rideStatus,
//            @RequestParam Long cityId) {
//
//        // Validate rideStatus
//        if (!rideStatus.equalsIgnoreCase("online")) {
//            return ResponseEntity.status(400).body("Only 'online' rideStatus is supported");
//        }
//
//        // Fetch the taxis from the service
//        List<AvailableTaxi> availableTaxis = availableTaxiService.getAllAvailableTaxisByStatusOnlineGender(gender, rideStatus, cityId);
//
//        // Check if results are found
//        if (availableTaxis.isEmpty()) {
//            return ResponseEntity.status(404).body("No taxis found for the given criteria");
//        }
//
//        return ResponseEntity.ok(availableTaxis);
//    }


    @GetMapping("/getAllOnlineOffline")
    public ResponseEntity<?> getAllAvailableTaxis(
            @RequestParam String gender,
            @RequestParam(required = false) String rideStatus,  // Optional parameter
            @RequestParam Long cityId) {

        // Fetch the taxis from the service based on whether rideStatus is provided
        List<AvailableTaxi> availableTaxis;

        if (rideStatus != null) {
            // Validate rideStatus if provided
            if (!rideStatus.equalsIgnoreCase("online") && !rideStatus.equalsIgnoreCase("offline")) {
                return ResponseEntity.status(400).body("Only 'online' or 'offline' rideStatus is supported");
            }

            availableTaxis = availableTaxiService.getAllAvailableTaxisByStatusOnlineGender(gender, rideStatus, cityId);
        } else {
            // If rideStatus is not provided, get all taxis regardless of status
            availableTaxis = availableTaxiService.getAllAvailableTaxisByGenderAndCity(gender, cityId);
        }

        // Check if results are found
        if (availableTaxis.isEmpty()) {
            return ResponseEntity.status(404).body("No taxis found for the given criteria");
        }

        return ResponseEntity.ok(availableTaxis);
    }


    // Get AvailableTaxi by ID with rideStatus 'online', gender, and cityId
//    @GetMapping("/getByIdOnline/{id}")
//    public ResponseEntity<?> getAvailableTaxiById(
//            @PathVariable Long id,
//            @RequestParam String gender,
//            @RequestParam String rideStatus,
//            @RequestParam Long cityId) {
//
//        if (!rideStatus.equals("online")) {
//            return ResponseEntity.status(400).body("Only 'online' rideStatus is supported");
//        }
//
//        Optional<AvailableTaxi> availableTaxi = availableTaxiService.getAvailableTaxiByIdAndStatus(id, gender, rideStatus, cityId);
//
//        return availableTaxi.map(at -> ResponseEntity.ok((Object) at))
//                .orElseGet(() -> ResponseEntity.status(404).body("AvailableTaxi not found or does not match the criteria"));
//    }

        @GetMapping("/getByIdOnlineOffline/{id}")
        public ResponseEntity<Object> getAvailableTaxiById(
                @PathVariable Long id,
                @RequestParam String gender,
                @RequestParam(required = false) String rideStatus, // Make this optional
                @RequestParam Long cityId) {

            // If rideStatus is provided, validate it
            if (rideStatus != null && !rideStatus.equals("online") && !rideStatus.equals("offline")) {
                return ResponseEntity.status(400).body("Only 'online' or 'offline' rideStatus is supported");
            }

            Optional<AvailableTaxiDTO> availableTaxi;

            // Check if rideStatus is present
            if (rideStatus != null) {
                availableTaxi = availableTaxiService.getAvailableTaxiByIdAndStatusWithDriver(id, gender, rideStatus, cityId);
            } else {
                // If rideStatus is not provided, fetch the taxi without the status filter
                availableTaxi = availableTaxiService.findByIdAndGenderAndCityId(id, gender, cityId);
            }

            return availableTaxi.map(at -> ResponseEntity.ok((Object) at))
                    .orElseGet(() -> ResponseEntity.status(404).body("AvailableTaxi not found or does not match the criteria"));
        }

    @GetMapping("/getByIdOnlineOfflinePartner/{id}")
    public ResponseEntity<Object> getAvailableTaxiByIdPartner(
            @PathVariable Long id,
            @RequestParam String gender,
            @RequestParam(required = false) String rideStatus, // Make this optional
            @RequestParam Long cityId) {

        // If rideStatus is provided, validate it
        if (rideStatus != null && !rideStatus.equals("online") && !rideStatus.equals("offline")) {
            return ResponseEntity.status(400).body("Only 'online' or 'offline' rideStatus is supported");
        }

        Optional<AvailableTaxiDTO> availableTaxi;

        // Check if rideStatus is present
        if (rideStatus != null) {
            availableTaxi = availableTaxiService.findByIdAndGenderAndCityIdPartner(id, gender, cityId);
        } else {
            // If rideStatus is not provided, fetch the taxi without the status filter
            availableTaxi = availableTaxiService.findByIdAndGenderAndCityId(id, gender, cityId);
        }

        return availableTaxi.map(at -> ResponseEntity.ok((Object) at))
                .orElseGet(() -> ResponseEntity.status(404).body("AvailableTaxi not found or does not match the criteria"));
    }
}
