package com.internode_studios.spring_boot_application.state.repository;

import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.state.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateRepository extends JpaRepository<State, Long> {

    Optional<State> findById(Long id);
}
