package hu.szrnkapeter.monolith.h2.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.szrnkapeter.monolith.h2.entity.OrderEntity;
import hu.szrnkapeter.monolith.utils.Constants;

@ConditionalOnProperty(name = "dao.impl", havingValue = Constants.DAO_IMPL_H2)
@Repository
public interface H2OrderRepository extends JpaRepository<OrderEntity, Long> {
}