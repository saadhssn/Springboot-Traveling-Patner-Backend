package com.internode_studios.spring_boot_application.Fileupload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileDataService {

    String uploadImageToFileSystem(MultipartFile file) throws IOException;

    byte[] downloadImageFromFileSystem(String fileName) throws IOException;
}
