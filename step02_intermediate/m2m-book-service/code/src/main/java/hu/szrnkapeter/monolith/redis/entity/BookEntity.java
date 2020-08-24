package hu.szrnkapeter.monolith.redis.entity;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash
public class BookEntity {

	private Long id;
	private String author;
	private String title;
	private int releaseYear;
}