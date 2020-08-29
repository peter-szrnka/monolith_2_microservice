package hu.szrnkapeter.monolith.redis.entity;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.redis.core.RedisHash;

import hu.szrnkapeter.monolith.type.OrderStatus;
import lombok.Data;

@Data
@RedisHash
public class OrderEntity {

	private Long id;
	private Set<OrderItemEntity> items;
	private OrderStatus orderStatus = OrderStatus.INITIATED;
	private LocalDate orderDate;
	private String transactionId;
}