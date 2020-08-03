package hu.szrnkapeter.monolith.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.szrnkapeter.monolith.dto.IdDto;
import hu.szrnkapeter.monolith.dto.OrderDto;
import hu.szrnkapeter.monolith.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@GetMapping("/{id}")
	public OrderDto getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/all")
	public List<OrderDto> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/save")
	public @ResponseBody IdDto save(@RequestBody OrderDto dto) {
		return service.save(dto);
	}
}