package com.internode_studios.spring_boot_application.basicinformation.service;


import com.internode_studios.spring_boot_application.basicinformation.model.BasicInformation;
import com.internode_studios.spring_boot_application.basicinformation.repository.BasicInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicInformationService {

    @Autowired
    private BasicInformationRepository basicInformationRepository;

    public BasicInformation createBasicInformation(BasicInformation basicInformation) {
        return basicInformationRepository.save(basicInformation);
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
            existingInfo.setFirstName(updatedInfo.getFirstName());
            existingInfo.setLastName(updatedInfo.getLastName());
            existingInfo.setGender(updatedInfo.getGender());
            existingInfo.setWhatsApp(updatedInfo.getWhatsApp());
            existingInfo.setEmail(updatedInfo.getEmail());
            existingInfo.setCnicNumber(updatedInfo.getCnicNumber());
            existingInfo.setCnicFront(updatedInfo.getCnicFront());
            existingInfo.setCnicBack(updatedInfo.getCnicBack());
            existingInfo.setProfilePicture(updatedInfo.getProfilePicture());
            existingInfo.setReferralCode(updatedInfo.getReferralCode());
            return basicInformationRepository.save(existingInfo);
        }
        return null;
    }

    public void deleteBasicInformation(Long id) {
        basicInformationRepository.deleteById(id);
    }
}
