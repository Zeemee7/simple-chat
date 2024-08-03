package simonerhardt.simplechat.db.chat;

import org.springframework.stereotype.Repository;
import simonerhardt.simplechat.core.chat.ChatMessage;
import simonerhardt.simplechat.core.chat.ChatMessageRepository;
import simonerhardt.simplechat.db.MappingJpaRepository;

import java.util.UUID;

@Repository
class ChatMessageMappingJpaRepository extends MappingJpaRepository<ChatMessage, ChatMessageEntity, UUID> implements ChatMessageRepository {

	protected ChatMessageMappingJpaRepository(ChatMessageJpaRepository springDataJpaRepository) {
		super(springDataJpaRepository);
	}

	@Override
	protected ChatMessage mapToModel(ChatMessageEntity entity) {
		return new ChatMessage(entity.getId(), entity.getChatSessionId(), entity.getSentAt(), entity.getUser(), entity.getMessage());
	}

	@Override
	protected ChatMessageEntity mapToEntity(ChatMessage model) {
		ChatMessageEntity entity = new ChatMessageEntity();
		entity.setId(model.id());
		entity.setSentAt(model.sentAt());
		entity.setChatSessionId(model.chatSessionId());
		entity.setUser(model.user());
		entity.setMessage(model.message());
		return entity;
	}
}
