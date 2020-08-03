package hu.szrnkapeter.monolith.dao;

import java.util.List;

import hu.szrnkapeter.monolith.dto.IdDto;

public interface AbstractDao<T> {

	/**
	 * Deletes a book.
	 * 
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * Returns all books.
	 * 
	 * @return
	 */
	List<T> getAll();
	
	/**
	 * Returns a book by id.
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);
	
	/**
	 * Saves a new book.
	 * 
	 * @param dto
	 * @return
	 */
	IdDto save(T dto);
}