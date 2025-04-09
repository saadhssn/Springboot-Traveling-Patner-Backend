package com.internode_studios.spring_boot_application.brand.controller;


import com.internode_studios.spring_boot_application.brand.model.Brand;
import com.internode_studios.spring_boot_application.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    // Endpoint to create a new brand
    @PostMapping("/create")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand savedBrand = brandService.saveBrand(brand);
        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

    // Endpoint to get all brands
    @GetMapping("/getAll")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    // Endpoint to get a brand by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id) {
        Optional<Brand> brand = brandService.getBrandById(id);
        if (brand.isPresent()) {
            return ResponseEntity.ok(brand);
        } else {
            return ResponseEntity.status(404).body("Brand not found");
        }
    }

    // Endpoint to update an existing brand
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Long id, @RequestBody Brand brandDetails) {
        Brand updatedBrand = brandService.updateBrand(id, brandDetails);
        if (updatedBrand != null) {
            return ResponseEntity.ok(updatedBrand);
        }
        return ResponseEntity.status(404).body("Brand not found");
    }

    // Endpoint to delete a brand
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
        Optional<Brand> brand = brandService.getBrandById(id);
        if (brand.isPresent()) {
            brandService.deleteBrand(id);
            return ResponseEntity.ok("Brand deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Brand not found");
        }
    }
}
