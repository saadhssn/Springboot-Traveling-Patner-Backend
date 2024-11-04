package com.internode_studios.spring_boot_application.rideplan.service;

import com.internode_studios.spring_boot_application.availabletaxis.model.AvailableTaxi;
import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.city.repository.CityRepository;
import com.internode_studios.spring_boot_application.rideplan.dto.RidePlanDTO;
import com.internode_studios.spring_boot_application.rideplan.model.RidePlan;
import com.internode_studios.spring_boot_application.rideplan.repository.RidePlanRepository;
import com.internode_studios.spring_boot_application.ridetype.model.RideType;
import com.internode_studios.spring_boot_application.ridetype.repository.RideTypeRepository;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class RidePlanService {

    @Autowired
    private RidePlanRepository ridePlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RideTypeRepository rideTypeRepository;

    public RidePlan createRidePlan(RidePlan ridePlan) throws IllegalArgumentException {
        // Ensure that either userId or driverId is provided
        if (ridePlan.getUserId() == null && ridePlan.getDriverId() == null) {
            throw new IllegalArgumentException("Either userId or driverId must be provided.");
        }

        // Validate userId if provided
        if (ridePlan.getUserId() != null) {
            Optional<User> user = userRepository.findByIdAndRole(ridePlan.getUserId(), "partner");
            if (user.isEmpty()) {
                throw new IllegalArgumentException("Invalid userId: User must have the role 'partner'.");
            }
        }

        // Validate driverId if provided
        if (ridePlan.getDriverId() != null) {
            Optional<User> driverUser = userRepository.findByIdAndRole(ridePlan.getDriverId(), "driver");
            if (driverUser.isEmpty()) {
                throw new IllegalArgumentException("Invalid driverId: User must have the role 'driver'.");
            }
        }

        // Validate cityId
        Optional<City> city = cityRepository.findById(ridePlan.getCityId());
        if (city.isEmpty()) {
            throw new IllegalArgumentException("Invalid cityId: City must be present in the 'city' table.");
        }

        // Validate rideTypeId
        Optional<RideType> rideType = rideTypeRepository.findById(ridePlan.getRideTypeId());
        if (rideType.isEmpty()) {
            throw new IllegalArgumentException("Invalid rideTypeId: RideType must be present in the 'ridetype' table.");
        }

        // If validations pass, create the RidePlan
        return ridePlanRepository.save(ridePlan);
    }

    // Get all RidePlans
    public List<RidePlan> getAllRidePlans() {
        return ridePlanRepository.findAll();
    }

    // Get RidePlan by ID
    public Optional<RidePlan> getRidePlanById(Long id) {
        return ridePlanRepository.findById(id);
    }

    public Optional<RidePlanDTO> getRidePlanByIdWithDetails(Long id) {
        Optional<RidePlan> ridePlanOpt = ridePlanRepository.findById(id);

        if (ridePlanOpt.isPresent()) {
            RidePlan ridePlan = ridePlanOpt.get();

            // Fetching the partner (User) by userId
            Optional<User> partnerOpt = userRepository.findById(ridePlan.getUserId());

            // Fetching the driver (User) by driverId
            Optional<User> driverOpt = userRepository.findById(ridePlan.getDriverId());

            // Fetching the city by cityId
            Optional<City> cityOpt = cityRepository.findById(ridePlan.getCityId());

            // Fetching the ride type by rideTypeId
            Optional<RideType> rideTypeOpt = rideTypeRepository.findById(ridePlan.getRideTypeId());

            if (partnerOpt.isPresent() && driverOpt.isPresent() && cityOpt.isPresent() && rideTypeOpt.isPresent()) {
                User partner = partnerOpt.get();
                User driver = driverOpt.get();
                City city = cityOpt.get();
                RideType rideType = rideTypeOpt.get();

                return Optional.of(new RidePlanDTO(ridePlan, partner, driver, city, rideType));
            }
        }
        return Optional.empty();
    }

    // Update RidePlan by ID
    public RidePlan updateRidePlan(Long id, RidePlan ridePlanDetails) {
        RidePlan ridePlan = ridePlanRepository.findById(id).orElse(null);
        if (ridePlan != null) {
            // Update fields
            //ridePlan.setUserId(ridePlanDetails.getUserId());
//            ridePlan.setDriverId(ridePlanDetails.getDriverId());
//            ridePlan.setRole(ridePlanDetails.getRole());
//            ridePlan.setRideTypeId(ridePlanDetails.getRideTypeId());
//            ridePlan.setDate(ridePlanDetails.getDate());
//            ridePlan.setStuff(ridePlanDetails.getStuff());
//            ridePlan.setBagsCount(ridePlanDetails.getBagsCount());
//            ridePlan.setPickUpLocation(ridePlanDetails.getPickUpLocation());
//            ridePlan.setDropOffLocation(ridePlanDetails.getDropOffLocation());
//            ridePlan.setCityId(ridePlanDetails.getCityId());
//            ridePlan.setTourDays(ridePlanDetails.getTourDays());
//            ridePlan.setMeal(ridePlanDetails.isMeal());
//            ridePlan.setSeats(ridePlanDetails.getSeats());
//            ridePlan.setSeatsAvailable(ridePlanDetails.getSeatsAvailable());
//            ridePlan.setSeatsReserved(ridePlanDetails.getSeatsReserved());
//            ridePlan.setVisitingPoints(ridePlanDetails.getVisitingPoints());
//            ridePlan.setPets(ridePlanDetails.isPets());
//            ridePlan.setSmoke(ridePlanDetails.isSmoke());
//            ridePlan.setAc(ridePlanDetails.isAc());
            //ridePlan.setFare(ridePlanDetails.getFare());
            //ridePlan.setDriverQuotedFare(ridePlanDetails.getDriverQuotedFare());
            //ridePlan.setPartnerQuotedFare(ridePlanDetails.getPartnerQuotedFare());
            ridePlan.setRideStatus(ridePlanDetails.getRideStatus());
            //ridePlan.setNote(ridePlanDetails.getNote());

            return ridePlanRepository.save(ridePlan);
        }
        return null;
    }

    // Delete RidePlan by ID
    public void deleteRidePlan(Long id) {
        ridePlanRepository.deleteById(id);
    }

    // Get RidePlan by dropOffLocation, date, time, female
//    public List<RidePlan> getRidePlansByCriteria(String dropOffLocation, String date, String time, boolean female) {
//        return ridePlanRepository.findByDropOffLocationAndDateAndTimeAndFemale(dropOffLocation, date, time, female);
//    }

    public List<RidePlan> getRidePlansForDriver(String dropOffLocation, String date, String time, boolean female) {
        return ridePlanRepository.findByDropOffLocationAndDateAndTimeAndFemaleAndRole(dropOffLocation, date, time, female, "driver");
    }

    public List<RidePlan> getRidePlansForPartner(String dropOffLocation, String date, String time, boolean female) {
        return ridePlanRepository.findByDropOffLocationAndDateAndTimeAndFemaleAndRole(dropOffLocation, date, time, female, "partner");
    }

}
