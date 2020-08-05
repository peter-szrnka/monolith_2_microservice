package hu.szrnkapeter.monolith.service;

import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.PaymentDto;

/**
 * DAO layer of payments.
 * 
 * @author Peter Szrnka
 */
public interface PaymentService extends AbstractService<PaymentDto> {
	
	/**
	 * Pays the given order.
	 * 
	 * @param orderId
	 * @return
	 */
	IdDto payOrder(Long orderId);
}