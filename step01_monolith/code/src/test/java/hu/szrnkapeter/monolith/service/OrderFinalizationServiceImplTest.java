package hu.szrnkapeter.monolith.service;

import java.util.UUID;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import hu.szrnkapeter.monolith.AbstractServiceTest;

public class OrderFinalizationServiceImplTest extends AbstractServiceTest {

	@InjectMocks
	private OrderFinalizationServiceImpl service;
	@Mock
	private OrderService orderService;
	
	@Test
	public void test() {
		final String transactionId = UUID.randomUUID().toString();
		service.finalizeOrder(1L, transactionId);
		
		Mockito.verify(orderService).finalizeOrder(1L, transactionId);
	}
}