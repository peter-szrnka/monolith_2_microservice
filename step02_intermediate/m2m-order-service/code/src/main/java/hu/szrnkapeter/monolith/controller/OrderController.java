package hu.szrnkapeter.monolith.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.szrnkapeter.monolith.dto.BaseResponseDto;
import hu.szrnkapeter.monolith.dto.IdResponseDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@DeleteMapping("/{id}")
	public @ResponseBody BaseResponseDto delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new BaseResponseDto();
	}
	
	@PostMapping("/createDraft")
	public @ResponseBody IdResponseDto createDraft(@RequestBody OrderDto dto) {
		return service.createDraft(dto);
	}
	
	@GetMapping("/{id}")
	public OrderDto getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/all")
	public List<OrderDto> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/initPayment/{id}")
	public void initPayment(@PathVariable("id") Long id) {
		service.initPayment(id);
	}
}