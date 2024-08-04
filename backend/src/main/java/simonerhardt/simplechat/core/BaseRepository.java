package simonerhardt.simplechat.core;

import java.util.List;
import java.util.Optional;

/**
 * Base interface to extend the individual, model-specific repository interfaces from.
 * This interface should contain operations that make sense for all models.
 *
 * @param <M> The business model class.
 * @param <I> The identifier class.
 */
public interface BaseRepository<M, I> {
	M save(M model);

	Optional<M> findById(I i);

	List<M> findAll();
}
