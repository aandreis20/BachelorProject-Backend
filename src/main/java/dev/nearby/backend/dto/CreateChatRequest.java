package dev.nearby.backend.dto;

import java.util.List;

public class CreateChatRequest {
    private List<String> participantIds;
    private String chatName;

    public CreateChatRequest() {}

    public List<String> getParticipantIds() { return participantIds; }
    public void setParticipantIds(List<String> participantIds) { this.participantIds = participantIds; }
    public String getChatName() { return chatName; }
    public void setChatName(String chatName) { this.chatName = chatName; }
} 