package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Book;

public interface BookRepository {
	int saveBook(Book book);
	Book getBookById(int id);
	List<Book> getAllBooks();
	int updateBook(Book book);
	int updateBookNew(Book book);
	int deleteBookById(int id);
}
