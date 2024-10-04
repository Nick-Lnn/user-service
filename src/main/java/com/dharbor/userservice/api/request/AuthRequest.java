package com.dharbor.userservice.api.request;

import lombok.Data;
/**
 * @author Nicolas
 */
@Data
public class AuthRequest {

    private String username;

    private String password;
}
