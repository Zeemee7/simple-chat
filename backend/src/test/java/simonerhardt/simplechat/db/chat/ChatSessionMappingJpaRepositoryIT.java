package simonerhardt.simplechat.db.chat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import simonerhardt.simplechat.core.chat.ChatSession;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({ ChatSessionMappingJpaRepository.class })
class ChatSessionMappingJpaRepositoryIT {

	@Autowired
	ChatSessionMappingJpaRepository chatSessionMappingJpaRepository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	void saveSaves() {
		LocalDateTime now = LocalDateTime.now();
		UUID id = UUID.randomUUID();
		ChatSession unsaved = new ChatSession(id, now);
		ChatSession saved = chatSessionMappingJpaRepository.save(unsaved);

		ChatSessionEntity entity = entityManager.find(ChatSessionEntity.class, saved.id());

		assertThat(entity).isNotNull();
		assertThat(entity.getId()).isEqualTo(id);
		assertThat(entity.getStartedAt()).isEqualTo(now);
	}

	@Test
	void loadLoads() {
		LocalDateTime now = LocalDateTime.now();
		UUID id = UUID.randomUUID();
		ChatSessionEntity entity = new ChatSessionEntity();
		entity.setId(id);
		entity.setStartedAt(now);
		entityManager.persist(entity);

		Optional<ChatSession> loaded = chatSessionMappingJpaRepository.findById(id);

		assertThat(loaded).isNotEmpty();
		assertThat(loaded.get().startedAt()).isEqualTo(now);
	}
}
