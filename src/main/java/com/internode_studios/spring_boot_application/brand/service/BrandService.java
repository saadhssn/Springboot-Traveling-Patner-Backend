package com.internode_studios.spring_boot_application.brand.service;

import com.internode_studios.spring_boot_application.brand.model.Brand;
import com.internode_studios.spring_boot_application.brand.repository.BrandRepository;
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
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private ModelNumberRepository modelNumberRepository;


    @Autowired
    private ColorRepository colorRepository;

    // Method to get all brands
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    // Method to find a brand by ID
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    // Method to save a new brand
    @Transactional
    public Brand saveBrand(Brand brand) {
        // Validate vehicleTypeId
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(brand.getVehicleTypeId());
        if (vehicleType.isEmpty()) {
            throw new IllegalArgumentException("Invalid vehicleTypeId: Vehicle type must be present in the 'vehicle_types' table.");
        }

        Optional<ModelNumber> modelNumber = modelNumberRepository.findById(brand.getModelNumberId());
        if (modelNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid modelNumberId: Model Number must be present in the 'model_number' table.");
        }

        // Validate colorId
        Optional<Color> color = colorRepository.findById(brand.getColorId());
        if (color.isEmpty()) {
            throw new IllegalArgumentException("Invalid colorId: Color must be present in the 'colors' table.");
        }

        // Save the brand if the vehicleTypeId and colorId are valid
        return brandRepository.save(brand);
    }

    // Method to update an existing brand
    @Transactional
    public Brand updateBrand(Long id, Brand brandDetails) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand != null) {
            // Validate vehicleTypeId
            Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(brandDetails.getVehicleTypeId());
            if (vehicleType.isEmpty()) {
                throw new IllegalArgumentException("Invalid vehicleTypeId: Vehicle type must be present in the 'vehicle_types' table.");
            }

            Optional<ModelNumber> modelNumber = modelNumberRepository.findById(brand.getModelNumberId());
            if (modelNumber.isEmpty()) {
                throw new IllegalArgumentException("Invalid modelNumberId: Model Number must be present in the 'model_number' table.");
            }

            // Validate colorId
            Optional<Color> color = colorRepository.findById(brandDetails.getColorId());
            if (color.isEmpty()) {
                throw new IllegalArgumentException("Invalid colorId: Color must be present in the 'colors' table.");
            }

            // Update the brand details
            brand.setName(brandDetails.getName());
            brand.setVehicleTypeId(brandDetails.getVehicleTypeId());
            brand.setColorId(brandDetails.getColorId());
            brand.setModelNumberId(brandDetails.getModelNumberId());

            // Save the updated brand
            return brandRepository.save(brand);
        }
        return null; // Or throw an exception if brand not found
    }

    // Method to delete a brand
    @Transactional
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}