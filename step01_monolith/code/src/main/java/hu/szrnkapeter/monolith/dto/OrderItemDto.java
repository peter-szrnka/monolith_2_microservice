package hu.szrnkapeter.monolith.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {

	private Long id;
	private IdDto book;
	private Integer quantity;
}