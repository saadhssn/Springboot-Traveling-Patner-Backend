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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    // CORS Configuration Bean
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://192.168.18.46:8080")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    // Security Filter Chain Bean
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors() // Enable CORS
                .and()
                .csrf().disable() // Disable CSRF for APIs
                .authorizeHttpRequests()
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
                        "/api/auth/verify-otp",
                        "http://localhost:3000",
                        "http://192.168.18.19:8080"
                ).permitAll() // Permit specified endpoints
                .requestMatchers("/api/roles/**").hasAuthority("admin") // Admin role for roles API
                .requestMatchers("/api/cities/**").hasAuthority("admin")
                .requestMatchers("/api/rideTypes/**").hasAuthority("admin")
                .anyRequest().authenticated() // All other requests require authentication
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Stateless session

        // Add JWT Filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}