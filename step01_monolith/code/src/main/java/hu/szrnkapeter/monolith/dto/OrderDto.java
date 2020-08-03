package hu.szrnkapeter.monolith.dto;

import java.time.LocalDate;
import java.util.List;

import hu.szrnkapeter.monolith.type.OrderStatus;
import lombok.Data;

@Data
public class OrderDto {
	
	private Long id;
	private List<BookDto> books;
	private OrderStatus orderStatus;
	private LocalDate orderDate;
	private String transactionId;
}