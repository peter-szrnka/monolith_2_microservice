package hu.szrnkapeter.monolith.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hu.szrnkapeter.monolith.dto.IdResponseDto;
import hu.szrnkapeter.monolith.dto.PaymentDto;

/**
 * Default implementation of {@link PaymentService}.
 * 
 * @author Peter Szrnka
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaymentDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentDto getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdResponseDto payOrder(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}