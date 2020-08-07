package hu.szrnkapeter.monolith.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import hu.szrnkapeter.monolith.utils.Constants;

@ConditionalOnProperty(name = Constants.PARAMETER_DAO_IMPL, havingValue = Constants.DAO_IMPL_REDIS)
@Configuration
@EnableRedisRepositories
public class RedisDatabaseConfig {

	@Bean
	public RedisTemplate<Long, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
	    RedisTemplate<Long, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(connectionFactory);
	    return template;
	}
}