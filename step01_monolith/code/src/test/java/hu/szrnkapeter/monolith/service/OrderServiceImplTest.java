package hu.szrnkapeter.monolith.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.common.collect.Sets;

import hu.szrnkapeter.monolith.AbstractServiceTest;
import hu.szrnkapeter.monolith.dao.BookDao;
import hu.szrnkapeter.monolith.dao.OrderDao;
import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.IdResponseDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.dto.OrderItemDto;
import hu.szrnkapeter.monolith.type.OrderStatus;

public class OrderServiceImplTest extends AbstractServiceTest {

	@InjectMocks
	private OrderServiceImpl service;
	@Mock
	private OrderDao dao;
	@Mock
	private BookDao bookDao;
	@Mock
	private PaymentService paymentService;

	@Test
	public void test01_delete() {
		service.delete(1L);
		Mockito.verify(dao).delete(ArgumentMatchers.anyLong());
	}

	@Test
	public void test02_getAll() {
		List<OrderDto> mockList = new ArrayList<>();
		mockList.add(new OrderDto());
		Mockito.when(dao.getAll()).thenReturn(mockList);
		List<OrderDto> response = service.getAll();
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
		Assert.assertEquals("Wrong response!", 1, response.size());
	}

	@Test
	public void test03_getById() {
		Mockito.when(dao.getById(ArgumentMatchers.anyLong())).thenReturn(new OrderDto());
		OrderDto response = service.getById(1L);
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
	}

	@Test
	public void test04_createDraft() {
		// Test1
		OrderDto dto = new OrderDto();
		IdResponseDto response = null;

		try {
			response = service.createDraft(dto);
		} catch (Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}

		// Test2
		dto.setId(1L);
		try {
			response = service.createDraft(dto);
		} catch (Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}

		// Test3
		Mockito.when(dao.save(ArgumentMatchers.any(OrderDto.class))).thenReturn(new IdResponseDto(1L));
		dto.setId(null);
		dto.setOrderStatus(OrderStatus.DRAFT);
		dto.setItems(Sets.newHashSet(new OrderItemDto(1L, new IdDto(1L), 1)));
		try {
			response = service.createDraft(dto);
		} catch (Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}

		// Test4
		Mockito.when(bookDao.getById(ArgumentMatchers.anyLong())).thenReturn(new BookDto());
		response = service.createDraft(dto);
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
	}

	@Test
	public void test05_initPayment() {
		// Test1
		Mockito.when(dao.getById(ArgumentMatchers.anyLong())).thenReturn(null);

		try {
			service.initPayment(1L);
		} catch (Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}

		// Test2
		OrderDto mockOrder = new OrderDto();
		mockOrder.setOrderStatus(OrderStatus.FINALIZED);
		Mockito.when(dao.getById(ArgumentMatchers.anyLong())).thenReturn(mockOrder);
		Mockito.when(dao.save(ArgumentMatchers.any(OrderDto.class))).thenReturn(new IdResponseDto(1L));

		try {
			service.initPayment(1L);
		} catch (Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}

		// Test3
		mockOrder.setOrderStatus(OrderStatus.DRAFT);
		Mockito.when(dao.getById(ArgumentMatchers.anyLong())).thenReturn(mockOrder);
		Mockito.when(dao.save(ArgumentMatchers.any(OrderDto.class))).thenReturn(new IdResponseDto(1L));
		service.initPayment(1L);
	}
	
	@Test
	public void test06_finalizeOrder() {
		OrderDto mockOrder = new OrderDto();
		mockOrder.setOrderStatus(OrderStatus.PAYMENT_STARTED);
		Mockito.when(dao.getById(ArgumentMatchers.anyLong())).thenReturn(mockOrder);
		Mockito.when(dao.save(ArgumentMatchers.any(OrderDto.class))).thenReturn(new IdResponseDto(1L));
		service.finalizeOrder(1L, UUID.randomUUID().toString());
		
		Mockito.verify(dao).getById(ArgumentMatchers.anyLong());
		Mockito.verify(dao).save(ArgumentMatchers.any(OrderDto.class));
	}
}