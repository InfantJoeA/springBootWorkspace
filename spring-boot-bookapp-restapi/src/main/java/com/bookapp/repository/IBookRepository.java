package com.bookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookapp.model.Book;

public interface IBookRepository extends JpaRepository<Book, Integer> {

}
