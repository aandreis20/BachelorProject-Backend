package dev.nearby.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "text")
    private String text;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    public Message() {}

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getChatId() { return chatId; }
    public void setChatId(String chatId) { this.chatId = chatId; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }
} 