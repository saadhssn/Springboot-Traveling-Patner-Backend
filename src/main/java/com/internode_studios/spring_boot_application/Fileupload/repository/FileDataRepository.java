package com.internode_studios.spring_boot_application.Fileupload.repository;

import com.internode_studios.spring_boot_application.Fileupload.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByName(String fileName);
}
