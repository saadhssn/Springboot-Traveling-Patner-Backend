package com.internode_studios.spring_boot_application.basicinformation.controller;


import com.internode_studios.spring_boot_application.basicinformation.model.BasicInformation;
import com.internode_studios.spring_boot_application.basicinformation.service.BasicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/basicInformation")
public class BasicInformationController {

    @Autowired
    private BasicInformationService basicInformationService;

    @PostMapping("/create")
    public ResponseEntity<BasicInformation> createBasicInformation(@RequestBody BasicInformation basicInformation) {
        BasicInformation createdInfo = basicInformationService.createBasicInformation(basicInformation);
        return ResponseEntity.ok(createdInfo);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BasicInformation>> getAllBasicInformation() {
        List<BasicInformation> infoList = basicInformationService.getAllBasicInformation();
        return ResponseEntity.ok(infoList);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getBasicInformationById(@PathVariable Long id) {
        BasicInformation info = basicInformationService.getBasicInformationById(id);
        if (info != null) {
            return ResponseEntity.ok(info);
        } else {
            return ResponseEntity.status(404).body("Basic Information not found");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBasicInformation(@PathVariable Long id, @RequestBody BasicInformation updatedInfo) {
        Optional<BasicInformation> existingInfo = Optional.ofNullable(basicInformationService.getBasicInformationById(id));
        if (existingInfo.isPresent()) {
            BasicInformation info = basicInformationService.updateBasicInformation(id, updatedInfo);
            return ResponseEntity.ok(info);
        }
        return ResponseEntity.status(404).body("Basic Information not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBasicInformation(@PathVariable Long id) {
        Optional<BasicInformation> basicInformation = Optional.ofNullable(basicInformationService.getBasicInformationById(id));
        if (basicInformation.isPresent()) {
            basicInformationService.deleteBasicInformation(id);
            return ResponseEntity.ok("Basic Information deleted successfully");
        }
        return ResponseEntity.status(404).body("Basic Information not found");
    }
}
