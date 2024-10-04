package com.dharbor.userservice.controller;

import com.dharbor.userservice.api.request.AuthRequest;
import com.dharbor.userservice.api.response.AuthResponse;
import com.dharbor.userservice.service.AuthLogInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nicolas
 */
@RestController
@RequestMapping("/api/user-service/auth")
public class AuthLogInController {

    private final AuthLogInService authLogInService;

    public AuthLogInController(AuthLogInService authLogInService) {
        this.authLogInService = authLogInService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authLogInService.login(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}