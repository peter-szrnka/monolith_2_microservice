package hu.szrnkapeter.monolith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.szrnkapeter.monolith.dto.BaseResponseDto;
import hu.szrnkapeter.monolith.dto.PaymentDto;
import hu.szrnkapeter.monolith.dto.PaymentListDto;
import hu.szrnkapeter.monolith.service.PaymentService;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	private PaymentService service;

	@DeleteMapping("/{id}")
	public @ResponseBody BaseResponseDto delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new BaseResponseDto();
	}

	@GetMapping("/{id}")
	public PaymentDto getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/all")
	public PaymentListDto getAll() {
		return new PaymentListDto(service.getAll());
	}
}