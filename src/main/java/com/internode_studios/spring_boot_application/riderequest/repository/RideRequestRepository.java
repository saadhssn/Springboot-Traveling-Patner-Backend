package com.internode_studios.spring_boot_application.riderequest.repository;

import com.internode_studios.spring_boot_application.riderequest.model.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}