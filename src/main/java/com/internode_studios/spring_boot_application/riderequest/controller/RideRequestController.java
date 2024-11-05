package com.internode_studios.spring_boot_application.riderequest.controller;

import com.internode_studios.spring_boot_application.riderequest.model.RideRequest;
import com.internode_studios.spring_boot_application.riderequest.service.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rideRequests")
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;

    // Create RideRequest
    @PostMapping("/create")
    public ResponseEntity<?> createRideRequest(@RequestBody RideRequest rideRequest) {
        try {
            RideRequest createdRideRequest = rideRequestService.createRideRequest(rideRequest);
            return ResponseEntity.ok(createdRideRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Get All RideRequests
    @GetMapping("/getAll")
    public ResponseEntity<List<RideRequest>> getAllRideRequests() {
        List<RideRequest> rideRequests = rideRequestService.getAllRideRequests();
        return ResponseEntity.ok(rideRequests);
    }

    // Get RideRequest by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getRideRequestById(@PathVariable Long id) {
        Optional<RideRequest> rideRequest = rideRequestService.getRideRequestById(id);
        return rideRequest.map(rr -> ResponseEntity.ok((Object) rr))
                .orElseGet(() -> ResponseEntity.status(404).body("RideRequest not found"));
    }

    @GetMapping("/byRidePlanAndDate")
    public List<RideRequest> getRideRequestsByRidePlanAndDate(
            @RequestParam Long ridePlanId,
            @RequestParam String date) {
        return rideRequestService.getRideRequestsByRidePlanIdAndDate(ridePlanId, date);
    }

    // Update RideRequest
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRideRequest(@PathVariable Long id, @RequestBody RideRequest rideRequestDetails) {
        try {
            RideRequest updatedRideRequest = rideRequestService.updateRideRequest(id, rideRequestDetails);
            if (updatedRideRequest != null) {
                return ResponseEntity.ok(updatedRideRequest);
            }
            return ResponseEntity.status(404).body("RideRequest not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Update fare only
    @PutMapping("/updateFare/{id}")
    public ResponseEntity<?> updateFare(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            // Get new fare from request body
            String newFare = request.get("fare");
            if (newFare == null) {
                return ResponseEntity.status(400).body("Fare must be provided in the request body");
            }

            RideRequest updatedRideRequest = rideRequestService.updateFare(id, newFare);
            return ResponseEntity.ok(updatedRideRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Delete RideRequest
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRideRequest(@PathVariable Long id) {
        Optional<RideRequest> rideRequest = rideRequestService.getRideRequestById(id);
        if (rideRequest.isPresent()) {
            rideRequestService.deleteRideRequest(id);
            return ResponseEntity.ok("RideRequest deleted successfully");
        }
        return ResponseEntity.status(404).body("RideRequest not found");
    }

    @GetMapping("/confirmedByPartner")
    public List<RideRequest> getConfirmedRideRequestsForPartner(@RequestParam Long partnerId) {
        return rideRequestService.getConfirmedRideRequestsForPartner(partnerId);
    }

    @GetMapping("/confirmedByDriver")
    public List<RideRequest> getConfirmedRideRequestsForDriver(@RequestParam Long driverId) {
        return rideRequestService.getConfirmedRideRequestsForDriver(driverId);
    }

    @GetMapping("/filtered")
    public ResponseEntity<?> getFilteredRideRequests(@RequestParam String status) {
        try {
            List<RideRequest> rideRequests = rideRequestService.getFilteredRideRequests(status);
            return ResponseEntity.ok(rideRequests);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
