package simonerhardt.simplechat.db.chat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import simonerhardt.simplechat.core.chat.ChatSession;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity for {@link ChatSession}.
 */
@Entity
@Table(name = "chat_session")
class ChatSessionEntity {

	@Id
	private UUID id;

	@Column(name = "started_at")
	private LocalDateTime startedAt;

	UUID getId() {
		return id;
	}

	void setId(UUID id) {
		this.id = id;
	}

	LocalDateTime getStartedAt() {
		return startedAt;
	}

	void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}
}
