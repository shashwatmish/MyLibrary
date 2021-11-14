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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.dao.BookRepository;
import com.project.demo.dao.StockRepository;
import com.project.demo.models.Book;
import com.project.demo.models.Manager;
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
	
	@GetMapping("/addstock")
	public String AddStock()
	{
		return "AddStock";
	}
	
	@GetMapping("/updatestock")
	public String UpdateStock()
	{
		return "UpdateStock";
	}
	
	@GetMapping("/deletestock")
	public String DeleteStock()
	{
		return "DeleteStock";
	}
	
	
	@PostMapping("/addstock")
	public String AddStock(@ModelAttribute("stock") Stock stock,Model model, HttpSession session)
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
		
		int flag =  stockrepo.saveStock(stock);
		
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		int cnt = stock.getBooksavailable();
		for(int i=1;i<=cnt;i++)
		{
			Book book = new Book();
			book.setShelfid(101);
			book.setLanguage("English");
			book.setPublications(stock.getPublications());
			book.setAuthor(stock.getAuthor());
			book.setTitle(stock.getTitle());
			int done = booksrepo.saveBook(book);
		}
		
		model.addAttribute("error","STOCK ADDED SUCCESSFULLY");
		return "home";
	}
	
	@GetMapping("/getstocks")
	public String GetStock(Model model,HttpSession session)
	{
		if(!is_student(session) && !is_staff(session) && !is_manager(session))
		{
			model.addAttribute("error","KINDLY LOGIN BEFORE VISITING THIS PAGE");
			return "Login";
		}
		
		if(is_student(session) || is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO SEE STOCK DETAILS");
			//return "NOT AUTHORISED TO SEE RETAILER DETAILS";
			return "home";
		}
		
		if(!is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE BOOKS DEPARTMENT BEFORE VIEWING THIS PAGE");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE";
			return "Login";		
		}
		
		Manager manager = (Manager)session.getAttribute("manager");
		int cur_id = manager.getDeptid();
		
		if(cur_id!=101)
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE BOOKS DEPARTMENT BEFORE VIEWING THIS PAGE");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE";
			return "Login";
		}
		
		List <Stock> list = stockrepo.getAllStocks();
		model.addAttribute("qresult",list);
		return "Stocks";
	}
	
	@GetMapping("/getstock")
	public String GetStock(
			@RequestParam("title") String title, 
			@RequestParam("author") String author, 
			@RequestParam("publications") String publications,
			Model model, HttpSession session
			)
	{
		
		 Stock stock =  stockrepo.getStock(title, author, publications);
		 model.addAttribute("q",stock);
		 return "Stock";
	}
	
	@PostMapping("/deletestock")
	public String DeleteStock(
			@RequestParam("title") String title, 
			@RequestParam("author") String author, 
			@RequestParam("publications") String publications,
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
		
		Stock stock = stockrepo.getStock(title, author, publications);
		
		if(stock.getBooksavailable()>0 || stock.getBooksissued()>0)
		{
			model.addAttribute("error","CAN'T DELETE THIS STOCK AS SOME BOOKS ARE STILL THERE IN THE LIBRARY");
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
	
	@PostMapping("/updatestock")
	public String UpdateStock(@ModelAttribute("stock") Stock stock,Model model, HttpSession session)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			//return "YOU DON'T HAVE APPROPRIATE PERMISSIONS";
			return "home";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ADD BOOKS");
			//return "ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ADD BOOKS";
			return "home";
		}
		
		Stock intialStock = stockrepo.getStock(stock.getTitle(),stock.getAuthor(),stock.getPublications());
		if(intialStock.getBooksavailable()==-1)
		{
			model.addAttribute("error","STOCK DETAILS DOESN'T EXIST");
			return "home";
			
		}
		
		int prev = intialStock.getBooksavailable();
		Stock updatedStock =  stockrepo.updateStockNew(stock);
		int cur = updatedStock.getBooksavailable();
		
		for(int i=1;i<=cur-prev;i++)
		{
			Book book = new Book();
			book.setShelfid(101);
			book.setLanguage("English");
			book.setPublications(updatedStock.getPublications());
			book.setAuthor(updatedStock.getAuthor());
			book.setTitle(updatedStock.getTitle());
			booksrepo.saveBook(book);
		}
		
		model.addAttribute("error","STOCK UPDATED SUCCESSFULLY");
		return "home";
	}
}
