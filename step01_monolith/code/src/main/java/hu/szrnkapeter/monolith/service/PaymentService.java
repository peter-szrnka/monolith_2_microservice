package hu.szrnkapeter.monolith.service;

import hu.szrnkapeter.monolith.dto.IdResponseDto;
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
	IdResponseDto payOrder(Long orderId);
}