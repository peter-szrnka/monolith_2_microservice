package hu.szrnkapeter.monolith.dto;

import java.time.LocalDate;
import java.util.Set;

import hu.szrnkapeter.monolith.type.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderDto extends BaseResponseDto {
	
	private Long id;
	private Set<OrderItemDto> items;
	private OrderStatus orderStatus;
	private LocalDate orderDate;
	private String transactionId;
}