package simonerhardt.simplechat.db.chat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import simonerhardt.simplechat.core.chat.ChatMessage;
import simonerhardt.simplechat.db.BaseMappingJpaRepositoryIT;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({ ChatMessageMappingJpaRepository.class, ChatSessionMappingJpaRepository.class })
class ChatMessageMappingJpaRepositoryIT extends BaseMappingJpaRepositoryIT<ChatMessage, ChatMessageEntity, UUID, ChatMessageJpaRepository> {

	@Autowired
	private ChatSessionMappingJpaRepository chatSessionMappingJpaRepository;

	@Override
	protected Class<ChatMessageEntity> getEntityClass() {
		return ChatMessageEntity.class;
	}

	@Test
	void findByChatSessionId() {
		ChatSessionEntity session1 = persistNewChatSession();
		ChatMessageEntity message1 = entityManager.persist(createNewEntity(session1));
		ChatMessageEntity message2 = entityManager.persist(createNewEntity(session1));
		ChatSessionEntity session2 = persistNewChatSession();
		entityManager.persist(createNewEntity(session2));

		List<ChatMessage> messages = ((ChatMessageMappingJpaRepository) repository).findByChatSessionId(session1.getId());

		// It only contains messages of session 1
		assertThat(messages).hasSize(2)
				.anyMatch(message -> message.id().equals(message1.getId()))
				.anyMatch(message -> message.id().equals(message2.getId()));
	}

	@Override
	protected ChatMessage createNewModel() {
		ChatSessionEntity chatSessionEntity = persistNewChatSession();
		return new ChatMessage(chatSessionEntity.getId(), "user-" + UUID.randomUUID(), "message-" + UUID.randomUUID());
	}

	@Override
	protected ChatMessageEntity createNewEntity() {
		return createNewEntity(persistNewChatSession());
	}

	private ChatMessageEntity createNewEntity(ChatSessionEntity chatSessionEntity) {
		ChatMessageEntity entity = new ChatMessageEntity();
		entity.setId(UUID.randomUUID());
		entity.setSentAt(LocalDateTime.now());
		entity.setUser("user-" + UUID.randomUUID());
		entity.setMessage("message-" + UUID.randomUUID());
		entity.setChatSessionId(chatSessionEntity.getId());
		return entity;
	}

	@Override
	protected UUID getModelId(ChatMessage model) {
		return model.id();
	}

	@Override
	protected UUID getEntityId(ChatMessageEntity entity) {
		return entity.getId();
	}

	@Override
	protected void assertPropertiesMatch(ChatMessage model, ChatMessageEntity entity) {
		assertThat(model.id()).isEqualTo(entity.getId());
		assertThat(model.sentAt()).isEqualTo(entity.getSentAt());
		assertThat(model.user()).isEqualTo(entity.getUser());
		assertThat(model.message()).isEqualTo(entity.getMessage());
		assertThat(model.chatSessionId()).isEqualTo(entity.getChatSessionId());
	}

	private ChatSessionEntity persistNewChatSession() {
		ChatSessionEntity chatSessionEntity = new ChatSessionEntity();
		chatSessionEntity.setId(UUID.randomUUID());
		chatSessionEntity.setStartedAt(LocalDateTime.now());
		entityManager.persist(chatSessionEntity);
		return chatSessionEntity;
	}
}
