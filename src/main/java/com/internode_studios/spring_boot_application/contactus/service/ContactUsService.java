package com.internode_studios.spring_boot_application.contactus.service;

import com.internode_studios.spring_boot_application.contactus.dto.ContactUsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendContactUsEmail(ContactUsDTO contactUsDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("travelingpartnertravelapp@gmail.com"); // Replace with the recipient's email
        message.setSubject(contactUsDTO.getSubject());
        message.setText("Name: " + contactUsDTO.getName() +
                "\nEmail: " + contactUsDTO.getEmail() +
                "\nPhone Number: " + contactUsDTO.getPhoneNumber() +
                "\nMessage: " + contactUsDTO.getMessage());
        emailSender.send(message);
    }
}
