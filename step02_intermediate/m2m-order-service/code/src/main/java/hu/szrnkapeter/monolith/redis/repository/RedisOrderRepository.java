package hu.szrnkapeter.monolith.redis.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szrnkapeter.monolith.redis.entity.OrderEntity;
import hu.szrnkapeter.monolith.utils.Constants;

/**
 * https://www.baeldung.com/spring-data-redis-tutorial
 */
@ConditionalOnProperty(name = Constants.PARAMETER_DAO_IMPL, havingValue = Constants.DAO_IMPL_REDIS)
@Repository
public interface RedisOrderRepository extends JpaRepository<OrderEntity, Long> {
}