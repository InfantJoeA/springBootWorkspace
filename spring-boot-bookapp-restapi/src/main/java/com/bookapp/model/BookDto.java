package com.bookapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
	
	private String title;
	private Integer bookId;
	private double price;
	private String author;
	private String category;

}
