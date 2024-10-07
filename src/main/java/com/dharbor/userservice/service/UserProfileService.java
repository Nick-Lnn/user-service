package com.dharbor.userservice.service;

import com.dharbor.userservice.api.response.UserResponse;
import com.dharbor.userservice.model.domain.User;
import com.dharbor.userservice.model.mapper.UserMapper;
import com.dharbor.userservice.model.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas
 */
@Service
public class UserProfileService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserProfileService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserResponse(user);
    }
}
