package hu.szrnkapeter.monolith.controller;

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
import hu.szrnkapeter.monolith.dto.BookDto;
import hu.szrnkapeter.monolith.dto.BookListDto;
import hu.szrnkapeter.monolith.dto.IdResponseDto;
import hu.szrnkapeter.monolith.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService service;
	
	@DeleteMapping("/{id}")
	public @ResponseBody BaseResponseDto delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new BaseResponseDto();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody BookDto getById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/all")
	public @ResponseBody BookListDto getAll() {
		return new BookListDto(service.getAll());
	}
	
	@PostMapping("/save")
	public @ResponseBody IdResponseDto save(@RequestBody BookDto dto) {
		return service.save(dto);
	}
}