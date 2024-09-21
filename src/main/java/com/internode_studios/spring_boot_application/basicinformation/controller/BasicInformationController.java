package com.internode_studios.spring_boot_application.basicinformation.controller;


import com.internode_studios.spring_boot_application.basicinformation.model.BasicInformation;
import com.internode_studios.spring_boot_application.basicinformation.service.BasicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basic-information")
public class BasicInformationController {

    @Autowired
    private BasicInformationService basicInformationService;

    @PostMapping
    public ResponseEntity<BasicInformation> createBasicInformation(@RequestBody BasicInformation basicInformation) {
        BasicInformation createdInfo = basicInformationService.createBasicInformation(basicInformation);
        return ResponseEntity.ok(createdInfo);
    }

    @GetMapping
    public ResponseEntity<List<BasicInformation>> getAllBasicInformation() {
        List<BasicInformation> infoList = basicInformationService.getAllBasicInformation();
        return ResponseEntity.ok(infoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicInformation> getBasicInformationById(@PathVariable Long id) {
        BasicInformation info = basicInformationService.getBasicInformationById(id);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasicInformation> updateBasicInformation(@PathVariable Long id, @RequestBody BasicInformation updatedInfo) {
        BasicInformation info = basicInformationService.updateBasicInformation(id, updatedInfo);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasicInformation(@PathVariable Long id) {
        basicInformationService.deleteBasicInformation(id);
        return ResponseEntity.noContent().build();
    }
}
