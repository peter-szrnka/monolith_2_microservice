package hu.szrnkapeter.monolith.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.h2.entity.BookEntity;
import hu.szrnkapeter.monolith.h2.entity.OrderEntity;
import hu.szrnkapeter.monolith.h2.repository.H2OrderRepository;
import hu.szrnkapeter.monolith.utils.Constants;

@ConditionalOnProperty(name = Constants.PARAMETER_DAO_IMPL, havingValue = Constants.DAO_IMPL_H2)
@Component
public class OrderDaoH2Impl extends DaoBase<OrderEntity> implements OrderDao {
	
	@Autowired
	private H2OrderRepository repository;
	
	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.DaoBase#daoFindById(java.lang.Long)
	 */
	@Override
	protected Optional<OrderEntity> daoFindById(Long id) {
		return repository.findById(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.BookDao#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/*
	 * (non-Javadoc)
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
	 * @see hu.szrnkapeter.monolith.dao.BookDao#save(hu.szrnkapeter.monolith.dto.OrderDto)
	 */
	@Override
	public IdDto save(OrderDto dto) {
		OrderEntity entity = new OrderEntity();
		
		getByIdOrThrowError(entity, dto.getId());
		
		entity.setId(dto.getId());
		entity.setBooks(convertDtoListToEntity(dto.getBooks()));
		entity.setOrderDate(dto.getOrderDate());
		entity.setOrderStatus(dto.getOrderStatus());
		entity.setTransactionId(dto.getTransactionId());
		entity = repository.save(entity);
		
		return new IdDto(entity.getId());
	}

	private List<BookEntity> convertDtoListToEntity(List<BookDto> books) {
		return books.stream().map(dto -> {
			// TODO put it in a common converter
			BookEntity entity = new BookEntity();
			entity.setAuthor(dto.getAuthor());
			entity.setId(dto.getId());
			entity.setReleaseYear(dto.getReleaseYear());
			entity.setTitle(dto.getTitle());
			return entity;
		}).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.BookDao#getById(java.lang.Long)
	 */
	@Override
	public OrderDto getById(Long id) {
		Optional<OrderEntity> entity = repository.findById(id);

		if(!entity.isPresent()) {
			throw new RuntimeException("Entity does not exists!");
		}

		return convertToDto(entity.get());
	}

	private OrderDto convertToDto(OrderEntity entity) {
		OrderDto dto = new OrderDto();
		// TODO put it in a common converter
		dto.setId(entity.getId());
		dto.setOrderDate(entity.getOrderDate());
		dto.setOrderStatus(entity.getOrderStatus());
		dto.setTransactionId(entity.getTransactionId());
		return dto;
	}
}