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
import com.project.demo.dao.StockRepository;
import com.project.demo.models.Book;
import com.project.demo.models.Stock;

@Controller
public class StockController {
	
	@Autowired
	StockRepository stockrepo;
	
	@Autowired
	BookRepository booksrepo;
	
	@PostMapping("/addstock")
	public @ResponseBody Stock AddStock(@RequestBody Stock stock)
	{
		Stock savedStock =  stockrepo.saveStock(stock);
		int cnt = savedStock.getBooksavailable();
		for(int i=1;i<=cnt;i++)
		{
			Book book = new Book();
			book.setShelfId(101);
			book.setLanguage("English");
			book.setPublications(savedStock.getPublications());
			book.setAuthor(savedStock.getAuthor());
			book.setTitle(savedStock.getTitle());
			book = booksrepo.saveBook(book);
		}
		return savedStock;
	}
	
	@GetMapping("/getstock")
	public @ResponseBody List<Stock> GetStock()
	{
		return stockrepo.getAllStocks();
	}
	
	@GetMapping("/getstock/{title}/{author}/{publications}")
	public @ResponseBody Stock GetStock(
			@PathVariable("title") String title, 
			@PathVariable("author") String author, 
			@PathVariable("publications") String publications
			)
	{
		
		return stockrepo.getStock(title, author, publications);
	}
	
	@DeleteMapping("/deletestock/{title}/{author}/{publications}")
	public @ResponseBody String DeleteStock(
			@PathVariable("title") String title, 
			@PathVariable("author") String author, 
			@PathVariable("publications") String publications
			)
	{
		
		return stockrepo.deleteStockById(title, author, publications);
	}
	
	@PutMapping("/updatestock")
	public @ResponseBody Stock UpdateStock(@RequestBody Stock stock)
	{
		// for now assume that the stock is being added
		Stock intialStock = stockrepo.getStock(stock.getTitle(),stock.getAuthor(),stock.getPublications());
		int prev = intialStock.getBooksavailable();
		Stock updatedStock =  stockrepo.updateStock(stock);
		int cur = updatedStock.getBooksavailable();
		
		for(int i=1;i<=cur-prev;i++)
		{
			Book book = new Book();
			book.setShelfId(101);
			book.setLanguage("English");
			book.setPublications(updatedStock.getPublications());
			book.setAuthor(updatedStock.getAuthor());
			book.setTitle(updatedStock.getTitle());
			book = booksrepo.saveBook(book);
		}
		
		return updatedStock;
	}
}
