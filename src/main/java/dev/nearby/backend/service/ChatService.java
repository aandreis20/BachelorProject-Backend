package dev.nearby.backend.service;

import dev.nearby.backend.dto.ChatResponse;
import dev.nearby.backend.dto.CreateChatRequest;
import dev.nearby.backend.dto.MessageResponse;
import dev.nearby.backend.model.Chat;
import dev.nearby.backend.model.Message;
import dev.nearby.backend.repository.ChatRepository;
import dev.nearby.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    public ChatResponse createChat(CreateChatRequest request) {
        Chat chat = new Chat();
        chat.setId(UUID.randomUUID().toString());
        chat.setParticipantIds(request.getParticipantIds());
        chat.setChatName(request.getChatName());
        chat.setCreatedAt(LocalDateTime.now());
        chat.setLastMessageAt(null);
        chatRepository.save(chat);
        return toChatResponse(chat, null);
    }

    public List<ChatResponse> getChatsForUser(String userId) {
        List<Chat> chats = chatRepository.findByParticipantIdsContaining(userId);
        return chats.stream().map(chat -> {
            Message lastMsg = getLastMessage(chat.getId());
            return toChatResponse(chat, lastMsg);
        }).collect(Collectors.toList());
    }

    public List<ChatResponse> searchChatsForUser(String userId, String query) {
        List<Chat> chats = chatRepository.findByParticipantIdsContainingAndChatNameContainingIgnoreCase(userId, query);
        return chats.stream().map(chat -> {
            Message lastMsg = getLastMessage(chat.getId());
            return toChatResponse(chat, lastMsg);
        }).collect(Collectors.toList());
    }

    private Message getLastMessage(String chatId) {
        List<Message> messages = messageRepository.findByChatIdOrderByTimestampDesc(chatId);
        return messages.isEmpty() ? null : messages.get(0);
    }

    private ChatResponse toChatResponse(Chat chat, Message lastMsg) {
        ChatResponse resp = new ChatResponse();
        resp.setId(chat.getId());
        resp.setParticipantIds(chat.getParticipantIds());
        resp.setChatName(chat.getChatName());
        resp.setCreatedAt(chat.getCreatedAt());
        resp.setLastMessageAt(chat.getLastMessageAt());
        if (lastMsg != null) {
            MessageResponse msgResp = new MessageResponse();
            msgResp.setId(lastMsg.getId());
            msgResp.setChatId(lastMsg.getChatId());
            msgResp.setText(lastMsg.getText());
            msgResp.setSenderId(lastMsg.getSenderId());
            msgResp.setTimestamp(lastMsg.getTimestamp());
            msgResp.setDeleted(lastMsg.isDeleted());
            resp.setLastMessage(msgResp);
        } else {
            resp.setLastMessage(null);
        }
        return resp;
    }
} 