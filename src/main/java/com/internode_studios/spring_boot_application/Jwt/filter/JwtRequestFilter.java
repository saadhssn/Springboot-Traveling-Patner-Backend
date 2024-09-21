package com.internode_studios.spring_boot_application.Jwt.filter;

import com.internode_studios.spring_boot_application.Jwt.service.JwtUtil;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String mobileNumber = null;
        String jwt = null;

        // Check if the Authorization header contains a Bearer token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer: ")) {
            jwt = authorizationHeader.substring(7);  // Extract the token from the header
            mobileNumber = jwtUtil.extractMobileNumber(jwt);     // Extract the mobile from the token
        }

        // If the mobile number is not null and no authentication is set in the SecurityContext
        if (mobileNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User userDetails = userRepository.findByMobileNumber(mobileNumber).get(0);  // Assumes mobileNumber is unique

            if (jwtUtil.validateToken(jwt, mobileNumber)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, new ArrayList<>());  // Add roles/authorities if needed
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }


        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
