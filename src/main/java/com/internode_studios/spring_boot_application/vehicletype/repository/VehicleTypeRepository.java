package com.internode_studios.spring_boot_application.vehicletype.repository;

import com.internode_studios.spring_boot_application.vehicletype.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
    // Custom queries can be added here if needed
}
