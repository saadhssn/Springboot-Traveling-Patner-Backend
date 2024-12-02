package com.internode_studios.spring_boot_application.location.repository;

import com.internode_studios.spring_boot_application.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
