package com.bookapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.model.Book;
import com.bookapp.model.BookDto;
import com.bookapp.repository.IBookRepository;

@Service
public class BookServiceImpl implements IBookService {
	
	private IBookRepository bookRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
//Autowired annotation not required
	public BookServiceImpl(IBookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public void addBook(BookDto bookDto) {
		//Converting the dto object to entity
		Book book = modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);
		
	}

	@Override
	public void updateBook(BookDto bookDto) {
		//Converting the dto object to entity
		Book book = modelMapper.map(bookDto, Book.class);
		bookRepository.save(book);
	}

	@Override
	public void DeleteBook(int bookId) {
		bookRepository.deleteById(bookId);
	}

	@Override
	public List<BookDto> getAll() throws BookNotFoundException {
		List<Book> listBooks = bookRepository.findAll();
		//legacy way
//		List<BookDto> bookDtos = new ArrayList<>();
		
//		for(Book book : listBooks){
//			//Convert entity object to Dto
//			BookDto bookDto = modelMapper.map(book,BookDto.class);
//			bookDtos.add(bookDto);
//		}
		List<BookDto> bookDtos = listBooks.stream()
				.map(book -> modelMapper.map(book, BookDto.class))
				.collect(Collectors.toList());
		return bookDtos;
	}

	@Override
	public BookDto getById(int id) throws BookNotFoundException {
	   Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("invalid id"));
	  
		return  modelMapper.map(book, BookDto.class);
	}

//	@Override
//	public List<BookDto> getByAuthor(String author) throws BookNotFoundException {
//		return null;
//	}
}
