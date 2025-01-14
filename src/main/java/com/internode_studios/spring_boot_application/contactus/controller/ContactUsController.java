package com.internode_studios.spring_boot_application.contactus.controller;

import com.internode_studios.spring_boot_application.contactus.dto.ContactUsDTO;
import com.internode_studios.spring_boot_application.contactus.service.ContactUsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @PostMapping
    public ResponseEntity<String> contactUs(@RequestBody ContactUsDTO contactUsDTO) {
        try {
            contactUsService.sendContactUsEmail(contactUsDTO);
            return ResponseEntity.ok("Contact message sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while sending the message.");
        }
    }
}