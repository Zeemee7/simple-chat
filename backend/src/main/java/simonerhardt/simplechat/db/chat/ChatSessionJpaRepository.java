package simonerhardt.simplechat.db.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatSessionJpaRepository extends JpaRepository<ChatSessionEntity, UUID> {
}
