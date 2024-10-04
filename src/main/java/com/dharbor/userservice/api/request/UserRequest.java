package com.dharbor.userservice.api.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Nicolas
 */
@Data
public class UserRequest {

    @NotEmpty(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be null")
    private String password;

    @NotEmpty(message = "First Name cannot be empty")
    @NotNull(message = "First Name cannot be null")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    @NotNull(message = "Last Name cannot be null")
    private String lastName;
}
