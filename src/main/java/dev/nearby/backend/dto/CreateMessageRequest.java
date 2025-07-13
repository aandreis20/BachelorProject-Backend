package dev.nearby.backend.dto;

import java.time.LocalDateTime;

public class CreateMessageRequest {
    private String text;
    private String senderId;
    private LocalDateTime timestamp;

    public CreateMessageRequest() {}

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
} 