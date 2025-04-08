package com.internode_studios.spring_boot_application.color.service;

import com.internode_studios.spring_boot_application.color.model.Color;
import com.internode_studios.spring_boot_application.color.repository.ColorRepository;
import com.internode_studios.spring_boot_application.modelNumber.model.ModelNumber;
import com.internode_studios.spring_boot_application.modelNumber.repository.ModelNumberRepository;
import com.internode_studios.spring_boot_application.vehicletype.model.VehicleType;
import com.internode_studios.spring_boot_application.vehicletype.repository.VehicleTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private ModelNumberRepository modelNumberRepository;


    // Method to get all colors
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    // Method to find a color by ID
    public Optional<Color> getColorById(Long id) {
        return colorRepository.findById(id);
    }

    // Method to save a new color
    @Transactional
    public Color saveColor(Color color) {
        // Validate vehicleTypeId
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(color.getVehicleTypeId());
        if (vehicleType.isEmpty()) {
            throw new IllegalArgumentException("Invalid vehicleTypeId: Vehicle type must be present in the 'vehicle_type' table.");
        }

        Optional<ModelNumber> modelNumber = modelNumberRepository.findById(color.getModelNumberId());
        if (modelNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid modelNumberId: Model Number must be present in the 'model_number' table.");
        }

        // Save the color if the vehicleTypeId is valid
        return colorRepository.save(color);
    }

    // Method to update an existing color
    @Transactional
    public Color updateColor(Long id, Color colorDetails) {
        Color color = colorRepository.findById(id).orElse(null);
        if (color != null) {
            // Validate vehicleTypeId
            Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(colorDetails.getVehicleTypeId());
            if (vehicleType.isEmpty()) {
                throw new IllegalArgumentException("Invalid vehicleTypeId: Vehicle type must be present in the 'vehicle_types' table.");
            }
            Optional<ModelNumber> modelNumber = modelNumberRepository.findById(color.getModelNumberId());
            if (modelNumber.isEmpty()) {
                throw new IllegalArgumentException("Invalid modelNumberId: Model Number must be present in the 'model_number' table.");
            }

            // Update the color details
            color.setName(colorDetails.getName());
            color.setVehicleTypeId(colorDetails.getVehicleTypeId());
            color.setModelNumberId(colorDetails.getModelNumberId());

            // Save the updated color
            return colorRepository.save(color);
        }
        return null; // Or throw an exception if color not found
    }

    // Method to delete a color
    @Transactional
    public void deleteColor(Long id) {
        colorRepository.deleteById(id);
    }
}