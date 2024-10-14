package com.internode_studios.spring_boot_application.ridetype.repository;

import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.ridetype.model.RideType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RideTypeRepository extends JpaRepository<RideType, Long> {

    Optional<RideType> findById(Long id);
}
