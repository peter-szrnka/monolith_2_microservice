package hu.szrnkapeter.monolith.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import hu.szrnkapeter.monolith.AbstractServiceTest;
import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.h2.entity.BookEntity;
import hu.szrnkapeter.monolith.h2.repository.H2BookRepository;

public class BookDaoH2ImplTest extends AbstractServiceTest {

	@InjectMocks
	private BookDaoH2Impl service;
	@Mock
	private H2BookRepository repository;

	@Test
	public void test01_delete() {
		service.delete(1L);
		Mockito.verify(repository).deleteById(ArgumentMatchers.anyLong());
	}

	@Test
	public void test02_getAll() {
		List<BookEntity> mockList = new ArrayList<>();
		mockList.add(new BookEntity());
		Mockito.when(repository.findAll()).thenReturn(mockList);
		List<BookDto> response = service.getAll();
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
		Assert.assertEquals("Wrong response!", 1, response.size());
	}

	@Test
	public void test03_getById() {
		Optional<BookEntity> mockOptional = Optional.empty();
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockOptional);
		BookDto response = null;
		try {
			response = service.getById(1L);
		} catch(Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}

		BookEntity result = new BookEntity();
		mockOptional = Optional.of(result);
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockOptional);
		response = service.getById(1L);
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
	}
	
	@Test
	public void test04_save() {
		// Test1
		service.save(new BookDto());
		Mockito.verify(repository).save(ArgumentMatchers.any(BookEntity.class));
		
		// Test2
		BookDto dto = new BookDto();
		dto.setId(1L);
		Optional<BookEntity> mockResult = Optional.empty();
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockResult);

		try {
			service.save(dto);
		} catch(Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}
		
		// Test3
		mockResult = Optional.of(new BookEntity());
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockResult);
		service.save(dto);
	}
}