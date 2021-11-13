package com.project.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	public boolean is_manager(HttpSession session) {
		if(session.getAttribute("manager")==null) return false;
		else return true;
	}
	
	public boolean is_student(HttpSession session) {
		if(session.getAttribute("student")==null) return false;
		else return true;
	}
	
	public boolean is_staff(HttpSession session) {
		if(session.getAttribute("staff")==null) return false;
		else return true;
	}
	
	@Autowired
	StockRepository stockrepo;
	
	@Autowired
	BookRepository booksrepo;
	
	@PostMapping("/addstock")
	public String AddStock(@RequestBody Stock stock,Model model, HttpSession session)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			return "home";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ADD BOOKS");
			return "home";
		}
		
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
			int flag = booksrepo.saveBook(book);
		}
		
		model.addAttribute("error","STOCK ADDED SUCCESSFULLY");
		return "home";
	}
	
	@GetMapping("/getstocks")
	public @ResponseBody String GetStock(Model model)
	{
		List <Stock> list = stockrepo.getAllStocks();
		model.addAttribute("qresult",list);
		return "Stocks";
	}
	
	@GetMapping("/getstock/{title}/{author}/{publications}")
	public String GetStock(
			@PathVariable("title") String title, 
			@PathVariable("author") String author, 
			@PathVariable("publications") String publications,
			Model model, HttpSession session
			)
	{
		
		 Stock stock =  stockrepo.getStock(title, author, publications);
		 model.addAttribute("stock",stock);
		 return "Stock";
	}
	
	@DeleteMapping("/deletestock/{title}/{author}/{publications}")
	public @ResponseBody String DeleteStock(
			@PathVariable("title") String title, 
			@PathVariable("author") String author, 
			@PathVariable("publications") String publications,
			Model model,HttpSession session
			)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			return "home";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ADD BOOKS");
			return "home";
		}
		
		int flag = stockrepo.deleteStockById(title, author, publications);
		if(flag!=1)
		{
			model.addAttribute("ERROR","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		model.addAttribute("error","STOCK DELETED SUCCESSFULLY");
		return "home";
	}
	
	@PutMapping("/updatestock")
	public @ResponseBody String UpdateStock(@RequestBody Stock stock,Model model, HttpSession session)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			return "YOU DON'T HAVE APPROPRIATE PERMISSIONS";
			//return "home";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ADD BOOKS");
			return "ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ADD BOOKS";
			//return "home";
		}
		
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
			booksrepo.saveBook(book);
		}
		
		model.addAttribute("error","STOCK UPDATED SUCCESSFULLY");
		return "STOCK UPDATED SUCCESSFULLY";
		//return "home";
	}
}
