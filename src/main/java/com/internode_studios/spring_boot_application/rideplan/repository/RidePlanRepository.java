package com.internode_studios.spring_boot_application.rideplan.repository;

import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.rideplan.model.RidePlan;
import com.internode_studios.spring_boot_application.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RidePlanRepository extends JpaRepository<RidePlan, Long> {

    @Override
    Optional<RidePlan> findById(Long id);

    List<RidePlan> findByDropOffLocationAndDateAndTimeAndFemale(
            String dropOffLocation, String date, String time, boolean female);
}
