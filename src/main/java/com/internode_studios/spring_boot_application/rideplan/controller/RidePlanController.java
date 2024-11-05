package com.internode_studios.spring_boot_application.rideplan.controller;

import com.internode_studios.spring_boot_application.rideplan.dto.RidePlanDTO;
import com.internode_studios.spring_boot_application.rideplan.model.RidePlan;
import com.internode_studios.spring_boot_application.rideplan.service.RidePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ridePlans")
public class RidePlanController {

    @Autowired
    private RidePlanService ridePlanService;

    // Create RidePlan
    @PostMapping("/create")
    public ResponseEntity<?> createRidePlan(@RequestBody RidePlan ridePlan) {
        try {
            RidePlan createdRidePlan = ridePlanService.createRidePlan(ridePlan);
            return ResponseEntity.ok(createdRidePlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Get All RidePlans
    @GetMapping("/getAll")
    public ResponseEntity<List<RidePlan>> getAllRidePlans() {
        List<RidePlan> ridePlans = ridePlanService.getAllRidePlans();
        return ResponseEntity.ok(ridePlans);
    }

//    @GetMapping("/getById/{id}")
//    public ResponseEntity<?> getRidePlanById(@PathVariable Long id) {
//        Optional<RidePlanDTO> ridePlanDTO = ridePlanService.getRidePlanByIdWithDetails(id);
//
//        return ridePlanDTO.map(rp -> ResponseEntity.ok((Object) rp))
//                .orElseGet(() -> ResponseEntity.status(404).body("RidePlan not found or details do not match"));
//    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getRidePlanById(@PathVariable Long id) {
        Optional<RidePlanDTO> ridePlanDTO = ridePlanService.getRidePlanByIdWithDetails(id);

        return ridePlanDTO.map(rp -> ResponseEntity.ok((Object) rp))
                .orElseGet(() -> ResponseEntity.status(404).body("RidePlan not found or details do not match"));
    }

    // Update RidePlan
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRidePlan(@PathVariable Long id, @RequestBody RidePlan ridePlanDetails) {
        Optional<RidePlan> existingRidePlan = ridePlanService.getRidePlanById(id);
        if (existingRidePlan.isPresent()) {
            RidePlan updatedRidePlan = ridePlanService.updateRidePlan(id, ridePlanDetails);
            return ResponseEntity.ok(updatedRidePlan);
        }
        return ResponseEntity.status(404).body("RidePlan not found");
    }

    // Delete RidePlan
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRidePlan(@PathVariable Long id) {
        Optional<RidePlan> ridePlan = ridePlanService.getRidePlanById(id);
        if (ridePlan.isPresent()) {
            ridePlanService.deleteRidePlan(id);
            return ResponseEntity.ok("RidePlan deleted successfully");
        }
        return ResponseEntity.status(404).body("RidePlan not found");
    }

//    // Get RidePlans by dropOffLocation, date, time, female
//    @GetMapping("/search")
//    public ResponseEntity<List<RidePlan>> getRidePlansByCriteria(
//            @RequestParam String dropOffLocation,
//            @RequestParam String date,
//            @RequestParam String time,
//            @RequestParam boolean female) {
//        List<RidePlan> ridePlans = ridePlanService.getRidePlansByCriteria(dropOffLocation, date, time, female);
//        return ResponseEntity.ok(ridePlans);
//    }

    // Endpoint to search RidePlans with role "driver"
    @GetMapping("/searchDriver")
    public ResponseEntity<List<RidePlan>> getRidePlansForDriver(
            @RequestParam String dropOffLocation,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam boolean female) {
        List<RidePlan> ridePlans = ridePlanService.getRidePlansForDriver(dropOffLocation, date, time, female);
        return ResponseEntity.ok(ridePlans);
    }

    // Endpoint to search RidePlans with role "partner"
    @GetMapping("/searchPartner")
    public ResponseEntity<List<RidePlan>> getRidePlansForPartner(
            @RequestParam String dropOffLocation,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam boolean female) {
        List<RidePlan> ridePlans = ridePlanService.getRidePlansForPartner(dropOffLocation, date, time, female);
        return ResponseEntity.ok(ridePlans);
    }
}
