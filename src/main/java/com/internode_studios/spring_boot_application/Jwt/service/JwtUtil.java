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
        String base64SecretKey = "OFR25eDzvg6rti2KaJhEmtMdzpr93aGRmINX3+M0jl4"; // 32-byte (256-bit) Base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(base64SecretKey);
        this.SECRET_KEY = Keys.hmacShaKeyFor(decodedKey);
    }

    // Generate token
    public String generateToken(String mobileNumber) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, mobileNumber);
    }

    // Create JWT token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .signWith(SECRET_KEY) // Use the static key for signing
                .compact();
    }

    // Validate token
    public Boolean validateToken(String token, String mobileNumber) {
        final String username = extractUsername(token);
        return (username.equals(mobileNumber) && !isTokenExpired(token));
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract claim
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

    // Extract expiration date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract mobile number from the token (the subject in this case)
    public String extractMobileNumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
