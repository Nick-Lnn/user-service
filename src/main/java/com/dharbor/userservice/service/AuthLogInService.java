package com.dharbor.userservice.service;

import com.dharbor.userservice.config.jwt.JwtTokenConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas
 */
@Service
public class AuthLogInService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenConfig jwtTokenConfig;

    public AuthLogInService(AuthenticationManager authenticationManager, JwtTokenConfig jwtTokenConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenConfig = jwtTokenConfig;
    }

    public String login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                return jwtTokenConfig.generateToken(username);
            } else {
                throw new RuntimeException("Invalid username or password");
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }
}