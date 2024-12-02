package com.internode_studios.spring_boot_application.banner.controller;

import com.internode_studios.spring_boot_application.banner.model.Banner;
import com.internode_studios.spring_boot_application.banner.service.BannerService;
import com.internode_studios.spring_boot_application.basicinformation.model.BasicInformation;
import com.internode_studios.spring_boot_application.city.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    // Create Banner
    @PostMapping("/create")
    public ResponseEntity<Banner> createBanner(@RequestBody Banner banner) {
        Banner createdDetail = bannerService.createBanner(banner);
        return ResponseEntity.ok(createdDetail);
    }

    // Get All Banners
    @GetMapping("/getAll")
    public ResponseEntity<List<Banner>> getAllBanners() {
        List<Banner> details = bannerService.getAllBanners();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getBannerById(@PathVariable Long id) {
        Optional<Banner> banner = bannerService.getBannerById(id);
        return banner.map(c -> ResponseEntity.ok((Object) c)) // Cast city object to Object
                .orElseGet(() -> ResponseEntity.status(404).body("Banner not found"));
    }

    // Update Banner
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBanner(@PathVariable Long id, @RequestBody Banner updatedBanner) {
        try {
            Banner detail = bannerService.updateBanner(id, updatedBanner);
            return ResponseEntity.ok(detail);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Delete Banner
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBanner(@PathVariable Long id) {
        Optional<Banner> detail = bannerService.getBannerById(id);
        if (detail.isPresent()) {
            bannerService.deleteBanner(id);
            return ResponseEntity.ok("Banner deleted successfully");
        }
        return ResponseEntity.status(404).body("Banner not found");
    }
}
