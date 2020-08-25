package hu.szrnkapeter.monolith.h2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DEMO_BOOK")
public class BookEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name ="ID")
	private Long id;
	@Column(name ="AUTHOR")
	private String author;
	@Column(name ="TITLE")
	private String title;
	@Column(name ="RELEASE_YEAR")
	private int releaseYear;
}