package simonerhardt.simplechat.core.chat;

import simonerhardt.simplechat.core.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ChatMessageRepository extends BaseRepository<ChatMessage, UUID> {

	List<ChatMessage> findByChatSessionId(UUID chatSessionId);

}
