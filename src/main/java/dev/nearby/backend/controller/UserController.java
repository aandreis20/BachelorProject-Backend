package dev.nearby.backend.controller;

import dev.nearby.backend.dto.CreateUserRequest;
import dev.nearby.backend.dto.CreateUserResponse;
import dev.nearby.backend.dto.UsernameAvailabilityResponse;
import dev.nearby.backend.dto.UpdateUserRequest;
import dev.nearby.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Pentru dezvoltare - în producție specifică domeniile exacte
public class UserController {
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        try {
            CreateUserResponse response = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to create user: " + e.getMessage());
        }
    }
    
    @GetMapping("/{accountId}")
    public ResponseEntity<CreateUserResponse> getUserByAccountId(@PathVariable UUID accountId) {
        try {
            CreateUserResponse response = userService.getUserByAccountId(accountId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new RuntimeException("User not found: " + e.getMessage());
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<CreateUserResponse> getUserByUsername(@PathVariable String username) {
        try {
            CreateUserResponse response = userService.getUserByUsername(username);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new RuntimeException("User not found: " + e.getMessage());
        }
    }

    @GetMapping("/check-username")
    public ResponseEntity<UsernameAvailabilityResponse> checkUsernameAvailability(@RequestParam("username") String username) {
        boolean available = userService.isUsernameAvailable(username);
        return ResponseEntity.ok(new UsernameAvailabilityResponse(available));
    }

    @PatchMapping("/{accountId}")
    public ResponseEntity<CreateUserResponse> updateUser(@PathVariable UUID accountId, @RequestBody UpdateUserRequest request) {
        try {
            CreateUserResponse response = userService.updateUser(accountId, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }
    }
    
    // Endpoint pentru health check
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Nearby Backend is running!");
    }
} 