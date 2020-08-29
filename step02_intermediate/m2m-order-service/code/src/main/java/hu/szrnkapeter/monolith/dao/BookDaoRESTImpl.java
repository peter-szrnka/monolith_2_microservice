package hu.szrnkapeter.monolith.dao;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.IdResponseDto;

@Component
public class BookDaoRESTImpl implements BookDao {
	
	@Value("${book.service.url}")
	private String bookBaseUrl;
	
	@Autowired
	private RestTemplate restTemplate;

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.AbstractDao#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		throw new UnsupportedOperationException("Service method not supported!");
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.AbstractDao#getAll()
	 */
	@Override
	public List<BookDto> getAll() {
		throw new UnsupportedOperationException("Service method not supported!");
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.AbstractDao#getById(java.lang.Long)
	 */
	@Override
	public BookDto getById(Long id) {
		return restTemplate.getForObject(URI.create(bookBaseUrl + "/book/" + id), BookDto.class);
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.AbstractDao#save(java.lang.Object)
	 */
	@Override
	public IdResponseDto save(BookDto dto) {
		throw new UnsupportedOperationException("Service method not supported!");
	}
}