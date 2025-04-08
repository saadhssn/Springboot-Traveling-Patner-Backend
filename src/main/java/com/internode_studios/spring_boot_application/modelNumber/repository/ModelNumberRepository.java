package com.internode_studios.spring_boot_application.modelNumber.repository;

import com.internode_studios.spring_boot_application.modelNumber.model.ModelNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelNumberRepository extends JpaRepository<ModelNumber, Long> {
    // Additional custom queries can be added here if needed
}
