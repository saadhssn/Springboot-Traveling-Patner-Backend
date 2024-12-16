package com.internode_studios.spring_boot_application.area.service;

import com.internode_studios.spring_boot_application.area.model.Area;
import com.internode_studios.spring_boot_application.area.repository.AreaRepository;
import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.city.repository.CityRepository;
import com.internode_studios.spring_boot_application.state.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    // Create a new Area
    public Area createArea(Area area) {
        // Check if the stateId exists in the State table
        if (!stateRepository.existsById(area.getStateId())) {
            throw new IllegalArgumentException("State with ID " + area.getStateId() + " does not exist.");
        }

        // Check if the cityId exists in the City table
        if (!cityRepository.existsById(area.getCityId())) {
            throw new IllegalArgumentException("City with ID " + area.getCityId() + " does not exist.");
        }
        return areaRepository.save(area);
    }

    // Get all Areas
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    // Get Area by ID
    public Optional<Area> getAreaById(Long id) {
        return areaRepository.findById(id);
    }

    // Update Area by ID
    public Area updateArea(Long id, Area areaDetails) {
        // Check if the Area exists
        Area area = areaRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Area with ID " + id + " does not exist."));

        // Check if the stateId exists in the State table
        if (!stateRepository.existsById(area.getStateId())) {
            throw new IllegalArgumentException("State with ID " + area.getStateId() + " does not exist.");
        }

        // Check if the cityId exists in the City table
        if (!cityRepository.existsById(area.getCityId())) {
            throw new IllegalArgumentException("City with ID " + area.getCityId() + " does not exist.");
        }

        // Update fields
        area.setName(areaDetails.getName());
        area.setCityId(areaDetails.getCityId());
        area.setStateId(areaDetails.getStateId());

        return areaRepository.save(area);
    }

    // Delete Area by ID
    public void deleteArea(Long id) {
        if (!areaRepository.existsById(id)) {
            throw new IllegalArgumentException("Area with ID " + id + " does not exist.");
        }
        areaRepository.deleteById(id);
    }
}
