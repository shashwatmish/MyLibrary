package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Book;

public interface BookRepository {
	Book saveBook(Book book);
	Book getBookById(int id);
	List<Book> getAllBooks();
	Book updateBook(Book book);
	String deleteBookById(int id);
}
