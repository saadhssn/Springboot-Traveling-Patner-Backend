package com.internode_studios.spring_boot_application.availabletaxis.repository;

import com.internode_studios.spring_boot_application.availabletaxis.model.AvailableTaxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableTaxiRepository extends JpaRepository<AvailableTaxi, Long> {

    List<AvailableTaxi> findByRideStatusAndGenderAndCityId(
            @Param("rideStatus") String rideStatus,
            @Param("gender") String gender,
            @Param("cityId") Long cityId);

    List<AvailableTaxi> findByGenderAndCityId(String gender, Long cityId);

    Optional<AvailableTaxi> findByIdAndRideStatusAndGenderAndCityId(Long id, String rideStatus, String gender, Long cityId);

    // Add this method to fetch a taxi by ID, gender, and cityId without rideStatus
    Optional<AvailableTaxi> findByIdAndGenderAndCityId(Long id, String gender, Long cityId);
}

