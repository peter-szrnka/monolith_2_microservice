package hu.szrnkapeter.monolith.dao;

import java.util.List;

import hu.szrnkapeter.monolith.dto.IdResponseDto;

public interface AbstractDao<T> {

	/**
	 * Deletes an entity.
	 * 
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * Returns all entities.
	 * 
	 * @return
	 */
	List<T> getAll();
	
	/**
	 * Returns an entity by id.
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);
	
	/**
	 * Saves a new entity.
	 * 
	 * @param dto
	 * @return
	 */
	IdResponseDto save(T dto);
}