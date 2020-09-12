package hu.szrnkapeter.monolith.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFinalizationServiceImpl implements OrderFinalizationService {
	
	@Autowired
	private OrderService orderService;

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.OrderFinalizationService#finalizeOrder(java.lang.Long, java.lang.String)
	 */
	@Transactional
	@Override
	public void finalizeOrder(Long orderId, String transactionId) {
		orderService.finalizeOrder(orderId, transactionId);
	}
}