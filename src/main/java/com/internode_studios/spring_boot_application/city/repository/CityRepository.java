package com.internode_studios.spring_boot_application.city.repository;

import com.internode_studios.spring_boot_application.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}