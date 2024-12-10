package com.internode_studios.spring_boot_application.user.repository;

import com.internode_studios.spring_boot_application.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByMobileNumber(String mobileNumber);
    User findByMobileNumberAndOtp(String mobileNumber, String otp);
    List<User> findByRole(String role);  // Adjusted method for String role

    List<User> findByMobileNumberAndRole(String tokenMobileNumber, String admin);
    Optional<User> findByIdAndRole(Long id, String role);

    Optional<User> findByCnicNumber(String cnicNumber);
    Optional<User> findByEmail(String email);
}
