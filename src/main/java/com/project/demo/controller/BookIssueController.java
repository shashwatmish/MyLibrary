package com.project.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.demo.dao.BookIssueRepository;
import com.project.demo.dao.BookRepository;
import com.project.demo.dao.StockRepository;
import com.project.demo.dao.StudentRepository;
import com.project.demo.models.Book;
import com.project.demo.models.BookIssue;
import com.project.demo.models.Manager;
import com.project.demo.models.Stock;
import com.project.demo.models.Student;

@Controller
public class BookIssueController {
	
	@Autowired
	BookIssueRepository bookissuerepo;
	
	@Autowired
	BookRepository bookrepo;
	
	@Autowired
	StockRepository stockrepo;
	
	@Autowired
	StudentRepository studentrepo;
	
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
	
	@PostMapping("/issue/book")
	public @ResponseBody String IssueBook(@ModelAttribute("bookissue") BookIssue bookissue, Model model, HttpSession session, RedirectAttributes r)
	{
		
		if(is_student(session))
		{
			model.addAttribute("error","YOU CAN ONLY ISSUE A BOOK UNDER THE SUPERVISION OF A MANAGER" );
			//return "home";
			return "YOU CAN ONLY ISSUE A BOOK UNDER THE SUPERVISION OF A MANAGER";
		}
		
		if(is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO ISSUE BOOKS ");
			return "NOT AUTHORISED TO ISSUE BOOKS ";
			//return "home";
		}
		
		if(!(is_manager(session)))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE BOOKS DEPARTMENT BEFORE ISSUING");
			return "LOGIN AS A MANAGER OF THE BOOKS DEPARTMENT BEFORE ISSUING";
			//return "home";
		}
		
		Manager manager = (Manager)session.getAttribute("manager");
		int dept = manager.getDeptid();
		
		System.out.print(dept);
		
		if(dept!=101)
		{
			model.addAttribute("error", "ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ISSUE BOOKS");
			return "ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ISSUE BOOKS";
			//return "LoginManager";
		}
			
		int bookid = bookissue.getBookid();
		Book book = bookrepo.getBookById(bookid);
		
		
		if(book.isIsissued()==true)
		{
			model.addAttribute("error","SORRY, THIS BOOK IS ALREADY ISSUED BY SOMEONE");
			return "SORRY, THIS BOOK IS ALREADY ISSUED BY SOMEONE";
			//return "home";
		}
		
		int studentid = bookissue.getStudentid();
		Student student = studentrepo.getStudentById(studentid);
		int total_books = student.getBooksIssued();
		
		if(total_books>=5)
		{
			model.addAttribute("error","YOU HAVE ALREADY ISSUED A LOT OF BOOKS. KINDLY RETURN THEM BEFORE ISSUING NEW BOOKS");
			return "YOU HAVE ALREADY ISSUED A LOT OF BOOKS. KINDLY RETURN THEM BEFORE ISSUING NEW BOOKS";
			// return "home";
		}
		
		total_books++;
		student.setBooksIssued(total_books);
		student = studentrepo.updateStudent(student);
		
		int response = bookissuerepo.IssueBook(bookissue);
		
		if(response!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG. CHECK THE VALUES PROPERLY AND TRY AGAIN");
			return "SOMETHING WENT WRONG. CHECK THE VALUES PROPERLY AND TRY AGAIN";
			//return "home";
		}
		
		
		book.setIs_issued(true);
		book = bookrepo.updateBook(book);
		
		Stock stock = stockrepo.getStock(book.getTitle(), book.getAuthor(), book.getPublications());
		int booksavailable = stock.getBooksavailable();
		int booksissued = stock.getBooksissued();
		
		booksavailable--;
		booksissued++;
		
		stock.setBooksavailable(booksavailable);
		stock.setBooksissued(booksissued);
		
		stock = stockrepo.updateStock(stock);
		
		
		model.addAttribute("error","BOOK ISSUED SUCCESSFULLY");
		return "BOOK ISSUED SUCCESSFULLY";
		//return "home";
	}
	
	@DeleteMapping("/return/book")
	public @ResponseBody String ReturnBook(@RequestParam("studentid") int studentid, @RequestParam("bookid") int bookid, Model model, HttpSession session)
	{
		if(!is_student(session) && !is_staff(session) && !is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE BOOKS DEPARTMENT BEFORE RETURNING");
			return "LOGIN AS A MANAGER OF THE BOOKS DEPARTMENT BEFORE RETURNING";
			// return "home";
		}
		
		if(is_student(session))
		{
			model.addAttribute("error","YOU CAN ONLY RETURN A BOOK UNDER THE SUPERVISION OF A MANAGER" );
			return "YOU CAN ONLY RETURN A BOOK UNDER THE SUPERVISION OF A MANAGER";
			//return "home";
		}
		
		if(is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO RETURN BOOKS ");
			return "NOT AUTHORISED TO RETURN BOOKS ";
			//return "home";
		}
		
		if(!is_manager(session))
		{
			model.addAttribute("error", "LOGIN AS A MANAGER BEFORE RETURNING BOOKS FROM STUDENTS");
			return "LOGIN AS A MANAGER BEFORE RETURNING BOOKS FROM STUDENTS";
			//return "LoginManager";
		}
		
		Manager manager = (Manager) session.getAttribute("manager");
		int dept = manager.getDeptid();
		
		if(dept!=101)
		{
			model.addAttribute("error", "ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN RETURN BOOKS");
			return "ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN RETURN BOOKS";
			//return "LoginManager";
		}
		
		int response = bookissuerepo.ReturnBook(studentid, bookid);
		
		if(response!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG. CHECK THE VALUES PROPERLY AND TRY AGAIN");
			return "SOMETHING WENT WRONG. CHECK THE VALUES PROPERLY AND TRY AGAIN";
			//return "home";
		}
		
		Book book = bookrepo.getBookById(bookid);
		book.setIs_issued(false);
		book = bookrepo.updateBook(book);
		
		Stock stock = stockrepo.getStock(book.getTitle(), book.getAuthor(), book.getPublications());
		int booksavailable = stock.getBooksavailable();
		int booksissued = stock.getBooksissued();
		
		booksavailable++;
		booksissued--;
		
		stock.setBooksavailable(booksavailable);
		stock.setBooksissued(booksissued);
		
		stock = stockrepo.updateStock(stock);
		
		Student student = studentrepo.getStudentById(studentid);
		int total_books = student.getBooksIssued();
		total_books--;
		student.setBooksIssued(total_books);
		student = studentrepo.updateStudent(student);
		
		model.addAttribute("error","BOOK RETURNED SUCCESSFULLY");
		return "BOOK RETURNED SUCCESSFULLY";
		//return "home";
	}
}
