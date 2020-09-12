package hu.szrnkapeter.monolith.dto;

import lombok.Data;

@Data
public class BookDto {

	private Long id;
	private String author;
	private String title;
	private int releaseYear;
}