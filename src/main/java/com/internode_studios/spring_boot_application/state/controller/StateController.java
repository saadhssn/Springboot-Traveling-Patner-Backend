package com.internode_studios.spring_boot_application.state.controller;

import com.internode_studios.spring_boot_application.state.model.State;
import com.internode_studios.spring_boot_application.state.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    private StateService stateService;

    // Create State
    @PostMapping("/create")
    public ResponseEntity<State> createState(@RequestBody State state) {
        State createdDetail = stateService.createState(state);
        return ResponseEntity.ok(createdDetail);
    }

    // Get All States
    @GetMapping("/getAll")
    public ResponseEntity<List<State>> getAllStates() {
        List<State> details = stateService.getAllStates();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getStateById(@PathVariable Long id) {
        Optional<State> state = stateService.getStateById(id);
        return state.map(c -> ResponseEntity.ok((Object) c)) // Cast city object to Object
                .orElseGet(() -> ResponseEntity.status(404).body("State not found"));
    }

    // Update State
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateState(@PathVariable Long id, @RequestBody State updatedState) {
        try {
            State detail = stateService.updateState(id, updatedState);
            return ResponseEntity.ok(detail);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Delete State
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteState(@PathVariable Long id) {
        Optional<State> detail = stateService.getStateById(id);
        if (detail.isPresent()) {
            stateService.deleteState(id);
            return ResponseEntity.ok("State deleted successfully");
        }
        return ResponseEntity.status(404).body("State not found");
    }
}
