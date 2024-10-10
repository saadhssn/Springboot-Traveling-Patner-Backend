package com.internode_studios.spring_boot_application.Jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64;
import java.util.function.Function;

@Service
public class JwtUtil {

    // Static secret key (ensure this is 256 bits or greater and base64 encoded)
    private final SecretKey SECRET_KEY;

    // Constructor to decode Base64 encoded secret key
    public JwtUtil() {
        String base64SecretKey = "OFR25eDzvg6rti2KaJhEmtMdzpr93aGRmINX3+M0jl4";
        byte[] decodedKey = Base64.getDecoder().decode(base64SecretKey);
        this.SECRET_KEY = Keys.hmacShaKeyFor(decodedKey);
    }

    // Generate token with id, role, and mobileNumber
    public String generateToken(Long id, String role, String mobileNumber) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("role", role);  // Add role to the claims
        claims.put("mobileNumber", mobileNumber);
        return createToken(claims, mobileNumber);
    }

    // Create JWT token with custom claims
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Validate token
    public Boolean validateToken(String token, String mobileNumber) {
        final String username = extractMobileNumber(token);
        return (username.equals(mobileNumber) && !isTokenExpired(token));
    }

    // Extract mobile number from the token
    public String extractMobileNumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract user ID from token
    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("id", Long.class);  // Get user ID
    }

    // Extract role from token
    public String extractRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);  // Extract role
    }

    // Other utility methods
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
