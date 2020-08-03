package hu.szrnkapeter.monolith.redis.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import hu.szrnkapeter.monolith.type.OrderStatus;
import lombok.Data;

@Data
@RedisHash
public class OrderEntity {

	private Long id;
	private List<BookEntity> books;
	private OrderStatus orderStatus = OrderStatus.INITIATED;
	private LocalDate orderDate;
	private String transactionId;
}