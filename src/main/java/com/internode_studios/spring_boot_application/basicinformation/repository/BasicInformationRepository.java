package com.internode_studios.spring_boot_application.basicinformation.repository;


import com.internode_studios.spring_boot_application.basicinformation.model.BasicInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicInformationRepository extends JpaRepository<BasicInformation, Long> {
}
