package com.internode_studios.spring_boot_application.riderequest.service;

import com.internode_studios.spring_boot_application.city.model.City;
import com.internode_studios.spring_boot_application.city.repository.CityRepository;
import com.internode_studios.spring_boot_application.rideplan.model.RidePlan;
import com.internode_studios.spring_boot_application.rideplan.repository.RidePlanRepository;
import com.internode_studios.spring_boot_application.ridetype.model.RideType;
import com.internode_studios.spring_boot_application.ridetype.repository.RideTypeRepository;
import com.internode_studios.spring_boot_application.riderequest.model.RideRequest;
import com.internode_studios.spring_boot_application.riderequest.repository.RideRequestRepository;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository rideRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideTypeRepository rideTypeRepository;

    @Autowired
    private RidePlanRepository ridePlanRepository;

    @Autowired
    private CityRepository cityRepository;

    // Create a new RideRequest
    public RideRequest createRideRequest(RideRequest rideRequest) throws IllegalArgumentException {
        // Validate driverId is a driver
        Optional<User> driver = userRepository.findByIdAndRole(rideRequest.getDriverId(), "driver");
        if (driver.isEmpty()) {
            throw new IllegalArgumentException("Invalid driverId: User must have the role 'driver'.");
        }

        // Validate rideTypeId exists
        Optional<RideType> rideType = rideTypeRepository.findById(rideRequest.getRideTypeId());
        if (rideType.isEmpty()) {
            throw new IllegalArgumentException("Invalid rideTypeId: RideType must be present in the 'ride_type' table.");
        }

        // Validate ridePlanId exists
        Optional<RidePlan> ridePlan = ridePlanRepository.findById(rideRequest.getRidePlanId());
        if (ridePlan.isEmpty()) {
            throw new IllegalArgumentException("Invalid ridePlanId: RidePlan must be present in the 'ride_plan' table.");
        }

        // Optionally, you can add more validations here (e.g., rideStatus values)

        // If all validations pass, save the RideRequest
        return rideRequestRepository.save(rideRequest);
    }

    // Get all RideRequests
    public List<RideRequest> getAllRideRequests() {
        return rideRequestRepository.findAll();
    }

    // Get RideRequest by ID
    public Optional<RideRequest> getRideRequestById(Long id) {
        return rideRequestRepository.findById(id);
    }

    // Update RideRequest by ID
    public RideRequest updateRideRequest(Long id, RideRequest rideRequestDetails) throws IllegalArgumentException {
        Optional<RideRequest> optionalRideRequest = rideRequestRepository.findById(id);
        if (optionalRideRequest.isPresent()) {
            RideRequest rideRequest = optionalRideRequest.get();

            // Update fields as necessary
            rideRequest.setDriverId(rideRequestDetails.getDriverId());
            rideRequest.setRideTypeId(rideRequestDetails.getRideTypeId());
            rideRequest.setRidePlanId(rideRequestDetails.getRidePlanId());
            rideRequest.setRideStatus(rideRequestDetails.getRideStatus());

            // Validate updated fields if necessary
            // For example, if rideTypeId is updated, validate it
            Optional<User> driver = userRepository.findByIdAndRole(rideRequest.getDriverId(), "driver");
            if (driver.isEmpty()) {
                throw new IllegalArgumentException("Invalid driverId: User must have the role 'driver'.");
            }

            Optional<RideType> rideType = rideTypeRepository.findById(rideRequest.getRideTypeId());
            if (rideType.isEmpty()) {
                throw new IllegalArgumentException("Invalid rideTypeId: RideType must be present in the 'ride_type' table.");
            }

            Optional<RidePlan> ridePlan = ridePlanRepository.findById(rideRequest.getRidePlanId());
            if (ridePlan.isEmpty()) {
                throw new IllegalArgumentException("Invalid ridePlanId: RidePlan must be present in the 'ride_plan' table.");
            }

            return rideRequestRepository.save(rideRequest);
        }
        return null;
    }

    // Delete RideRequest by ID
    public void deleteRideRequest(Long id) {
        rideRequestRepository.deleteById(id);
    }
}