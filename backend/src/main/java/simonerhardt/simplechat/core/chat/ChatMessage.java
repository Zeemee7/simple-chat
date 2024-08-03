package simonerhardt.simplechat.core.chat;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChatMessage(UUID id, UUID chatSessionId, LocalDateTime sentAt, String user, String message) {
	public ChatMessage(UUID chatSessionId, String user, String message) {
		this(UUID.randomUUID(), chatSessionId, LocalDateTime.now(), user, message);
	}
}
