package com.internode_studios.spring_boot_application.area.controller;


import com.internode_studios.spring_boot_application.area.model.Area;
import com.internode_studios.spring_boot_application.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    // Create City
    @PostMapping("/create")
    public ResponseEntity<Area> createArea(@RequestBody Area area) {
        Area createdArea = areaService.createArea(area);
        return ResponseEntity.ok(createdArea);
    }

    // Get All Areas
    @GetMapping("/getAll")
    public ResponseEntity<List<Area>> getAllAreas() {
        List<Area> areas = areaService.getAllAreas();
        return ResponseEntity.ok(areas);
    }

    // Get Area by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable Long id) {
        Optional<Area> area = areaService.getAreaById(id);
        return area.map(c -> ResponseEntity.ok((Object) c)) // Cast city object to Object
                .orElseGet(() -> ResponseEntity.status(404).body("Area not found"));
    }

    // Update Area
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArea(@PathVariable Long id, @RequestBody Area areaDetails) {
        Optional<Area> existingArea = areaService.getAreaById(id);
        if (existingArea.isPresent()) {
            Area updateArea = areaService.updateArea(id, areaDetails);
            return ResponseEntity.ok(updateArea);
        }
        return ResponseEntity.status(404).body("City not found");
    }

    // Delete Area
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArea(@PathVariable Long id) {
        Optional<Area> area = areaService.getAreaById(id);
        if (area.isPresent()) {
            areaService.deleteArea(id);
            return ResponseEntity.ok("Area deleted successfully");
        }
        return ResponseEntity.status(404).body("Area not found");
    }
}
