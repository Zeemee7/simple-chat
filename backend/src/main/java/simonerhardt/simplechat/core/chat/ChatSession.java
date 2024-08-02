package simonerhardt.simplechat.core.chat;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChatSession(UUID id, LocalDateTime startedAt) {
	public ChatSession() {
		this(UUID.randomUUID(), LocalDateTime.now());
	}
}
