package hu.szrnkapeter.monolith.service;

/**
 * Order finalization service.
 * 
 * @author Peter Szrnka
 */
public interface OrderFinalizationService {

	/**
	 * Finalizes the order process.
	 * 
	 * @param orderId
	 * @param transactionId
	 */
	void finalizeOrder(Long orderId, String transactionId);
}