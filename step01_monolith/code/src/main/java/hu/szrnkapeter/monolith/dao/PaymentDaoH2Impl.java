package hu.szrnkapeter.monolith.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.PaymentDto;
import hu.szrnkapeter.monolith.h2.entity.PaymentEntity;
import hu.szrnkapeter.monolith.h2.repository.H2PaymentRepository;
import hu.szrnkapeter.monolith.utils.Constants;

@ConditionalOnProperty(name = Constants.PARAMETER_DAO_IMPL, havingValue = Constants.DAO_IMPL_H2)
@Component
public class PaymentDaoH2Impl extends DaoBase<PaymentEntity> implements PaymentDao {
	
	@Autowired
	private H2PaymentRepository repository;
	
	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.DaoBase#daoFindById(java.lang.Long)
	 */
	@Override
	protected Optional<PaymentEntity> daoFindById(Long id) {
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
	public List<PaymentDto> getAll() {
		return repository.findAll().stream().map(entity -> {
			return convertToDto(entity);
		}).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.BookDao#save(hu.szrnkapeter.monolith.dto.PaymentDto)
	 */
	@Override
	public IdDto save(PaymentDto dto) {
		PaymentEntity entity = new PaymentEntity();
		
		getByIdOrThrowError(entity, dto.getId());
		
		entity.setId(dto.getId());
		entity.setPaymentDate(dto.getPaymentDate());
		entity.setTransactionId(dto.getTransactionId());
		entity = repository.save(entity);
		
		return new IdDto(entity.getId());
	}

	/*
	 * (non-Javadoc)
	 * @see hu.szrnkapeter.monolith.dao.BookDao#getById(java.lang.Long)
	 */
	@Override
	public PaymentDto getById(Long id) {
		Optional<PaymentEntity> entity = repository.findById(id);

		if(!entity.isPresent()) {
			throw new RuntimeException("Entity does not exists!");
		}

		return convertToDto(entity.get());
	}

	private PaymentDto convertToDto(PaymentEntity entity) {
		PaymentDto dto = new PaymentDto();
		// TODO put it in a common converter
		dto.setId(entity.getId());
		dto.setPaymentDate(entity.getPaymentDate());
		dto.setTransactionId(entity.getTransactionId());
		return dto;
	}
}