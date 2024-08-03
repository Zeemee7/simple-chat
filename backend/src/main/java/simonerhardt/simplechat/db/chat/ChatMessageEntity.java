package simonerhardt.simplechat.db.chat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chat_message")
class ChatMessageEntity {

	@Id
	private UUID id;

	// Because we never need the full entity, we only have the id here.
	// The schema asserts the referential integrity.
	@Column(name = "chat_session_id")
	private UUID chatSessionId;

	@Column(name = "sent_at")
	private LocalDateTime sentAt;

	@Column(name = "user_name")
	private String user;

	@Column(name = "message")
	private String message;

	UUID getId() {
		return id;
	}

	void setId(UUID id) {
		this.id = id;
	}

	public UUID getChatSessionId() {
		return chatSessionId;
	}

	public void setChatSessionId(UUID chatSessionId) {
		this.chatSessionId = chatSessionId;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String username) {
		this.user = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
