package dev.nearby.backend.dto;

import dev.nearby.backend.model.User;
import java.util.List;

public class UpdateUserRequest {
    private String username;
    private String fullName;
    private Integer age;
    private User.Gender gender;
    private String bio;
    private List<String> interests;
    private String profilePictureUrl;
    private Boolean bluetoothEnabled;

    public UpdateUserRequest() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public User.Gender getGender() { return gender; }
    public void setGender(User.Gender gender) { this.gender = gender; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
    public Boolean getBluetoothEnabled() { return bluetoothEnabled; }
    public void setBluetoothEnabled(Boolean bluetoothEnabled) { this.bluetoothEnabled = bluetoothEnabled; }
} 