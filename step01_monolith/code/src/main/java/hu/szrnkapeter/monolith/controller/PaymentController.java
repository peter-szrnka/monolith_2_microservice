package hu.szrnkapeter.monolith.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szrnkapeter.monolith.dto.PaymentDto;
import hu.szrnkapeter.monolith.service.PaymentService;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	@GetMapping("/{id}")
	public PaymentDto getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/all")
	public List<PaymentDto> getAll() {
		return service.getAll();
	}
	
	/*@PostMapping("/save")
	public void save(@RequestBody PaymentDto dto) {
		service.save(dto);
	}*/
}