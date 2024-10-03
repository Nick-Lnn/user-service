package com.dharbor.userservice.api.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author Nicolas
 */
public class UserRequest {

    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    private String username;

    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    private String password;

    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    private String firstName;

    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    private String lastName;
}
