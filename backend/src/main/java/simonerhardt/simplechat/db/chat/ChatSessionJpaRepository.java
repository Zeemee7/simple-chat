package simonerhardt.simplechat.db.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for {@link ChatSessionEntity}.
 */
interface ChatSessionJpaRepository extends JpaRepository<ChatSessionEntity, UUID> {
}
