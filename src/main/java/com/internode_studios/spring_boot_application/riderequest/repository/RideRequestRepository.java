package com.internode_studios.spring_boot_application.riderequest.repository;

import com.internode_studios.spring_boot_application.riderequest.model.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
    List<RideRequest> findByRidePlanIdAndDate(Long ridePlanId, String date);

    List<RideRequest> findByDriverIdAndRideStatus(Long driverId, String rideStatus);

    List<RideRequest> findByPartnerIdAndRideStatus(Long partnerId, String rideStatus);
}