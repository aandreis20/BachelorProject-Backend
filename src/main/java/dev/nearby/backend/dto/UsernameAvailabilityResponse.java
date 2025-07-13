package dev.nearby.backend.dto;

public class UsernameAvailabilityResponse {
    private boolean available;

    public UsernameAvailabilityResponse() {}
    public UsernameAvailabilityResponse(boolean available) {
        this.available = available;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
} 