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

import com.google.common.collect.Lists;

import hu.szrnkapeter.monolith.AbstractServiceTest;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.h2.entity.BookEntity;
import hu.szrnkapeter.monolith.h2.entity.OrderEntity;
import hu.szrnkapeter.monolith.h2.repository.H2BookRepository;
import hu.szrnkapeter.monolith.h2.repository.H2OrderRepository;

public class OrderDaoH2ImplTest extends AbstractServiceTest {

	@Mock
	private H2OrderRepository repository;
	@Mock
	private H2BookRepository bookRepository;
	@InjectMocks
	private OrderDaoH2Impl service;
	
	@Test
	public void test01_delete() {
		service.delete(1L);
		Mockito.verify(repository).deleteById(ArgumentMatchers.anyLong());
	}

	@Test
	public void test02_getAll() {
		List<OrderEntity> mockList = new ArrayList<>();
		mockList.add(new OrderEntity());
		Mockito.when(repository.findAll()).thenReturn(mockList);
		List<OrderDto> response = service.getAll();
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
		Assert.assertEquals("Wrong response!", 1, response.size());
	}

	@Test
	public void test03_getById() {
		Optional<OrderEntity> mockOptional = Optional.empty();
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockOptional);
		OrderDto response = null;
		try {
			response = service.getById(1L);
		} catch(Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}

		OrderEntity result = new OrderEntity();
		result.setBooks(Lists.newArrayList(new BookEntity()));
		mockOptional = Optional.of(result);
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockOptional);
		response = service.getById(1L);
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
	}
	
	@Test
	public void test04_save() {
		Mockito.when(repository.save(ArgumentMatchers.any(OrderEntity.class))).thenReturn(new OrderEntity());
		// Test1
		service.save(new OrderDto());
		Mockito.verify(repository).save(ArgumentMatchers.any(OrderEntity.class));
		
		// Test2
		OrderDto dto = new OrderDto();
		dto.setId(1L);
		Optional<OrderEntity> mockResult = Optional.empty();
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockResult);

		try {
			service.save(dto);
		} catch(Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}

		// Test3
		BookEntity mockBook = new BookEntity();
		Mockito.when(bookRepository.findById(ArgumentMatchers.eq(1L))).thenReturn(Optional.of(mockBook));
		dto.setBooks(Lists.newArrayList(new IdDto(1L),new IdDto(2L)));
		OrderEntity mockEntity = new OrderEntity();
		mockEntity.setBooks(Lists.newArrayList(new BookEntity()));
		mockResult = Optional.of(mockEntity);
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockResult);
		service.save(dto);
	}
}