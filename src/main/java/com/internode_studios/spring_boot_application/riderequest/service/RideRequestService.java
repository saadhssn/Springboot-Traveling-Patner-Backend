package com.internode_studios.spring_boot_application.riderequest.service;

import com.internode_studios.spring_boot_application.ridetype.model.RideType;
import com.internode_studios.spring_boot_application.ridetype.repository.RideTypeRepository;
import com.internode_studios.spring_boot_application.rideplan.model.RidePlan;
import com.internode_studios.spring_boot_application.rideplan.repository.RidePlanRepository;
import com.internode_studios.spring_boot_application.riderequest.model.RideRequest;
import com.internode_studios.spring_boot_application.riderequest.repository.RideRequestRepository;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Update RideRequest by ID
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
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


    public RideRequest createRideRequest(RideRequest rideRequest) throws IllegalArgumentException {
        // Validate driverId is a driver
        if (rideRequest.getDriverId() != null) {
            Optional<User> driver = userRepository.findByIdAndRole(rideRequest.getDriverId(), "driver");
            if (driver.isEmpty()) {
                throw new IllegalArgumentException("Invalid driverId: User must have the role 'driver'.");
            }
        }

        // Validate partnerId is a partner if present
        if (rideRequest.getPartnerId() != null) {
            Optional<User> partner = userRepository.findByIdAndRole(rideRequest.getPartnerId(), "partner");
            if (partner.isEmpty()) {
                throw new IllegalArgumentException("Invalid partnerId: User must have the role 'partner'.");
            }
        }

        // Validate rideTypeId exists
        Optional<RideType> rideType = rideTypeRepository.findById(rideRequest.getRideTypeId());
        if (rideType.isEmpty()) {
            throw new IllegalArgumentException("Invalid rideTypeId: RideType must be present in the 'ride_type' table.");
        }

        // Validate ridePlanId exists and fetch its details
        Optional<RidePlan> ridePlanOpt = ridePlanRepository.findById(rideRequest.getRidePlanId());
        if (ridePlanOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid ridePlanId: RidePlan must be present in the 'ride_plan' table.");
        }

        RidePlan ridePlan = ridePlanOpt.get();

        // Only proceed with seat reservation if the rideStatus is "confirmed"
        if ("confirmed".equalsIgnoreCase(rideRequest.getRideStatus())) {
            // Check if enough seats are available
            if (rideRequest.getSeatsReserved() > ridePlan.getSeatsAvailable()) {
                throw new IllegalArgumentException("Not enough seats available. Seats reserved: "
                        + rideRequest.getSeatsReserved() + ", Seats available: " + ridePlan.getSeatsAvailable());
            }

            // Update the ride plan with the reserved seats
            ridePlan.setSeatsReserved(ridePlan.getSeatsReserved() + rideRequest.getSeatsReserved());
            ridePlan.setSeatsAvailable(ridePlan.getSeatsAvailable() - rideRequest.getSeatsReserved());
            ridePlan.setFare(ridePlan.getFare());
            ridePlanRepository.save(ridePlan);
        }

        // Set pickUpLocation and dropOffLocation from the RidePlan
        rideRequest.setPickUpLocation(ridePlan.getPickUpLocation());
        rideRequest.setDropOffLocation(ridePlan.getDropOffLocation());
        rideRequest.setDate(ridePlan.getDate());
        rideRequest.setTime(ridePlan.getTime());

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

    public List<RideRequest> getRideRequestsByRidePlanIdAndDate(Long ridePlanId, String date) {
        return rideRequestRepository.findByRidePlanIdAndDate(ridePlanId, date);
    }

    @Transactional
    public RideRequest updateRideRequest(Long id, RideRequest rideRequestDetails) throws IllegalArgumentException {
        Optional<RideRequest> optionalRideRequest = rideRequestRepository.findById(id);
        if (optionalRideRequest.isPresent()) {
            RideRequest rideRequest = optionalRideRequest.get();

            // Update fields as necessary
            rideRequest.setRideStatus(rideRequestDetails.getRideStatus());

            // Validate driverId
            if (rideRequest.getDriverId() != null) {
                Optional<User> driver = userRepository.findByIdAndRole(rideRequest.getDriverId(), "driver");
                if (driver.isEmpty()) {
                    throw new IllegalArgumentException("Invalid driverId: User must have the role 'driver'.");
                }
            }

            // Validate partnerId if present
            if (rideRequest.getPartnerId() != null) {
                Optional<User> partner = userRepository.findByIdAndRole(rideRequest.getPartnerId(), "partner");
                if (partner.isEmpty()) {
                    throw new IllegalArgumentException("Invalid partnerId: User must have the role 'partner'.");
                }
            }

            // Validate rideTypeId
            Optional<RideType> rideType = rideTypeRepository.findById(rideRequest.getRideTypeId());
            if (rideType.isEmpty()) {
                throw new IllegalArgumentException("Invalid rideTypeId: RideType must be present in the 'ride_type' table.");
            }

            // Validate ridePlanId
            Optional<RidePlan> ridePlanOpt = ridePlanRepository.findById(rideRequest.getRidePlanId());
            if (ridePlanOpt.isEmpty()) {
                throw new IllegalArgumentException("Invalid ridePlanId: RidePlan must be present in the 'ride_plan' table.");
            }

            RidePlan ridePlan = ridePlanOpt.get();

            // Only proceed with seat reservation if the rideStatus is "confirmed"
            if ("confirmed".equalsIgnoreCase(rideRequest.getRideStatus())) {
                // Check if enough seats are available
                if (rideRequest.getSeatsReserved() > ridePlan.getSeatsAvailable()) {
                    throw new IllegalArgumentException("Not enough seats available. Seats reserved: "
                            + rideRequest.getSeatsReserved() + ", Seats available: " + ridePlan.getSeatsAvailable());
                }

                // Update the ride plan with the reserved seats
                ridePlan.setSeatsReserved(ridePlan.getSeatsReserved() + rideRequest.getSeatsReserved());
                ridePlan.setSeatsAvailable(ridePlan.getSeatsAvailable() - rideRequest.getSeatsReserved());

                // Explicitly set fare to trigger an update
                //ridePlan.setRideStatus(rideRequest.getRideStatus());

                // Explicitly set fare to trigger an update
                //ridePlan.setFare(rideRequest.getFare());  // Set a specific value if needed, e.g., "150.00 USD"

                // Save the updated RidePlan
                ridePlanRepository.save(ridePlan);
            }

            // Save and return updated RideRequest
            return rideRequestRepository.save(rideRequest);
        }
        return null;
    }


    // Update Fare by RideRequest ID
    public RideRequest updateFare(Long id, String newFare) throws IllegalArgumentException {
        Optional<RideRequest> optionalRideRequest = rideRequestRepository.findById(id);
        if (optionalRideRequest.isPresent()) {
            RideRequest rideRequest = optionalRideRequest.get();

            // Update fare
            rideRequest.setFare(newFare);

            // Save and return updated RideRequest
            return rideRequestRepository.save(rideRequest);
        } else {
            throw new IllegalArgumentException("RideRequest not found with ID: " + id);
        }
    }

    // Delete RideRequest by ID
    public void deleteRideRequest(Long id) {
        rideRequestRepository.deleteById(id);
    }

    //Partner
    public List<RideRequest> getConfirmedRideRequestsForPartner(Long partnerId) {
        return rideRequestRepository.findByPartnerIdAndRideStatus(partnerId, "confirmed");
    }

    //Driver
    public List<RideRequest> getConfirmedRideRequestsForDriver(Long driverId) {
        return rideRequestRepository.findByDriverIdAndRideStatus(driverId, "confirmed");
    }
}
