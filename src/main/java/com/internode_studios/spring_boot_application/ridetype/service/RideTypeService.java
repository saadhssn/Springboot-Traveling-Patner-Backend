package com.internode_studios.spring_boot_application.ridetype.service;

import com.internode_studios.spring_boot_application.ridetype.model.RideType;
import com.internode_studios.spring_boot_application.ridetype.repository.RideTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideTypeService {

    @Autowired
    private RideTypeRepository rideTypeRepository;

    // Create a new RideType
    public RideType createRideType(RideType rideType) {
        return rideTypeRepository.save(rideType);
    }

    // Get all RideTypes
    public List<RideType> getAllRideTypes() {
        return rideTypeRepository.findAll();
    }

    // Get RideType by ID
    public Optional<RideType> getRideTypeById(Long id) {
        return rideTypeRepository.findById(id);
    }

    // Update RideType by ID
    public RideType updateRideType(Long id, RideType rideTypeDetails) {
        RideType rideType = rideTypeRepository.findById(id).orElse(null);
        if (rideType != null) {
            // Update fields
            rideType.setType(rideTypeDetails.getType());
            return rideTypeRepository.save(rideType);
        }
        return null; // Or throw an exception
    }

    // Delete RideType by ID
    public void deleteRideType(Long id) {
        rideTypeRepository.deleteById(id);
    }
}
