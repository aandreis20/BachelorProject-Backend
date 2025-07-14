package dev.nearby.backend.service;

import dev.nearby.backend.dto.CreateUserRequest;
import dev.nearby.backend.dto.CreateUserResponse;
import dev.nearby.backend.dto.UpdateUserRequest;
import dev.nearby.backend.model.User;
import dev.nearby.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public CreateUserResponse createUser(CreateUserRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists: " + request.getUsername());
        }
        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setBio(request.getBio());
        user.setInterests(request.getInterests());
        // user.setProfilePictureUrl(request.getProfilePictureUrl()); // eliminat, nu mai vine din request
        user.setBluetoothEnabled(request.getBluetoothEnabled());
        // locationEnabled eliminat
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(savedUser);
    }
    
    public CreateUserResponse getUserByAccountId(UUID accountId) {
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("User not found with accountId: " + accountId));
        return new CreateUserResponse(user);
    }
    
    public CreateUserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return new CreateUserResponse(user);
    }

    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    public CreateUserResponse updateUser(UUID accountId, UpdateUserRequest request) {
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("User not found with accountId: " + accountId));
        if (request.getUsername() != null) user.setUsername(request.getUsername());
        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getAge() != null) user.setAge(request.getAge());
        if (request.getGender() != null) user.setGender(request.getGender());
        if (request.getBio() != null) user.setBio(request.getBio());
        if (request.getInterests() != null) user.setInterests(request.getInterests());
        if (request.getProfilePictureUrl() != null) user.setProfilePictureUrl(request.getProfilePictureUrl());
        if (request.getBluetoothEnabled() != null) user.setBluetoothEnabled(request.getBluetoothEnabled());
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(savedUser);
    }
} 