package simonerhardt.simplechat.api.chat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import simonerhardt.simplechat.core.chat.ChatSession;
import simonerhardt.simplechat.core.chat.ChatSessionRepository;

import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatSessionController.class)
class ChatSessionControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ChatSessionRepository chatSessionRepository;

	@Test
	void createChatSessionSavesAndReturnsNewSession() throws Exception {
		ChatSession chatSession = new ChatSession();
		when(chatSessionRepository.save(any())).thenReturn(chatSession);

		MvcResult mvcResult = mvc.perform(post("/api/v1/chat-sessions")).andExpect(status().isCreated()).andReturn();

		String json = mvcResult.getResponse().getContentAsString();
		ChatSessionDto chatSessionDto = new ObjectMapper().readValue(json, ChatSessionDto.class);

		assertChatSession(chatSessionDto, chatSession);
	}

	@Test
	void getChatSessionsReturnsChatSessions() throws Exception {
		ChatSession chatSession1 = new ChatSession();
		ChatSession chatSession2 = new ChatSession();
		when(chatSessionRepository.findAll()).thenReturn(List.of(chatSession1, chatSession2));

		MvcResult mvcResult = mvc.perform(get("/api/v1/chat-sessions")).andExpect(status().isOk()).andReturn();

		String json = mvcResult.getResponse().getContentAsString();
		List<ChatSessionDto> chatSessions = new ObjectMapper().readValue(json, new TypeReference<>() {
		});
		assertThat(chatSessions).isNotEmpty();
		assertChatSession(chatSessions.get(0), chatSession1);
		assertChatSession(chatSessions.get(1), chatSession2);
	}

	private static void assertChatSession(ChatSessionDto dto, ChatSession model) {
		assertThat(dto).isNotNull();
		assertThat(dto.id()).isEqualTo(model.id().toString());
		long epochMillis = model.startedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		assertThat(dto.startedAt()).isEqualTo(epochMillis);
	}
}
