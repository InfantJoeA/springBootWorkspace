package com.bookapp;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.model.BookDto;
import com.bookapp.service.IBookService;

//REST api
@RestController
public class BookController {
	
	@Autowired
	private IBookService bookService;
	
	
	@PostMapping("/books")
	ResponseEntity<Void> addBook(@RequestBody BookDto bookDto) {
		bookService.addBook(bookDto);
		return  ResponseEntity.status(201).build();
	}
	
	@PutMapping("/books")
	ResponseEntity<Void> updateBook(@RequestBody BookDto bookDto) {
		bookService.updateBook(bookDto);
	    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("/books/bookId/{bookId}")
	public ResponseEntity<Void> DeleteBook(@PathVariable int bookId) {
		bookService.DeleteBook(bookId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<BookDto>> getAll(){
		List<BookDto> bookDtos = bookService.getAll();
		return ResponseEntity.ok().body(bookDtos);	
	}
	
	@GetMapping("/books/bookId/{bookId}")
    ResponseEntity<BookDto> getById(@PathVariable int bookId) {
		BookDto bookDto = bookService.getById(bookId);
		return ResponseEntity.ok().body(bookDto);
	}
}
