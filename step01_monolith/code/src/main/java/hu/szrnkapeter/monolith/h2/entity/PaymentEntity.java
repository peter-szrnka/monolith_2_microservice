package hu.szrnkapeter.monolith.h2.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DEMO_PAYMENT")
public class PaymentEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name ="ID")
	private Long id;

	@Column(name ="PAYMENT_DATE")
	private Date paymentDate;
	
	@Column(name ="TRANSACTION_ID")
	private String transactionId;
}