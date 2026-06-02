package com.bookapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
//@ToString
@Data
@Entity
//@Table(name="BK_details") //to give a different table name
public class Book {
	
	private String title;
	@Id            //id is must
	@GeneratedValue
	private Integer bookId;
	@Column(name="Cost")
	private double price;
	@Column(length=20)
	private String author;
	@Column
	private String category;

}
