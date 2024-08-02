package simonerhardt.simplechat.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import simonerhardt.simplechat.core.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * Base class to extend the individual JPA repository implementations from.
 *
 * @param <M> The business model class.
 * @param <E> The JPA entity class.
 * @param <I> The identifier class.
 */
public abstract class MappingJpaRepository<M, E, I> implements BaseRepository<M, I> {

	protected final JpaRepository<E, I> springDataJpaRepository;

	protected MappingJpaRepository(JpaRepository<E, I> springDataJpaRepository) {
		this.springDataJpaRepository = springDataJpaRepository;
	}

	@Override
	@Transactional
	public M save(M model) {
		return mapToModel(springDataJpaRepository.save(mapToEntity(model)));
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
