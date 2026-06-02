package com.bookapp.service;

import java.util.List;


import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.model.BookDto;


public interface IBookService {
	//CRUD
	
	void addBook(BookDto bookDto);
	void updateBook(BookDto bookDto);
	void DeleteBook(int bookId);
	
	List<BookDto> getAll() throws BookNotFoundException;
	BookDto getById(int id)throws BookNotFoundException;
//	List<BookDto> getByAuthor(String author)throws BookNotFoundException;
}
