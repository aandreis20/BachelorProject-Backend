package dev.nearby.backend.service;

import dev.nearby.backend.dto.CreateUserRequest;
import dev.nearby.backend.dto.CreateUserResponse;
import dev.nearby.backend.model.User;
import dev.nearby.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        user.setProfilePictureUrl(request.getProfilePictureUrl());
        user.setBluetoothEnabled(request.getBluetoothEnabled());
        // locationEnabled eliminat
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(savedUser);
    }
    
    public CreateUserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
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
} 