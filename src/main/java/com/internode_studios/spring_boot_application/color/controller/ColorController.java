package com.internode_studios.spring_boot_application.color.controller;


import com.internode_studios.spring_boot_application.color.model.Color;
import com.internode_studios.spring_boot_application.color.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

    @Autowired
    private ColorService colorService;

    // Endpoint to create a new color
    @PostMapping("/create")
    public ResponseEntity<Color> createColor(@RequestBody Color color) {
        Color savedColor = colorService.saveColor(color);
        return new ResponseEntity<>(savedColor, HttpStatus.CREATED);
    }

    // Endpoint to get all colors
    @GetMapping("/getAll")
    public ResponseEntity<List<Color>> getAllColors() {
        List<Color> colors = colorService.getAllColors();
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    // Endpoint to get a color by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getColorById(@PathVariable Long id) {
        Optional<Color> color = colorService.getColorById(id);
        if (color.isPresent()) {
            return ResponseEntity.ok(color);
        } else {
            return ResponseEntity.status(404).body("Color not found");
        }
    }

    // Endpoint to update an existing color
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateColor(@PathVariable Long id, @RequestBody Color colorDetails) {
        Color updatedColor = colorService.updateColor(id, colorDetails);
        if (updatedColor != null) {
            return ResponseEntity.ok(updatedColor);
        }
        return ResponseEntity.status(404).body("Color not found");
    }

    // Endpoint to delete a color
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable Long id) {
        Optional<Color> color = colorService.getColorById(id);
        if (color.isPresent()) {
            colorService.deleteColor(id);
            return ResponseEntity.ok("Color deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Color not found");
        }
    }
}
