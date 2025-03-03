package com.internode_studios.spring_boot_application.Cloudinary.repository;

import com.internode_studios.spring_boot_application.Cloudinary.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
