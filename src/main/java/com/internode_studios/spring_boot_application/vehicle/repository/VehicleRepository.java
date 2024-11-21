package com.internode_studios.spring_boot_application.vehicle.repository;

import com.internode_studios.spring_boot_application.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // Additional custom queries can be added here if needed
}
