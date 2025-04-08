package com.internode_studios.spring_boot_application.basicinformation.service;

import com.internode_studios.spring_boot_application.basicinformation.model.BasicInformation;
import com.internode_studios.spring_boot_application.basicinformation.repository.BasicInformationRepository;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicInformationService {

    @Autowired
    private BasicInformationRepository basicInformationRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public BasicInformation createBasicInformation(BasicInformation basicInformation) {
        User user = userRepository.findById(basicInformation.getUserId()).orElse(null);

        if (user != null) {
            // Save Basic Information
            basicInformation = basicInformationRepository.save(basicInformation);
            System.out.println("Basic Information saved with ID: " + basicInformation.getId());

            // Update user's basicInformationId
            user.setBasicInformationId(basicInformation.getId());
            userRepository.save(user);

            System.out.println("User updated with new basicInformationId: " + user.getBasicInformationId());
            return basicInformation;
        }

        System.out.println("User not found with ID: " + basicInformation.getUserId());
        return null;
    }

    public List<BasicInformation> getAllBasicInformation() {
        return basicInformationRepository.findAll();
    }

    public BasicInformation getBasicInformationById(Long id) {
        return basicInformationRepository.findById(id).orElse(null);
    }

    public BasicInformation updateBasicInformation(Long id, BasicInformation updatedInfo) {
        BasicInformation existingInfo = getBasicInformationById(id);
        if (existingInfo != null) {
            // Update only the non-null fields
            if (updatedInfo.getFirstName() != null) {
                existingInfo.setFirstName(updatedInfo.getFirstName());
            }
            if (updatedInfo.getLastName() != null) {
                existingInfo.setLastName(updatedInfo.getLastName());
            }
            if (updatedInfo.getGender() != null) {
                existingInfo.setGender(updatedInfo.getGender());
            }
            if (updatedInfo.getWhatsApp() != null) {
                existingInfo.setWhatsApp(updatedInfo.getWhatsApp());
            }
            if (updatedInfo.getEmail() != null) {
                existingInfo.setEmail(updatedInfo.getEmail());
            }
            if (updatedInfo.getCnicNumber() != null) {
                existingInfo.setCnicNumber(updatedInfo.getCnicNumber());
            }
            if (updatedInfo.getCnicFront() != null) {
                existingInfo.setCnicFront(updatedInfo.getCnicFront());
            }
            if (updatedInfo.getCnicBack() != null) {
                existingInfo.setCnicBack(updatedInfo.getCnicBack());
            }
            if (updatedInfo.getProfilePicture() != null) {
                existingInfo.setProfilePicture(updatedInfo.getProfilePicture());
            }
            if (updatedInfo.getReferralCode() != null) {
                existingInfo.setReferralCode(updatedInfo.getReferralCode());
            }
            if (updatedInfo.getAcceptTerm() != null) {
                existingInfo.setAcceptTerm(updatedInfo.getAcceptTerm());
            }
            return basicInformationRepository.save(existingInfo);
        }
        throw new IllegalArgumentException("Basic Information with ID " + id + " does not exist.");
    }

    public void deleteBasicInformation(Long id) {
        basicInformationRepository.deleteById(id);
    }
}
