package com.internode_studios.spring_boot_application.state.repository;

import com.internode_studios.spring_boot_application.state.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
