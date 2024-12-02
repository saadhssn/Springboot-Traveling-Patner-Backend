package com.internode_studios.spring_boot_application.banner.repository;

import com.internode_studios.spring_boot_application.banner.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
}
