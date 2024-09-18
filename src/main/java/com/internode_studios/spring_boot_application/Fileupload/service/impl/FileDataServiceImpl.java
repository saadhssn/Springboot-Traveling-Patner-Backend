package com.internode_studios.spring_boot_application.Fileupload.service.impl;

import com.internode_studios.spring_boot_application.Fileupload.entity.FileData;
import com.internode_studios.spring_boot_application.Fileupload.repository.FileDataRepository;
import com.internode_studios.spring_boot_application.Fileupload.service.FileDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileDataServiceImpl implements FileDataService {

    private final FileDataRepository fileDataRepository;

    private final String FOLDER_PATH = "D:\\Saad\\spring-boot\\spring-boot-application\\spring-boot-application\\src\\main\\resources\\static\\datapart";

    @Override
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        // Ensure folder exists
        File directory = new File(FOLDER_PATH);
        if (!directory.exists()) {
            directory.mkdirs(); // Create directories if they don't exist
        }

        // Define the file path
        String filePath = FOLDER_PATH + File.separator + file.getOriginalFilename();

        // Save file details to the database
        FileData fileData = fileDataRepository.save(
                FileData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath).build()
        );

        // Transfer file to the defined location
        file.transferTo(new File(filePath));

        // Return response
        if (fileData != null) {
            return "File uploaded successfully: " + filePath;
        }
        return "File upload failed";
    }

    @Override
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.orElseThrow(() ->
                new RuntimeException("File not found with name: " + fileName)).getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }
}
