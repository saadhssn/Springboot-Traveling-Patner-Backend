package com.internode_studios.spring_boot_application.rideplan.repository;

import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.rideplan.model.RidePlan;
import com.internode_studios.spring_boot_application.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RidePlanRepository extends JpaRepository<RidePlan, Long> {

    @Override
    Optional<RidePlan> findById(Long id);

}
