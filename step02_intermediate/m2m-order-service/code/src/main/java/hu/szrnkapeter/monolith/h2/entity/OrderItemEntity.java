package hu.szrnkapeter.monolith.h2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "DEMO_ORDER_ITEM")
@EqualsAndHashCode(exclude = {"fkOrder"})
@ToString(exclude = {"fkOrder"})
public class OrderItemEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name ="ID")
	private Long id;
	
	@Column(name ="QUANTITY")
	private Integer quantity = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_ORDER", nullable = false)
	private OrderEntity fkOrder;

	private Long fkBook;
}