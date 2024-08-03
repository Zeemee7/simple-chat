package simonerhardt.simplechat.api.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simonerhardt.simplechat.api.MappingUtils;
import simonerhardt.simplechat.core.chat.ChatSession;
import simonerhardt.simplechat.core.chat.ChatSessionRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/chat-sessions")
class ChatSessionController {

	private final ChatSessionRepository chatSessionRepository;

	ChatSessionController(ChatSessionRepository chatSessionRepository) {
		this.chatSessionRepository = chatSessionRepository;
	}

	@PostMapping
	ResponseEntity<ChatSessionDto> createChatSession() throws URISyntaxException {
		ChatSessionDto dto = mapToDto(chatSessionRepository.save(new ChatSession()));
		return ResponseEntity.created(new URI("/chat-sessions/" + dto.id())).body(dto);
	}

	@GetMapping
	List<ChatSessionDto> getChatSessions() {
		return chatSessionRepository.findAll().stream().map(this::mapToDto).toList();
	}

	private ChatSessionDto mapToDto(ChatSession chatSession) {
		return new ChatSessionDto(chatSession.id().toString(), MappingUtils.toEpochMillis(chatSession.startedAt()));
	}
}
