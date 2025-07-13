package dev.nearby.backend.dto;

import dev.nearby.backend.model.User;
import jakarta.validation.constraints.*;
import java.util.List;

public class CreateUserRequest {
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters")
    private String fullName;
    
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "User must be at least 18 years old")
    @Max(value = 100, message = "Age cannot exceed 100")
    private Integer age;
    
    @NotNull(message = "Gender is required")
    private User.Gender gender;
    
    @NotBlank(message = "Bio is required")
    @Size(max = 500, message = "Bio cannot exceed 500 characters")
    private String bio;
    
    @NotEmpty(message = "At least one interest is required")
    @Size(max = 10, message = "Cannot have more than 10 interests")
    private List<String> interests;
    
    private Boolean bluetoothEnabled = false;
    
    // Constructors
    public CreateUserRequest() {}
    
    public CreateUserRequest(String username, String fullName, Integer age, User.Gender gender, String bio, List<String> interests) {
        this.username = username;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.bio = bio;
        this.interests = interests;
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public User.Gender getGender() {
        return gender;
    }
    
    public void setGender(User.Gender gender) {
        this.gender = gender;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public List<String> getInterests() {
        return interests;
    }
    
    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
    
    public Boolean getBluetoothEnabled() {
        return bluetoothEnabled;
    }
    
    public void setBluetoothEnabled(Boolean bluetoothEnabled) {
        this.bluetoothEnabled = bluetoothEnabled;
    }
} 