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

import com.google.common.collect.Sets;

import hu.szrnkapeter.monolith.AbstractServiceTest;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.dto.OrderItemDto;
import hu.szrnkapeter.monolith.redis.entity.BookEntity;
import hu.szrnkapeter.monolith.redis.entity.OrderEntity;
import hu.szrnkapeter.monolith.redis.entity.OrderItemEntity;
import hu.szrnkapeter.monolith.redis.repository.RedisBookRepository;
import hu.szrnkapeter.monolith.redis.repository.RedisOrderRepository;

public class OrderDaoRedisImplTest extends AbstractServiceTest {

	@Mock
	private RedisOrderRepository repository;
	@Mock
	private RedisBookRepository bookRepository;
	@InjectMocks
	private OrderDaoRedisImpl service;
	
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
		result.setItems(Sets.newHashSet(new OrderItemEntity()));
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
		dto.setItems(Sets.newHashSet(createOrderItemDto(1L, 1), createOrderItemDto(2L, 1)));
		OrderEntity mockEntity = new OrderEntity();
		mockEntity.setItems(Sets.newHashSet(new OrderItemEntity()));
		mockResult = Optional.of(mockEntity);
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockResult);
		service.save(dto);
	}
	
	private OrderItemDto createOrderItemDto(Long id, Integer quantity) {
		return new OrderItemDto(1L, new IdDto(id), quantity);
	}
}