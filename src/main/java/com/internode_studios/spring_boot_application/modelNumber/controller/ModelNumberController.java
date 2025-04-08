package com.internode_studios.spring_boot_application.modelNumber.controller;

import com.internode_studios.spring_boot_application.modelNumber.model.ModelNumber;
import com.internode_studios.spring_boot_application.modelNumber.service.ModelNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modelNumbers")
public class ModelNumberController {

    @Autowired
    private ModelNumberService modelNumberService;

    // Endpoint to create a new model number
    @PostMapping("/create")
    public ResponseEntity<ModelNumber> createModelNumber(@RequestBody ModelNumber modelNumber) {
        ModelNumber savedModelNumber = modelNumberService.saveModelNumber(modelNumber);
        return new ResponseEntity<>(savedModelNumber, HttpStatus.CREATED);
    }

    // Endpoint to get all model numbers
    @GetMapping("/getAll")
    public ResponseEntity<List<ModelNumber>> getAllModelNumbers() {
        List<ModelNumber> modelNumbers = modelNumberService.getAllModelNumbers();
        return new ResponseEntity<>(modelNumbers, HttpStatus.OK);
    }

    // Endpoint to get a model number by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getModelNumberById(@PathVariable Long id) {
        Optional<ModelNumber> modelNumber = modelNumberService.getModelNumberById(id);
        if (modelNumber.isPresent()) {
            return ResponseEntity.ok(modelNumber);
        } else {
            return ResponseEntity.status(404).body("Model number not found");
        }
    }

    // Endpoint to update an existing model number
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateModelNumber(@PathVariable Long id, @RequestBody ModelNumber modelNumberDetails) {
        ModelNumber updatedModelNumber = modelNumberService.updateModelNumber(id, modelNumberDetails);
        if (updatedModelNumber != null) {
            return ResponseEntity.ok(updatedModelNumber);
        }
        return ResponseEntity.status(404).body("Model number not found");
    }

    // Endpoint to delete a model number
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteModelNumber(@PathVariable Long id) {
        Optional<ModelNumber> modelNumber = modelNumberService.getModelNumberById(id);
        if (modelNumber.isPresent()) {
            modelNumberService.deleteModelNumber(id);
            return ResponseEntity.ok("Model number deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Model number not found");
        }
    }
}