package hu.szrnkapeter.monolith.service;

import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.szrnkapeter.monolith.dao.BookDao;
import hu.szrnkapeter.monolith.dao.OrderDao;
import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.type.OrderStatus;

/**
 * Default implementation of {@link OrderService}.
 * 
 * @author Peter Szrnka
 */
@Service
public class OrderServiceImpl extends BaseService<OrderDto, OrderDao> implements OrderService {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private PaymentService paymentService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.szrnkapeter.monolith.service.AbstractService#getAll()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<OrderDto> getAll() {
		return dao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.szrnkapeter.monolith.service.AbstractService#getById(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	@Override
	public OrderDto getById(Long id) {
		return dao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.szrnkapeter.monolith.service.AbstractService#delete(java.lang.Long)
	 */
	@Transactional
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public IdDto createDraft(OrderDto dto) {
		if (dto.getId() != null || !OrderStatus.DRAFT.equals(dto.getOrderStatus())) {
			throw new RuntimeException("Only drafts are allowed!");
		}

		for (IdDto idDto : ListUtils.emptyIfNull(dto.getBooks())) {
			BookDto book = bookDao.getById(idDto.getId());
			if (book == null) {
				throw new RuntimeException("Book with id=" + idDto.getId() + " does not exists!");
			}
		}

		return dao.save(dto);
	}

	@Transactional
	@Override
	public void initPayment(Long orderId) {
		OrderDto dto = dao.getById(orderId);
		
		if(dto == null || !OrderStatus.DRAFT.equals(dto.getOrderStatus())) {
			throw new RuntimeException("Only DRAFT payments are allowed!");
		}
		
		dto.setOrderStatus(OrderStatus.INITIATED);

		IdDto response = dao.save(dto);
		startPayment(response.getId());
	}

	@Transactional
	@Override
	public void finalizeOrder(Long orderId, String transactionId) {
		OrderDto dto = dao.getById(orderId);
		
		if(dto == null || !OrderStatus.PAYMENT_STARTED.equals(dto.getOrderStatus())) {
			throw new RuntimeException("Only PAYMENT_STARTED payments are allowed!");
		}
		
		dto.setOrderStatus(OrderStatus.FINALIZED);
		dto.setTransactionId(transactionId);
		IdDto response = dao.save(dto);
		System.out.println("Order finalized! Id = " + response.getId());
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void startPayment(Long orderId) {
		OrderDto dto = dao.getById(orderId);
		
		if(dto == null || !OrderStatus.INITIATED.equals(dto.getOrderStatus())) {
			throw new RuntimeException("Only INITIATED payments are allowed!");
		}
		
		dto.setOrderStatus(OrderStatus.PAYMENT_STARTED);
		dao.save(dto);
		
		paymentService.payOrder(orderId);
	}
}