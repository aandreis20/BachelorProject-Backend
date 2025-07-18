package dev.nearby.backend.dto;

import dev.nearby.backend.model.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CreateUserResponse {
    
    private UUID accountId;
    private Integer age;
    private User.Gender gender;
    private String bio;
    private List<String> interests;
    private String profilePictureUrl;
    private Boolean bluetoothEnabled;
    private String username;
    private String fullName;
    private LocalDateTime createdAt;
    
    // Constructors
    public CreateUserResponse() {}
    
    public CreateUserResponse(User user) {
        this.accountId = user.getAccountId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.bio = user.getBio();
        this.interests = user.getInterests();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.bluetoothEnabled = user.getBluetoothEnabled();
        this.createdAt = user.getCreatedAt();
    }
    
    // Getters and Setters
    public UUID getAccountId() {
        return accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
    
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
    
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
    
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
    
    public Boolean getBluetoothEnabled() {
        return bluetoothEnabled;
    }
    
    public void setBluetoothEnabled(Boolean bluetoothEnabled) {
        this.bluetoothEnabled = bluetoothEnabled;
    }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 