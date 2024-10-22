package com.internode_studios.spring_boot_application.availabletaxis.service;

import com.internode_studios.spring_boot_application.availabletaxis.dto.AvailableTaxiDTO;
import com.internode_studios.spring_boot_application.availabletaxis.model.AvailableTaxi;
import com.internode_studios.spring_boot_application.availabletaxis.repository.AvailableTaxiRepository;
import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.city.repository.CityRepository;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailableTaxiService {

    @Autowired
    private AvailableTaxiRepository availableTaxiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    // Create a new AvailableTaxi
    public AvailableTaxi createAvailableTaxi(AvailableTaxi availableTaxi) throws IllegalArgumentException {

        // Validate driverId is a driver
        Optional<User> driver = userRepository.findByIdAndRole(availableTaxi.getDriverId(), "driver");
        if (driver.isEmpty()) {
            throw new IllegalArgumentException("Invalid driverId: User must have the role 'driver'.");
        }

        // Validate cityId
        Optional<City> city = cityRepository.findById(availableTaxi.getCityId());
        if (city.isEmpty()) {
            throw new IllegalArgumentException("Invalid cityId: City must be present in the 'city' table.");
        }

        return availableTaxiRepository.save(availableTaxi);
    }

    // Get all AvailableTaxis
    public List<AvailableTaxi> getAllAvailableTaxis() {
        return availableTaxiRepository.findAll();
    }

    // Get AvailableTaxi by ID
    public Optional<AvailableTaxi> getAvailableTaxiById(Long id) {
        return availableTaxiRepository.findById(id);
    }

    // Update rideStatus AvailableTaxi by ID
    public AvailableTaxi updateRideStatusAvailableTaxi(Long id, AvailableTaxi availableTaxiDetails) throws IllegalArgumentException {
        Optional<AvailableTaxi> optionalAvailableTaxi = availableTaxiRepository.findById(id);
        if (optionalAvailableTaxi.isPresent()) {
            AvailableTaxi availableTaxi = optionalAvailableTaxi.get();
            availableTaxi.setRideStatus(availableTaxiDetails.getRideStatus());

            return availableTaxiRepository.save(availableTaxi);
        }
        return null;
    }

    // Update AvailableTaxi by ID
    public AvailableTaxi updateAvailableTaxi(Long id, AvailableTaxi availableTaxiDetails) throws IllegalArgumentException {
        Optional<AvailableTaxi> optionalAvailableTaxi = availableTaxiRepository.findById(id);
        if (optionalAvailableTaxi.isPresent()) {
            AvailableTaxi availableTaxi = optionalAvailableTaxi.get();
            availableTaxi.setDriverId(availableTaxiDetails.getDriverId());
            availableTaxi.setCityId(availableTaxiDetails.getCityId());
            availableTaxi.setGender(availableTaxiDetails.getGender());
            availableTaxi.setRideStatus(availableTaxiDetails.getRideStatus());

            return availableTaxiRepository.save(availableTaxi);
        }
        return null;
    }

    // Delete AvailableTaxi by ID
    public void deleteAvailableTaxi(Long id) {
        availableTaxiRepository.deleteById(id);
    }

    // Get all AvailableTaxis with rideStatus 'online'
    public List<AvailableTaxi> getAllAvailableTaxisByStatusOnlineGender(String gender, String rideStatus, Long cityId) {
        return availableTaxiRepository.findByRideStatusAndGenderAndCityId(rideStatus, gender, cityId);
    }

    // Method to get all available taxis filtered by gender and city
    public List<AvailableTaxi> getAllAvailableTaxisByGenderAndCity(String gender, Long cityId) {
        // Assuming you have a repository method that performs the query
        return availableTaxiRepository.findByGenderAndCityId(gender, cityId);
    }

    // Get AvailableTaxi by ID, gender, rideStatus, and cityId
//    public Optional<AvailableTaxi> getAvailableTaxiByIdAndStatus(Long id, String gender, String rideStatus, Long cityId) {
//        return availableTaxiRepository.findByIdAndRideStatusAndGenderAndCityId(id, rideStatus, gender, cityId);
//    }

    // Get AvailableTaxi by ID, gender, rideStatus, and cityId, including driver details
    public Optional<AvailableTaxiDTO> getAvailableTaxiByIdAndStatusWithDriver(Long id, String gender, String rideStatus, Long cityId) {
        Optional<AvailableTaxi> availableTaxiOpt = availableTaxiRepository.findByIdAndRideStatusAndGenderAndCityId(id, rideStatus, gender, cityId);

        if (availableTaxiOpt.isPresent()) {
            AvailableTaxi availableTaxi = availableTaxiOpt.get();
            Optional<User> driverOpt = userRepository.findById(availableTaxi.getDriverId());
            Optional<City> cityOpt = cityRepository.findById(availableTaxi.getCityId());
            if (driverOpt.isPresent() && cityOpt.isPresent()) {
                User driver = driverOpt.get();
                City city = cityOpt.get();
                return Optional.of(new AvailableTaxiDTO(availableTaxi, driver, city));
            }
        }
        return Optional.empty();
    }

    // Get AvailableTaxi by ID, gender, and cityId, including driver details
    public Optional<AvailableTaxiDTO> findByIdAndGenderAndCityId(Long id, String gender, Long cityId) {
        Optional<AvailableTaxi> availableTaxiOpt = availableTaxiRepository.findByIdAndGenderAndCityId(id, gender, cityId);

        if (availableTaxiOpt.isPresent()) {
            AvailableTaxi availableTaxi = availableTaxiOpt.get();
            Optional<User> driverOpt = userRepository.findById(availableTaxi.getDriverId());
            Optional<City> cityOpt = cityRepository.findById(availableTaxi.getCityId());
            if (driverOpt.isPresent() && cityOpt.isPresent()) {
                User driver = driverOpt.get();
                City city = cityOpt.get();
                return Optional.of(new AvailableTaxiDTO(availableTaxi, driver, city));
            }
        }
        return Optional.empty();
    }
}
