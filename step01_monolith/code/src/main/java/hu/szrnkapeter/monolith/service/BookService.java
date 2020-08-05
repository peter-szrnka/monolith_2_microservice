package hu.szrnkapeter.monolith.service;

import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.IdDto;

/**
 * DAO layer of books.
 * 
 * @author Peter Szrnka
 */
public interface BookService extends AbstractService<BookDto> {
	
	/**
	 * Saves a new book.
	 * 
	 * @param dto
	 */
	IdDto save(BookDto dto);
}