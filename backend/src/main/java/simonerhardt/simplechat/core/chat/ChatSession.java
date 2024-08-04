package simonerhardt.simplechat.core.chat;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Model record for a chat session. A chat session represents a dialogue of two users with related {@link ChatMessage}s.
 *
 * @param id        Unique id of the chat session object.
 * @param startedAt The time the session has been created.
 * @see ChatMessage
 */
public record ChatSession(UUID id, LocalDateTime startedAt) {
	/**
	 * Creates a new {@link ChatSession} with a unique id and {@link #startedAt} set to now.
	 */
	public ChatSession() {
		this(UUID.randomUUID(), LocalDateTime.now());
	}
}
