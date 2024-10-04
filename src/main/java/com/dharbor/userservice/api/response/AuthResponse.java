package com.dharbor.userservice.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Nicolas
 */
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
