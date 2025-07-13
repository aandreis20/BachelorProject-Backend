package dev.nearby.backend.repository;

import dev.nearby.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findByChatIdOrderByTimestampAsc(String chatId);
    List<Message> findByChatIdOrderByTimestampDesc(String chatId);
} 