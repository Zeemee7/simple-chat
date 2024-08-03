package simonerhardt.simplechat.api.chat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import simonerhardt.simplechat.core.chat.ChatMessage;
import simonerhardt.simplechat.core.chat.ChatMessageRepository;

import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatMessageController.class)
class ChatMessageControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ChatMessageRepository chatMessageRepository;

	@Test
	void createChatMessageSavesAndReturnsNewMessage() throws Exception {
		UUID chatSessionId = UUID.randomUUID();
		ChatMessage chatMessage = new ChatMessage(chatSessionId, "myuser", "mymessage");
		when(chatMessageRepository.save(any())).thenReturn(chatMessage);

		MvcResult mvcResult = mvc.perform(post("/api/v1/chat-sessions/{chatSessionId}/messages",
				chatSessionId).contentType(MediaType.APPLICATION_JSON).content("""
					{ "user": "myuser", "message": "mymessage" }
				""")).andExpect(status().isCreated()).andReturn();

		// Correct values saved
		ArgumentCaptor<ChatMessage> chatMessageCaptor = ArgumentCaptor.forClass(ChatMessage.class);
		verify(chatMessageRepository).save(chatMessageCaptor.capture());
		ChatMessage capturedMessage = chatMessageCaptor.getValue();
		assertThat(capturedMessage.chatSessionId()).isEqualTo(chatSessionId);
		assertThat(capturedMessage.user()).isEqualTo("myuser");
		assertThat(capturedMessage.message()).isEqualTo("mymessage");

		// Correct object returned
		String json = mvcResult.getResponse().getContentAsString();
		ChatMessageDto chatMessageDto = new ObjectMapper().readValue(json, ChatMessageDto.class);
		assertChatMessage(chatMessageDto, chatMessage);
	}

	@Test
	void getChatMessagesReturnsChatMessages() throws Exception {
		UUID chatSessionId = UUID.randomUUID();
		ChatMessage chatMessage1 = new ChatMessage(chatSessionId, "user1", "message1");
		ChatMessage chatMessage2 = new ChatMessage(chatSessionId, "user1", "message1");
		when(chatMessageRepository.findByChatSessionId(chatSessionId)).thenReturn(List.of(chatMessage1, chatMessage2));

		MvcResult mvcResult = mvc.perform(get("/api/v1/chat-sessions/{chatSessionId}/messages",
						chatSessionId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String json = mvcResult.getResponse().getContentAsString();
		List<ChatMessageDto> chatSessions = new ObjectMapper().readValue(json, new TypeReference<>() {
		});
		assertThat(chatSessions).isNotEmpty();
		assertChatMessage(chatSessions.get(0), chatMessage1);
		assertChatMessage(chatSessions.get(1), chatMessage2);
	}

	private static void assertChatMessage(ChatMessageDto dto, ChatMessage model) {
		assertThat(dto).isNotNull();
		assertThat(dto.id()).isEqualTo(model.id().toString());
		long epochMillis = model.sentAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		assertThat(dto.sentAt()).isEqualTo(epochMillis);
		assertThat(dto.user()).isEqualTo(model.user());
		assertThat(dto.message()).isEqualTo(model.message());
		assertThat(dto.chatSessionId()).isEqualTo(model.chatSessionId().toString());
	}
}
