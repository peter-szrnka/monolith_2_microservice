package hu.szrnkapeter.monolith.redis.entity;

import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash
public class PaymentEntity {

	private Long id;
	private Date paymentDate;
	private String transactionId;
}