package hu.szrnkapeter.monolith.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import hu.szrnkapeter.monolith.AbstractServiceTest;
import hu.szrnkapeter.monolith.dao.BookDao;
import hu.szrnkapeter.monolith.dto.BookDto;

public class BookServiceImplTest extends AbstractServiceTest {

	@InjectMocks
	private BookServiceImpl service;
	@Mock
	private BookDao dao;

	@Test
	public void test01_delete() {
		service.delete(1L);
		Mockito.verify(dao).delete(ArgumentMatchers.anyLong());
	}

	@Test
	public void test02_getAll() {
		List<BookDto> mockList = new ArrayList<>();
		mockList.add(new BookDto());
		Mockito.when(dao.getAll()).thenReturn(mockList);
		List<BookDto> response = service.getAll();
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
		Assert.assertEquals("Wrong response!", 1, response.size());
	}

	@Test
	public void test03_getById() {
		Mockito.when(dao.getById(ArgumentMatchers.anyLong())).thenReturn(new BookDto());
		BookDto response = service.getById(1L);
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
	}
	
	@Test
	public void test04_save() {
		service.save(new BookDto());
	}
}