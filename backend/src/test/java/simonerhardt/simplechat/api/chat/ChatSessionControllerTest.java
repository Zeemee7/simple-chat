package simonerhardt.simplechat.api.chat;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

		assertThat(chatSessionDto).isNotNull();
		assertThat(chatSessionDto.id()).isEqualTo(chatSession.id().toString());
		long epochMillis = chatSession.startedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		assertThat(chatSessionDto.startedAt()).isEqualTo(epochMillis);
	}
}
