package simonerhardt.simplechat.db.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simonerhardt.simplechat.core.chat.ChatSession;
import simonerhardt.simplechat.core.chat.ChatSessionRepository;
import simonerhardt.simplechat.db.MappingJpaRepository;

import java.util.UUID;

@Repository
class ChatSessionMappingJpaRepository extends MappingJpaRepository<ChatSession, ChatSessionEntity, UUID> implements ChatSessionRepository {

	protected ChatSessionMappingJpaRepository(JpaRepository<ChatSessionEntity, UUID> springDataJpaRepository) {
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
