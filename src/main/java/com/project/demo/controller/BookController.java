package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.dao.BookRepository;
import com.project.demo.models.Book;

@Controller
public class BookController {
	@Autowired
	BookRepository bookrepo;
	
	@PostMapping("/addbook")
	public @ResponseBody Book AddBook(@RequestBody Book Book)
	{
		return bookrepo.saveBook(Book);
	}
	
	@GetMapping("/getbook")
	public @ResponseBody List<Book> GetBook()
	{
		return bookrepo.getAllBooks();
	}
	
	@GetMapping("/getbook/{id}")
	public @ResponseBody Book GetBook(@PathVariable("id")int id)
	{
		
		return bookrepo.getBookById(id);
	}
	
	@DeleteMapping("/deletebook/{id}")
	public @ResponseBody String DeleteBook(@PathVariable("id")int id)
	{
		
		return bookrepo.deleteBookById(id);
	}
	
	@PutMapping("/updatebook")
	public @ResponseBody Book UpdateBook(@RequestBody Book book)
	{
		return bookrepo.updateBook(book);
	}

}
