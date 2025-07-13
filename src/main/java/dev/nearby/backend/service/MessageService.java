package dev.nearby.backend.service;

import dev.nearby.backend.dto.CreateMessageRequest;
import dev.nearby.backend.dto.MessageResponse;
import dev.nearby.backend.model.Message;
import dev.nearby.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageResponse createMessage(String chatId, CreateMessageRequest request) {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setChatId(chatId);
        message.setText(request.getText());
        message.setSenderId(request.getSenderId());
        message.setTimestamp(request.getTimestamp() != null ? request.getTimestamp() : LocalDateTime.now());
        message.setDeleted(false);
        messageRepository.save(message);
        return toMessageResponse(message);
    }

    public List<MessageResponse> getMessagesForChat(String chatId, int limit, int offset) {
        List<Message> messages = messageRepository.findByChatIdOrderByTimestampAsc(chatId);
        return messages.stream().skip(offset).limit(limit).map(this::toMessageResponse).collect(Collectors.toList());
    }

    public int getTotalCountForChat(String chatId) {
        return messageRepository.findByChatIdOrderByTimestampAsc(chatId).size();
    }

    public void deleteMessage(String chatId, String messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new RuntimeException("Message not found"));
        if (!message.getChatId().equals(chatId)) {
            throw new RuntimeException("Message does not belong to chat");
        }
        message.setDeleted(true);
        messageRepository.save(message);
    }

    public MessageResponse toMessageResponse(Message message) {
        MessageResponse resp = new MessageResponse();
        resp.setId(message.getId());
        resp.setChatId(message.getChatId());
        resp.setText(message.getText());
        resp.setSenderId(message.getSenderId());
        resp.setTimestamp(message.getTimestamp());
        resp.setDeleted(message.isDeleted());
        return resp;
    }
} 