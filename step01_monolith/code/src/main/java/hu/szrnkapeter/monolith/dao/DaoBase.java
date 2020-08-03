package hu.szrnkapeter.monolith.dao;

import java.util.Optional;

public abstract class DaoBase<T> {

	/**
	 * Returns an entity by id.
	 * 
	 * @param id
	 * @return
	 */
	protected abstract Optional<T> daoFindById(Long id);

	/**
	 * Returns an entity by id, or throws an error.
	 * 
	 * @param entity
	 * @param id
	 */
	protected void getByIdOrThrowError(T entity, Long id) {
		if (id == null) {
			return;
		}

		Optional<T> result = daoFindById(id);

		if (!result.isPresent()) {
			throw new RuntimeException("Entity not found by id!");
		}

		entity = result.get();
	}
}