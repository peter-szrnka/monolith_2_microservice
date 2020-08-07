package hu.szrnkapeter.monolith.dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.IdResponseDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.dto.OrderItemDto;
import hu.szrnkapeter.monolith.h2.entity.BookEntity;
import hu.szrnkapeter.monolith.h2.entity.OrderEntity;
import hu.szrnkapeter.monolith.h2.entity.OrderItemEntity;
import hu.szrnkapeter.monolith.h2.repository.H2BookRepository;
import hu.szrnkapeter.monolith.h2.repository.H2OrderRepository;
import hu.szrnkapeter.monolith.utils.Constants;

@ConditionalOnProperty(name = Constants.PARAMETER_DAO_IMPL, havingValue = Constants.DAO_IMPL_H2)
@Component
public class OrderDaoH2Impl extends DaoBase<OrderEntity> implements OrderDao {

	@Autowired
	private H2OrderRepository repository;
	@Autowired
	private H2BookRepository bookRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.szrnkapeter.monolith.dao.DaoBase#daoFindById(java.lang.Long)
	 */
	@Override
	protected Optional<OrderEntity> daoFindById(Long id) {
		return repository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.szrnkapeter.monolith.dao.BookDao#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.szrnkapeter.monolith.dao.BookDao#getAll()
	 */
	@Override
	public List<OrderDto> getAll() {
		return repository.findAll().stream().map(entity -> {
			return convertToDto(entity);
		}).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.szrnkapeter.monolith.dao.BookDao#save(hu.szrnkapeter.monolith.dto.
	 * OrderDto)
	 */
	@Override
	public IdResponseDto save(OrderDto dto) {
		OrderEntity entity = new OrderEntity();

		getByIdOrThrowError(entity, dto.getId());

		if (entity.getId() == null) {
			entity.setId(dto.getId());
		}
		entity.setItems(convertDtoListToEntity(entity, dto.getItems()));
		entity.setOrderDate(LocalDate.now());
		entity.setOrderStatus(dto.getOrderStatus());
		entity.setTransactionId(dto.getTransactionId());
		entity = repository.save(entity);

		return new IdResponseDto(entity.getId());
	}

	private Set<OrderItemEntity> convertDtoListToEntity(OrderEntity orderEntity, Set<OrderItemDto> items) {
		orderEntity.setItems(null);
		Set<OrderItemEntity> result = SetUtils.emptyIfNull(items).stream().map(dto -> {
			Optional<BookEntity> entityResult = bookRepository.findById(dto.getBook().getId());

			if (!entityResult.isPresent()) {
				return null;
			}

			BookEntity book = entityResult.get();

			OrderItemEntity entity = new OrderItemEntity();
			entity.setId(dto.getId());
			entity.setFkOrder(orderEntity);
			entity.setFkBook(book.getId());
			entity.setQuantity(dto.getQuantity());
			return entity;
		}).filter(entity -> entity != null).collect(Collectors.toSet());

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.szrnkapeter.monolith.dao.BookDao#getById(java.lang.Long)
	 */
	@Override
	public OrderDto getById(Long id) {
		Optional<OrderEntity> entity = repository.findById(id);

		if (!entity.isPresent()) {
			throw new RuntimeException("Entity does not exists!");
		}

		return convertToDto(entity.get());
	}

	private OrderDto convertToDto(OrderEntity entity) {
		OrderDto dto = new OrderDto();
		dto.setId(entity.getId());
		dto.setOrderDate(entity.getOrderDate());
		dto.setOrderStatus(entity.getOrderStatus());
		dto.setTransactionId(entity.getTransactionId());

		Set<OrderItemDto> items = new HashSet<>();
		System.out.println("order convert to dto: " + entity.getItems());
		for (OrderItemEntity item : SetUtils.emptyIfNull(entity.getItems())) {
			items.add(new OrderItemDto(item.getId(), new IdDto(item.getFkBook()), item.getQuantity()));
		}
		dto.setItems(items);
		return dto;
	}
}