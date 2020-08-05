package hu.szrnkapeter.monolith.service;

import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.OrderDto;

/**
 * DAO layer of orders.
 * 
 * @author Peter Szrnka
 */
public interface OrderService extends AbstractService<OrderDto> {
	
	/**
	 * Adds the content to the basket.
	 * 
	 * @param dto
	 * @return
	 */
	IdDto createDraft(OrderDto dto);

	/**
	 * Initializes the payment
	 * 
	 * @param orderId
	 */
	void initPayment(Long orderId);
	
	/**
	 * Starts the payment
	 * 
	 * @param orderId
	 */
	void startPayment(Long orderId);
	
	/**
	 * Finalizes the given order
	 * 
	 * @param dto
	 */
	void finalizeOrder(Long orderId, String transactionId);
}