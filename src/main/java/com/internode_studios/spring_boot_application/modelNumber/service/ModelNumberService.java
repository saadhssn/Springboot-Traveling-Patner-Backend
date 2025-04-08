package com.internode_studios.spring_boot_application.modelNumber.service;

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
public class ModelNumberService {


    @Autowired
    private ModelNumberRepository modelNumberRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    // Method to get all model numbers
    public List<ModelNumber> getAllModelNumbers() {
        return modelNumberRepository.findAll();
    }

    // Method to find a model number by ID
    public Optional<ModelNumber> getModelNumberById(Long id) {
        return modelNumberRepository.findById(id);
    }

    // Method to save a new model number
    @Transactional
    public ModelNumber saveModelNumber(ModelNumber modelNumber) {
        // Validate vehicleTypeId
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(modelNumber.getVehicleTypeId());
        if (vehicleType.isEmpty()) {
            throw new IllegalArgumentException("Invalid vehicleTypeId: Vehicle type must be present in the 'vehicle_types' table.");
        }

        // Save the model number if the vehicleTypeId is valid
        return modelNumberRepository.save(modelNumber);
    }


    @Transactional
    public ModelNumber updateModelNumber(Long id, ModelNumber modelNumberDetails) {
        ModelNumber modelNumber = modelNumberRepository.findById(id).orElse(null);
        if (modelNumber != null) {
            // Validate vehicleTypeId
            Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(modelNumberDetails.getVehicleTypeId());
            if (vehicleType.isEmpty()) {
                throw new IllegalArgumentException("Invalid vehicleTypeId: Vehicle type must be present in the 'vehicle_types' table.");
            }

            // Update the model number details
            modelNumber.setName(modelNumberDetails.getName());
            modelNumber.setVehicleTypeId(modelNumberDetails.getVehicleTypeId());

            // Save the updated model number
            return modelNumberRepository.save(modelNumber);
        }
        return null; // Or throw an exception if model number not found
    }


    // Method to delete a model number
    @Transactional
    public void deleteModelNumber(Long id) {
        modelNumberRepository.deleteById(id);
    }
}
