package hu.szrnkapeter.monolith.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService service;
	
	@GetMapping("/{id}")
	public BookDto getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/all")
	public List<BookDto> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/save")
	public void save(@RequestBody BookDto dto) {
		service.save(dto);
	}
}