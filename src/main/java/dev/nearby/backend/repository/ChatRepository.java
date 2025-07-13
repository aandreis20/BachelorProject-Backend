package dev.nearby.backend.repository;

import dev.nearby.backend.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {
    List<Chat> findByParticipantIdsContaining(String userId);
    List<Chat> findByParticipantIdsContainingAndChatNameContainingIgnoreCase(String userId, String query);
} 