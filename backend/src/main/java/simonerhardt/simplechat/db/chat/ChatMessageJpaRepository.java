package simonerhardt.simplechat.db.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatMessageJpaRepository extends JpaRepository<ChatMessageEntity, UUID> {

	List<ChatMessageEntity> findByChatSessionId(UUID chatSessionId);

}
