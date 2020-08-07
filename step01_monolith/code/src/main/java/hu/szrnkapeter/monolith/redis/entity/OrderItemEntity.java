package hu.szrnkapeter.monolith.redis.entity;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash
public class OrderItemEntity {
	private Long id;
	private Integer quantity = 0;
	private Long fkBook;
}