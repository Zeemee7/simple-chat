package simonerhardt.simplechat.api.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simonerhardt.simplechat.api.MappingUtils;
import simonerhardt.simplechat.core.chat.ChatMessage;
import simonerhardt.simplechat.core.chat.ChatMessageRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat-sessions/{chatSessionId}/messages")
class ChatMessageController {

	private final ChatMessageRepository chatMessageRepository;

	ChatMessageController(ChatMessageRepository chatMessageRepository) {
		this.chatMessageRepository = chatMessageRepository;
	}

	@PostMapping
	ResponseEntity<ChatMessageDto> createChatMessage(@PathVariable("chatSessionId") String chatSessionId,
			@RequestBody NewChatMessageDto newChatMessageDto) throws URISyntaxException {
		ChatMessageDto dto = mapToDto(chatMessageRepository.save(mapToModel(newChatMessageDto, chatSessionId)));
		return ResponseEntity.created(new URI(String.format("/chat-sessions/%s/messages/%s", dto.chatSessionId(), dto.id()))).body(dto);
	}

	@GetMapping
	List<ChatMessageDto> getChatMessages(@PathVariable("chatSessionId") String chatSessionId) {
		return chatMessageRepository.findByChatSessionId(UUID.fromString(chatSessionId)).stream().map(this::mapToDto).toList();
	}

	private ChatMessageDto mapToDto(ChatMessage chatMessage) {
		return new ChatMessageDto(
				chatMessage.id().toString(),
				chatMessage.chatSessionId().toString(),
				MappingUtils.toEpochMillis(chatMessage.sentAt()),
				chatMessage.user(),
				chatMessage.message());
	}

	private ChatMessage mapToModel(NewChatMessageDto newChatMessageDto, String chatSessionId) {
		return new ChatMessage(UUID.fromString(chatSessionId), newChatMessageDto.user(), newChatMessageDto.message());
	}

	/*
	 * DTO record that is retrieved to create new chat messages.
	 */
	private record NewChatMessageDto(String user, String message) {
	}
}
