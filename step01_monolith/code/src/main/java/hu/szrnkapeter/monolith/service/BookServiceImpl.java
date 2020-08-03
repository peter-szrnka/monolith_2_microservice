package hu.szrnkapeter.monolith.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szrnkapeter.monolith.dao.BookDao;
import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.IdDto;

/**
 * Default implementation of {@link BookService}.
 * 
 * @author Peter Szrnka
 */
@Service
public class BookServiceImpl extends AbstractServiceImpl<BookDto, BookDao> implements BookService {

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.BookService#delete(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.BookService#getAll()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<BookDto> getAll() {
		return dao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.BookService#getById(java.lang.Long)
	 */
	@Transactional
	@Override
	public BookDto getById(Long id) {
		return dao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.BookService#save(hu.szrnkapeter.monolith.dto.BookDto)
	 */
	@Transactional
	@Override
	public IdDto save(BookDto dto) {
		return dao.save(dto);
	}
}