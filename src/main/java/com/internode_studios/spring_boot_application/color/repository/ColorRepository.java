package com.internode_studios.spring_boot_application.color.repository;

import com.internode_studios.spring_boot_application.color.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    // Additional custom queries can be added here if needed
}
