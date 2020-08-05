package hu.szrnkapeter.monolith.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szrnkapeter.monolith.dao.PaymentDao;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.PaymentDto;

/**
 * Default implementation of {@link PaymentService}.
 * 
 * @author Peter Szrnka
 */
@Service
public class PaymentServiceImpl extends AbstractServiceImpl<PaymentDto, PaymentDao> implements PaymentService {

	@Autowired
	private OrderFinalizationService finalizationService;

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.AbstractService#getAll()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<PaymentDto> getAll() {
		return dao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.AbstractService#getById(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	@Override
	public PaymentDto getById(Long id) {
		return dao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.AbstractService#delete(java.lang.Long)
	 */
	@Transactional
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.PaymentService#payOrder(java.lang.Long)
	 */
	@Transactional
	@Override
	public IdDto payOrder(Long orderId) {
		PaymentDto dto = new PaymentDto();
		dto.setTransactionId(UUID.randomUUID().toString());
		dto.setPaymentDate(new Date());
		IdDto response = dao.save(dto);
		
		finalizationService.finalizeOrder(orderId, dto.getTransactionId());
		return response;
	}
}