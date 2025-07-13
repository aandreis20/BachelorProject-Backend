package dev.nearby.backend.controller;

import dev.nearby.backend.dto.*;
import dev.nearby.backend.service.ChatService;
import dev.nearby.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = "*")
public class ChatController {
    private final ChatService chatService;
    private final MessageService messageService;

    @Autowired
    public ChatController(ChatService chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    // 1. Creează o nouă conversație
    @PostMapping
    public ResponseEntity<ChatResponse> createChat(@RequestBody CreateChatRequest request) {
        ChatResponse response = chatService.createChat(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2. Obține lista conversațiilor
    @GetMapping
    public ResponseEntity<Map<String, List<ChatResponse>>> getChats(@RequestParam String userId) {
        List<ChatResponse> chats = chatService.getChatsForUser(userId);
        Map<String, List<ChatResponse>> resp = new HashMap<>();
        resp.put("chats", chats);
        return ResponseEntity.ok(resp);
    }

    // 3. Obține istoricul mesajelor pentru o conversație
    @GetMapping("/{chatId}/messages")
    public ResponseEntity<Map<String, Object>> getMessages(
            @PathVariable String chatId,
            @RequestParam(defaultValue = "50") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        List<MessageResponse> messages = messageService.getMessagesForChat(chatId, limit, offset);
        int totalCount = messageService.getTotalCountForChat(chatId);
        Map<String, Object> resp = new HashMap<>();
        resp.put("messages", messages);
        resp.put("totalCount", totalCount);
        resp.put("hasMore", totalCount > (offset + messages.size()));
        return ResponseEntity.ok(resp);
    }

    // 4. Salvează un mesaj nou
    @PostMapping("/{chatId}/messages")
    public ResponseEntity<MessageResponse> createMessage(
            @PathVariable String chatId,
            @RequestBody CreateMessageRequest request) {
        MessageResponse response = messageService.createMessage(chatId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 5. Șterge un mesaj
    @DeleteMapping("/{chatId}/messages/{messageId}")
    public ResponseEntity<Map<String, Object>> deleteMessage(
            @PathVariable String chatId,
            @PathVariable String messageId) {
        messageService.deleteMessage(chatId, messageId);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("message", "Message deleted successfully");
        return ResponseEntity.ok(resp);
    }

    // 6. Caută conversații
    @GetMapping("/search")
    public ResponseEntity<Map<String, List<ChatResponse>>> searchChats(
            @RequestParam String userId,
            @RequestParam String query) {
        List<ChatResponse> chats = chatService.searchChatsForUser(userId, query);
        Map<String, List<ChatResponse>> resp = new HashMap<>();
        resp.put("chats", chats);
        return ResponseEntity.ok(resp);
    }
} 