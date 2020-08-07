package hu.szrnkapeter.monolith.h2.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import hu.szrnkapeter.monolith.type.OrderStatus;
import lombok.Data;

@Data
@Entity
@Table(name = "DEMO_ORDER")
public class OrderEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name ="ID")
	private Long id;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fkOrder", orphanRemoval = true)
	private Set<OrderItemEntity> items;
	@Column(name ="ORDER_STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus = OrderStatus.INITIATED;
	@Column(name ="ORDER_DATE")
	private LocalDate orderDate;
	@Column(name ="TRANSACTION_ID", nullable = true)
	private String transactionId;
}