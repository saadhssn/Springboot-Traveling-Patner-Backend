package com.internode_studios.spring_boot_application.area.repository;

import com.internode_studios.spring_boot_application.area.model.Area;
import com.internode_studios.spring_boot_application.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {

    Optional<Area> findById(Long id);
}
