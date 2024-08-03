package simonerhardt.simplechat.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base class of ITs for subclasses of {@link MappingJpaRepository} that tests the base methods.
 *
 * @param <M> The business model class.
 * @param <E> The JPA entity class.
 * @param <I> The identifier class.
 */
@DataJpaTest
public abstract class BaseMappingJpaRepositoryIT<M, E, I> {

	@Autowired
	MappingJpaRepository<M, E, I> repository;

	@Autowired
	protected TestEntityManager entityManager;

	@Test
	void saveSavesAllProperties() {
		M unsaved = createNewModel();

		M saved = repository.save(unsaved);

		E loaded = entityManager.find(getEntityClass(), getModelId(saved));
		assertPropertiesMatch(unsaved, loaded);
	}

	@Test
	void findByIdLoadsAllProperties() {
		E entity = createNewEntity();
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.clear();

		Optional<M> loaded = repository.findById(getEntityId(entity));

		assertPropertiesMatch(loaded.orElseThrow(), entity);
	}

	@Test
	void findByIdFindsTheCorrectEntity() {
		E entity1 = createNewEntity();
		E entity2 = createNewEntity();
		entityManager.persist(entity1);
		entityManager.persist(entity2);
		entityManager.flush();
		entityManager.clear();

		Optional<M> loaded1 = repository.findById(getEntityId(entity1));
		Optional<M> loaded2 = repository.findById(getEntityId(entity2));

		assertThat(getModelId(loaded1.orElseThrow())).isEqualTo(getEntityId(entity1));
		assertThat(getModelId(loaded2.orElseThrow())).isEqualTo(getEntityId(entity2));
	}

	@Test
	void findAllReturnsAll() {
		entityManager.persist(createNewEntity());
		entityManager.persist(createNewEntity());

		List<M> all = repository.findAll();

		assertThat(all).hasSize(2);
	}

	protected abstract Class<E> getEntityClass();

	/**
	 * Creates a model object with random/unique properties.
	 *
	 * @return A new model object.
	 */
	protected abstract M createNewModel();

	/**
	 * Creates an entity object with random/unique properties.
	 *
	 * @return A new entity object.
	 */
	protected abstract E createNewEntity();

	protected abstract I getModelId(M model);

	protected abstract I getEntityId(E entity);

	protected abstract void assertPropertiesMatch(M model, E entity);
}
