package hu.szrnkapeter.monolith.service;

import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.IdResponseDto;

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
	IdResponseDto save(BookDto dto);
}