package com.dharbor.userservice.api.response;

import lombok.Data;

/**
 * @author Nicolas
 */
@Data
public class UserResponse {

    private String username;

    private Long accountId;

    private String firstName;

    private String lastName;
}
