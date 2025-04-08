package com.internode_studios.spring_boot_application.vehicletype.service;

import com.internode_studios.spring_boot_application.vehicletype.model.VehicleType;
import com.internode_studios.spring_boot_application.vehicletype.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    // Method to get all vehicle types
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    // Method to find a vehicle type by ID
    public Optional<VehicleType> getVehicleTypeById(Long id) {
        return vehicleTypeRepository.findById(id);
    }

    // Method to save a new vehicle type
    public VehicleType saveVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    // Method to update an existing vehicle type
    public VehicleType updateVehicleType(Long id, VehicleType vehicleTypeDetails) {
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
        if (vehicleType.isPresent()) {
            VehicleType existingVehicleType = vehicleType.get();
            existingVehicleType.setName(vehicleTypeDetails.getName());
            return vehicleTypeRepository.save(existingVehicleType);
        }
        return null; // Or throw an exception if needed
    }

    // Method to delete a vehicle type
    public void deleteVehicleType(Long id) {
        vehicleTypeRepository.deleteById(id);
    }
}