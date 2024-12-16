package com.internode_studios.spring_boot_application.state.service;

import com.internode_studios.spring_boot_application.banner.model.Banner;
import com.internode_studios.spring_boot_application.banner.repository.BannerRepository;
import com.internode_studios.spring_boot_application.state.model.State;
import com.internode_studios.spring_boot_application.state.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    // Create a new State
    public State createState(State state) {
        return stateRepository.save(state);
    }

    // Get all State
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    // Get State by ID
    public Optional<State> getStateById(Long id) {
        return stateRepository.findById(id);
    }

    // Update State by ID
    public State updateState(Long id, State updatedState) {
        return stateRepository.findById(id).map(existingState -> {
            // Update fields only if they are non-null
            if (updatedState.getName() != null) {
                existingState.setName(updatedState.getName());
            }
            // Save the updated state
            return stateRepository.save(existingState);
        }).orElseThrow(() -> new RuntimeException("State not found with ID: " + id));
    }


    // Delete State by ID
    public void deleteState(Long id) {
        stateRepository.deleteById(id);
    }
}
