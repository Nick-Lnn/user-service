package com.dharbor.userservice.controller;

import com.dharbor.userservice.api.response.UserResponse;
import com.dharbor.userservice.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nicolas
 */
@RestController
@RequestMapping(value = "/api/user-service")  // Adjust this path as needed
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUserWithToken(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userProfileService.getUserByUsername(userDetails.getUsername()));
    }
}
