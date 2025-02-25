package com.internode_studios.spring_boot_application.config;

import com.internode_studios.spring_boot_application.Jwt.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // Authentication Manager Bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Security Filter Chain Bean
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of(
                            "http://localhost:3000",  // ✅ Web Frontend
                            "http://localhost:8081",  // ✅ Metro Bundler (React Native)
                            "http://10.0.2.2:8081",   // ✅ Android Emulator (React Native Metro Bundler)
                            "http://192.168.1.100:8081" // ✅ Physical Device (Replace with your IP)
                    ));
                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    corsConfig.setAllowCredentials(true);
                    return corsConfig;
                }))
                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for APIs
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ Stateless sessions
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/v3/api-docs/**",
                                "/webjars/**",
                                "/api/auth/admin/login",
                                "/api/auth/user/login",
                                "/api/auth/user/roleassign",
                                "/api/auth/setpassword",
                                "/api/auth/verify-password",
                                "/api/auth/verify-otp"
                        ).permitAll() // ✅ Open public APIs
                        .requestMatchers("/api/roles/**").hasAuthority("admin")
                        .requestMatchers("/api/cities/**").hasAuthority("admin")
                        .requestMatchers("/api/rideTypes/**").hasAuthority("admin")
                        .anyRequest().authenticated() // ✅ Protect other APIs
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // ✅ Adding JWT filter correctly

        return http.build();
    }
}