package simonerhardt.simplechat.db.chat;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import simonerhardt.simplechat.core.chat.ChatSession;
import simonerhardt.simplechat.db.BaseMappingJpaRepositoryIT;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({ ChatSessionMappingJpaRepository.class })
class ChatSessionMappingJpaRepositoryIT extends BaseMappingJpaRepositoryIT<ChatSession, ChatSessionEntity, UUID> {

	@Override
	protected Class<ChatSessionEntity> getEntityClass() {
		return ChatSessionEntity.class;
	}

	@Override
	protected ChatSession createNewModel() {
		return new ChatSession();
	}

	@Override
	protected ChatSessionEntity createNewEntity() {
		ChatSessionEntity entity = new ChatSessionEntity();
		entity.setId(UUID.randomUUID());
		entity.setStartedAt(LocalDateTime.now());
		return entity;
	}

	@Override
	protected UUID getModelId(ChatSession model) {
		return model.id();
	}

	@Override
	protected UUID getEntityId(ChatSessionEntity entity) {
		return entity.getId();
	}

	@Override
	protected void assertPropertiesMatch(ChatSession model, ChatSessionEntity entity) {
		assertThat(model.startedAt()).isEqualTo(entity.getStartedAt());
		assertThat(model.id()).isEqualTo(entity.getId());
	}
}
