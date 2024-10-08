package com.dharbor.userservice.service;

import com.dharbor.userservice.api.request.UserRequest;
import com.dharbor.userservice.api.response.UserResponse;
import com.dharbor.userservice.model.domain.User;
import com.dharbor.userservice.model.mapper.UserMapper;
import com.dharbor.userservice.model.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas
 */
@Service
public class UserRegistryService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserRegistryService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = userMapper.userRequestToUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setAccountId(generateUniqueAccountId());
        user.setIsDeleted(false);

        User savedUser = userRepository.save(user);
        return userMapper.userToUserResponse(savedUser);
    }

    private Long generateUniqueAccountId() {
        return System.currentTimeMillis();
    }
}