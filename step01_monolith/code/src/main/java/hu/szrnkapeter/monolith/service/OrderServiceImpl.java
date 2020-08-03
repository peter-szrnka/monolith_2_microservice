package hu.szrnkapeter.monolith.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szrnkapeter.monolith.dao.OrderDao;
import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.OrderDto;

/**
 * Default implementation of {@link OrderService}.
 * 
 * @author Peter Szrnka
 */
@Service
public class OrderServiceImpl extends AbstractServiceImpl<OrderDto, OrderDao> implements OrderService {

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.AbstractService#getAll()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<OrderDto> getAll() {
		return dao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.AbstractService#getById(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	@Override
	public OrderDto getById(Long id) {
		return dao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.service.AbstractService#save(java.lang.Object)
	 */
	@Transactional
	@Override
	public IdDto save(OrderDto dto) {
		return dao.save(dto);
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
}