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
import hu.szrnkapeter.monolith.dao.PaymentDao;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.PaymentDto;

public class PaymentServiceImplTest extends AbstractServiceTest {

	@InjectMocks
	private PaymentServiceImpl service;
	@Mock
	private PaymentDao dao;
	@Mock
	private OrderFinalizationService finalizationService;

	@Test
	public void test01_delete() {
		service.delete(1L);
		Mockito.verify(dao).delete(ArgumentMatchers.anyLong());
	}

	@Test
	public void test02_getAll() {
		List<PaymentDto> mockList = new ArrayList<>();
		mockList.add(new PaymentDto());
		Mockito.when(dao.getAll()).thenReturn(mockList);
		List<PaymentDto> response = service.getAll();
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
		Assert.assertEquals("Wrong response!", 1, response.size());
	}

	@Test
	public void test03_getById() {
		Mockito.when(dao.getById(ArgumentMatchers.anyLong())).thenReturn(new PaymentDto());
		PaymentDto response = service.getById(1L);
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
	}

	@Test
	public void test04_payOrder() {
		Mockito.when(dao.save(ArgumentMatchers.any(PaymentDto.class))).thenReturn(new IdDto(1L));
		IdDto response = service.payOrder(1L);
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
		Assert.assertEquals("Wrong response!", Long.valueOf(1L), response.getId());
	}
}