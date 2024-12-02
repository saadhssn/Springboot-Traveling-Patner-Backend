package com.internode_studios.spring_boot_application.banner.service;

import com.internode_studios.spring_boot_application.banner.model.Banner;
import com.internode_studios.spring_boot_application.banner.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    // Create a new Banner
    public Banner createBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    // Get all Banner
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }

    // Get Banner by ID
    public Optional<Banner> getBannerById(Long id) {
        return bannerRepository.findById(id);
    }

    // Update Banner by ID
    public Banner updateBanner(Long id, Banner updatedBanner) {
        return bannerRepository.findById(id).map(detail -> {
            detail.setBanner(updatedBanner.getBanner());
            detail.setTitle1(updatedBanner.getTitle1());
            detail.setDescription1(updatedBanner.getDescription1());
            detail.setTitle2(updatedBanner.getTitle2());
            detail.setDescription2(updatedBanner.getDescription2());
            detail.setTitle3(updatedBanner.getTitle3());
            detail.setDescription3(updatedBanner.getDescription3());
            detail.setCoverImage(updatedBanner.getCoverImage());
            detail.setImage1(updatedBanner.getImage1());
            detail.setImage2(updatedBanner.getImage2());
            detail.setImage3(updatedBanner.getImage3());
            return bannerRepository.save(detail);
        }).orElseThrow(() -> new RuntimeException("Banner not found"));
    }

    // Delete Banner by ID
    public void deleteBanner(Long id) {
        bannerRepository.deleteById(id);
    }
}
