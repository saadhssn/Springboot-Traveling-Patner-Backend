package com.internode_studios.spring_boot_application.license.repository;

import com.internode_studios.spring_boot_application.license.model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    License findByUserId(Long userId);
}
