package com.internode_studios.spring_boot_application.brand.repository;

import com.internode_studios.spring_boot_application.brand.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    // Additional custom queries can be added here if needed
}