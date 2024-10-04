package com.dharbor.userservice.controller;

import com.dharbor.userservice.api.request.UserRequest;
import com.dharbor.userservice.api.response.UserResponse;
import com.dharbor.userservice.service.UserRegistryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nicolas
 */
@Validated
@RestController
@RequestMapping("/api/user-service")
public class UserRegistryController {

    private final UserRegistryService userRegistryService;

    public UserRegistryController(UserRegistryService userRegistryService) {
        this.userRegistryService = userRegistryService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userRegistryService.registerUser(userRequest));
    }
}
