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
import com.project.demo.models.Stock;

@Controller
public class BookController {
	
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
	BookRepository bookrepo;
	
	@Autowired
	StockRepository stockrepo;
	
	@GetMapping("/getbooks")
	public String GetBook(HttpSession session, Model model)
	{
		if(!is_student(session) && !is_staff(session) && !is_manager(session))
		{
			model.addAttribute("error","KINDLY LOGIN BEFORE VISITING THIS PAGE");
			return "Login";
		}
		
		List <Book> list = bookrepo.getAllBooks();
		model.addAttribute("qresult",list);
		return "Books";
	}
	
	@PostMapping("/addbook")
	public String AddBook(HttpSession session, Model model)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			return "Books";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ADD BOOKS");
			return "Books";
		}
		
		model.addAttribute("error","YOU CAN VISIT THE STOCK PAGE TO ADD BOOKS");
		return "Books";
	}
	
	
	@GetMapping("/getbook")
	public String GetBook(@RequestParam("id") int id, Model model)
	{
		Book book = bookrepo.getBookById(id);
		if(book.getBookid()==-1)
		{
			model.addAttribute("error","BOOK DOESN'T EXIST");
			return "Books";
		}
		model.addAttribute("qq",book);
		return "Books";
	}
	
	@PostMapping("/deletebook")
	public String DeleteBook(@RequestParam("id") int id, HttpSession session , Model model)
	{	
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			return "home";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN DELETE BOOKS");
			return "home";
		}
		
		Book book = bookrepo.getBookById(id);
		if(book.getIsissued()==true)
		{
			model.addAttribute("error","CAN'T DELETE THIS BOOK AS IT IS ISSUED BY SOMEONE");
			return "home";
		}
		
		int flag = bookrepo.deleteBookById(id);
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		String title = book.getTitle();
		String author = book.getAuthor();
		String publications = book.getPublications();
		
		Stock stock = stockrepo.getStock(title, author, publications);
		int booksavailable = stock.getBooksavailable();
		booksavailable--;
		stock.setBooksavailable(booksavailable);
		
		stockrepo.updateStockNew(stock);
		
		model.addAttribute("error","Book deleted successfully");
		return "home";
	}
	
	@GetMapping("/updatebook")
	public String UpdateBook()
	{
		return "UpdateBook";
	}
	
	@PostMapping("/updatebook")
	public String UpdateBook(@ModelAttribute("book") Book book,Model model, HttpSession session)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			return "home";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN UPDATE BOOK DETAILS");
			return "home";
		}
		
		System.out.println(book);
		int flag = bookrepo.updateBookNew(book);
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		model.addAttribute("error","UPDATION SUCCESSFUL");
		return "home";
	}

}
