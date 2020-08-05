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
import hu.szrnkapeter.monolith.dto.PaymentDto;
import hu.szrnkapeter.monolith.h2.entity.PaymentEntity;
import hu.szrnkapeter.monolith.h2.repository.H2PaymentRepository;

public class PaymentDaoH2ImplTest extends AbstractServiceTest {

	@InjectMocks
	private PaymentDaoH2Impl service;
	@Mock
	private H2PaymentRepository repository;

	@Test
	public void test01_delete() {
		service.delete(1L);
		Mockito.verify(repository).deleteById(ArgumentMatchers.anyLong());
	}

	@Test
	public void test02_getAll() {
		List<PaymentEntity> mockList = new ArrayList<>();
		mockList.add(new PaymentEntity());
		Mockito.when(repository.findAll()).thenReturn(mockList);
		List<PaymentDto> response = service.getAll();
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
		Assert.assertEquals("Wrong response!", 1, response.size());
	}

	@Test
	public void test03_getById() {
		Optional<PaymentEntity> mockOptional = Optional.empty();
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockOptional);
		PaymentDto response = null;
		try {
			response = service.getById(1L);
		} catch(Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}

		PaymentEntity result = new PaymentEntity();
		mockOptional = Optional.of(result);
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockOptional);
		response = service.getById(1L);
		Assert.assertNotNull(RESPONSE_CANNOT_NULL, response);
	}
	
	@Test
	public void test04_save() {
		Mockito.when(repository.save(ArgumentMatchers.any(PaymentEntity.class))).thenReturn(new PaymentEntity());
		// Test1
		service.save(new PaymentDto());
		Mockito.verify(repository).save(ArgumentMatchers.any(PaymentEntity.class));
		
		// Test2
		PaymentDto dto = new PaymentDto();
		dto.setId(1L);
		Optional<PaymentEntity> mockResult = Optional.empty();
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockResult);

		try {
			service.save(dto);
		} catch(Exception e) {
			Assert.assertTrue("Wrong response!", e instanceof RuntimeException);
		}
		
		// Test3
		mockResult = Optional.of(new PaymentEntity());
		Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(mockResult);
		service.save(dto);
	}
}