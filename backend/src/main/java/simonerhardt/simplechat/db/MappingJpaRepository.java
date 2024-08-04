package simonerhardt.simplechat.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import simonerhardt.simplechat.core.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * Base class to extend the individual JPA repository implementations from. Because of the desired mapping
 * from JPA entities to neutral (core) data models, Spring Data JPA repositories are not implemented directly.
 * Instead, they are a (autowired) member of the respective {@link MappingJpaRepository} implementation and
 * used to perform the actual JPA operations, whereas the {@link MappingJpaRepository} adds the mapping on
 * top, and inputs/outputs only core data models.
 *
 * @param <M> The core model class.
 * @param <E> The JPA entity class.
 * @param <I> The class of the model's/entity's identifier (has to be the same).
 * @param <R> The Spring Data JPA repository providing the necessary JPA operations.
 */
public abstract class MappingJpaRepository<M, E, I, R extends JpaRepository<E, I>> implements BaseRepository<M, I> {

	protected final R springDataJpaRepository;

	protected MappingJpaRepository(R springDataJpaRepository) {
		this.springDataJpaRepository = springDataJpaRepository;
	}

	@Override
	@Transactional
	public M save(M model) {
		return mapToModel(springDataJpaRepository.saveAndFlush(mapToEntity(model)));
	}

	@Override
	public Optional<M> findById(I i) {
		Optional<E> entity = springDataJpaRepository.findById(i);
		if (entity.isPresent()) {
			return entity.map(this::mapToModel);
		}
		return Optional.empty();
	}

	@Override
	public List<M> findAll() {
		return springDataJpaRepository.findAll().stream().map(this::mapToModel).toList();
	}

	protected abstract M mapToModel(E entity);

	protected abstract E mapToEntity(M model);
}
