package simonerhardt.simplechat.core.chat;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Model record for a single chat message.
 *
 * @param id            Unique id of the message object.
 * @param chatSessionId The id of the {@link ChatSession} the message belongs to.
 * @param sentAt        The time the message has been sent (or created).
 * @param user          The user that sent the message.
 * @param message       The message text.
 * @see ChatSession
 */
public record ChatMessage(UUID id, UUID chatSessionId, LocalDateTime sentAt, String user, String message) {
	/**
	 * Creates a new {@link ChatMessage} with a unique id and {@link #sentAt} set to now.
	 *
	 * @param chatSessionId The id of the {@link ChatSession} the message belongs to.
	 * @param user          The user that sent the message.
	 * @param message       The message text.
	 */
	public ChatMessage(UUID chatSessionId, String user, String message) {
		this(UUID.randomUUID(), chatSessionId, LocalDateTime.now(), user, message);
	}
}
