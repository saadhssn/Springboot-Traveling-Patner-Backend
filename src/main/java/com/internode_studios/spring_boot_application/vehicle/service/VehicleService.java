package com.internode_studios.spring_boot_application.vehicle.service;

import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import com.internode_studios.spring_boot_application.vehicle.model.Vehicle;
import com.internode_studios.spring_boot_application.vehicle.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    // Method to get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Method to find a vehicle by ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Transactional
    public Vehicle saveVehicle(Vehicle vehicle) {
        // Save the vehicle first
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        // Find the user associated with this vehicle
        User user = userRepository.findById(vehicle.getUserId()).orElse(null);

        if (user != null) {
            // Update the user with the new vehicle ID
            user.setVehicleId(savedVehicle.getId());
            userRepository.save(user);

            System.out.println("User updated with new vehicleId: " + user.getVehicleId());
        } else {
            System.out.println("User not found for ID: " + vehicle.getUserId());
        }

        return savedVehicle; // Return the saved vehicle
    }

    // Method to update an existing vehicle
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        if (vehicle != null) {
            // Update fields
            vehicle.setUserId(vehicleDetails.getUserId());
            vehicle.setModelNo(vehicleDetails.getModelNo());
            vehicle.setVehicleColor(vehicleDetails.getVehicleColor());
            vehicle.setVehicleBrand(vehicleDetails.getVehicleBrand());
            vehicle.setRegistrationNo(vehicleDetails.getRegistrationNo());
            vehicle.setRegistrationFront(vehicleDetails.getRegistrationFront());
            vehicle.setRegistrationBack(vehicleDetails.getRegistrationBack());
            vehicle.setOutdoorImages(vehicleDetails.getOutdoorImages());
            vehicle.setIndoorImages(vehicleDetails.getIndoorImages());
            vehicle.setVehicleType(vehicleDetails.getVehicleType());
            vehicle.setAc(vehicleDetails.isAc());
            vehicle.setPetsAllowed(vehicleDetails.isPetsAllowed());
            vehicle.setSmokingAllowed(vehicleDetails.isSmokingAllowed());
            return vehicleRepository.save(vehicle);
        }
        return null; // Or throw an exception
    }

    // Method to delete a vehicle
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
