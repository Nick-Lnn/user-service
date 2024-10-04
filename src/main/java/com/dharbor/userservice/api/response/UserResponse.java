package com.dharbor.userservice.api.response;

import lombok.Data;

/**
 * @author Nicolas
 */
@Data
public class UserResponse {

    private String username;

    private String accountId;

    private String firstName;

    private String lastName;
}
