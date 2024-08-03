package simonerhardt.simplechat.db.chat;

import org.springframework.stereotype.Repository;
import simonerhardt.simplechat.core.chat.ChatSession;
import simonerhardt.simplechat.core.chat.ChatSessionRepository;
import simonerhardt.simplechat.db.MappingJpaRepository;

import java.util.UUID;

@Repository
class ChatSessionMappingJpaRepository extends MappingJpaRepository<ChatSession, ChatSessionEntity, UUID, ChatSessionJpaRepository>
		implements ChatSessionRepository {

	protected ChatSessionMappingJpaRepository(ChatSessionJpaRepository springDataJpaRepository) {
		super(springDataJpaRepository);
	}

	@Override
	protected ChatSession mapToModel(ChatSessionEntity entity) {
		return new ChatSession(entity.getId(), entity.getStartedAt());
	}

	@Override
	protected ChatSessionEntity mapToEntity(ChatSession model) {
		ChatSessionEntity entity = new ChatSessionEntity();
		entity.setId(model.id());
		entity.setStartedAt(model.startedAt());
		return entity;
	}
}
