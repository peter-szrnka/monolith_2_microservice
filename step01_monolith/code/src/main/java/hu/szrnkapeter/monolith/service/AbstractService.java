package hu.szrnkapeter.monolith.service;

import java.util.List;

import hu.szrnkapeter.monolith.dto.IdDto;

public interface AbstractService<T> {

	/**
	 * Returns all books.
	 * 
	 * @return
	 */
	List<T> getAll();
	
	/**
	 * Returns one book by id.
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);
	
	/**
	 * Saves a new book.
	 * 
	 * @param dto
	 */
	IdDto save(T dto);
	
	/**
	 * Deletes a book by id
	 * 
	 * @param id
	 */
	void delete(Long id);
}