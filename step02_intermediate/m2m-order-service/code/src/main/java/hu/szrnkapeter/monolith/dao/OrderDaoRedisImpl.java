package hu.szrnkapeter.monolith.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.IdResponseDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.dto.OrderItemDto;
import hu.szrnkapeter.monolith.redis.entity.OrderEntity;
import hu.szrnkapeter.monolith.redis.entity.OrderItemEntity;
import hu.szrnkapeter.monolith.redis.repository.RedisOrderRepository;
import hu.szrnkapeter.monolith.utils.Constants;

@ConditionalOnProperty(name = Constants.PARAMETER_DAO_IMPL, havingValue = Constants.DAO_IMPL_REDIS)
@Component
public class OrderDaoRedisImpl extends DaoBase<OrderEntity> implements OrderDao {

	@Autowired
	private RedisOrderRepository repository;
	@Autowired
	private BookDao bookRepository;

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
		entity.setItems(convertDtoListToEntity(dto.getItems()));
		entity.setOrderDate(LocalDate.now());
		entity.setOrderStatus(dto.getOrderStatus());
		entity.setTransactionId(dto.getTransactionId());
		entity = repository.save(entity);

		return new IdResponseDto(entity.getId());
	}

	private Set<OrderItemEntity> convertDtoListToEntity(Set<OrderItemDto> items) {
		return SetUtils.emptyIfNull(items).stream().map(dto -> {
			BookDto book = bookRepository.getById(dto.getBook().getId());

			if (book == null) {
				return null;
			}

			OrderItemEntity entity = new OrderItemEntity();
			entity.setFkBook(book.getId());
			entity.setQuantity(dto.getQuantity());
			return entity;
		}).collect(Collectors.toSet());
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

		Set<OrderItemDto> items = Sets.newHashSet();
		for (OrderItemEntity item : SetUtils.emptyIfNull(entity.getItems())) {
			items.add(new OrderItemDto(item.getId(), new IdDto(item.getFkBook()), item.getQuantity()));
		}
		dto.setItems(items);
		return dto;
	}
}