package com.internode_studios.spring_boot_application.user.repository;

import com.internode_studios.spring_boot_application.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByMobileNumber(String mobileNumber);
    User findByMobileNumberAndOtp(String mobileNumber, String otp);
}
