package hu.szrnkapeter.monolith.service;

import java.util.List;

import org.apache.commons.collections4.SetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szrnkapeter.monolith.dao.BookDao;
import hu.szrnkapeter.monolith.dao.OrderDao;
import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.IdResponseDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.dto.OrderItemDto;
import hu.szrnkapeter.monolith.type.OrderStatus;

@Service
public class OrderServiceImpl extends BaseService<OrderDto, OrderDao> implements OrderService {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

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

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.OrderService#createDraft(hu.szrnkapeter.monolith.dto.OrderDto)
	 */
	@Transactional
	@Override
	public IdResponseDto createDraft(OrderDto dto) {
		if (dto.getId() != null || !OrderStatus.DRAFT.equals(dto.getOrderStatus())) {
			throw new RuntimeException("Only drafts are allowed!");
		}

		for (OrderItemDto idDto : SetUtils.emptyIfNull(dto.getItems())) {
			BookDto book = bookDao.getById(idDto.getBook().getId());
			if (book == null) {
				throw new RuntimeException("Book with id=" + idDto.getBook().getId() + " does not exists!");
			}
		}

		LOG.info("Draft created!");
		return dao.save(dto);
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.OrderService#initPayment(java.lang.Long)
	 */
	@Transactional
	@Override
	public void initPayment(Long orderId) {
		OrderDto dto = dao.getById(orderId);
		checkDto(dto, OrderStatus.DRAFT);

		dto.setOrderStatus(OrderStatus.INITIATED);
		IdResponseDto response = dao.save(dto);
		startPayment(response.getId());
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.OrderService#finalizeOrder(java.lang.Long, java.lang.String)
	 */
	@Override
	public void finalizeOrder(Long orderId, String transactionId) {
		OrderDto dto = dao.getById(orderId);
		checkDto(dto, OrderStatus.PAYMENT_STARTED);

		dto.setOrderStatus(OrderStatus.FINALIZED);
		dto.setTransactionId(transactionId);
		IdResponseDto response = dao.save(dto);
		LOG.info("Order finalized! Id = {}", response.getId());
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.OrderService#startPayment(java.lang.Long)
	 */
	@Override
	public void startPayment(Long orderId) {
		OrderDto dto = dao.getById(orderId);
		checkDto(dto, OrderStatus.INITIATED);

		dto.setOrderStatus(OrderStatus.PAYMENT_STARTED);
		dao.save(dto);

		LOG.info("Payment started for order={}", orderId);
		paymentService.payOrder(orderId);
	}

	private void checkDto(OrderDto dto, OrderStatus status) {
		if (dto == null || !status.equals(dto.getOrderStatus())) {
			LOG.error("Only {} payments are allowed!", status.name());
			throw new RuntimeException("Only " + status.name() + " payments are allowed!");
		}
	}
}