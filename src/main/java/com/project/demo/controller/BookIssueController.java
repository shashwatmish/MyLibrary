package com.project.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/issue/book")
	public String IssueBook()
	{
		return "IssueBook";
	}
	
	@GetMapping("/return/book")
	public String ReturnBook()
	{
		return "ReturnBook";
	}
	
	@PostMapping("/issue/book")
	public String IssueBook(@ModelAttribute("bookissue") BookIssue bookissue, Model model, HttpSession session, RedirectAttributes r)
	{
		
		if(is_student(session))
		{
			model.addAttribute("error","YOU CAN ONLY ISSUE A BOOK UNDER THE SUPERVISION OF A MANAGER" );
			return "home";
		}
		
		if(is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO ISSUE BOOKS ");
			return "home";
		}
		
		if(!(is_manager(session)))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE BOOKS OR STUDENTAFFAIRS DEPARTMENT BEFORE ISSUING");
			return "home";
		}
		
		Manager manager = (Manager)session.getAttribute("manager");
		int dept = manager.getDeptid();
		
		
		if(dept!=101 && dept!=103)
		{
			model.addAttribute("error", "ONLY MANAGERS OF THE BOOKS OR STUDENTAFFAIRS DEPARTMENT CAN ISSUE BOOKS");
			return "home";
		}
			
		int bookid = bookissue.getBookid();
		Book book = bookrepo.getBookById(bookid);
		
		
		if(book.getIsissued()==true)
		{
			model.addAttribute("error","SORRY, THIS BOOK IS ALREADY ISSUED BY SOMEONE");
			return "home";
		}
		
		int studentid = bookissue.getStudentid();
		Student student = studentrepo.getStudentById(studentid);
		int total_books = student.getBooksissued();
		
		//System.out.print(total_books);
		
		if(total_books>=5)
		{
			model.addAttribute("error","THIS STUDENT HAS ALREADY BORROWED A LOT OF BOOKS. KINDLY ASK HIM TO RETURN THEM BEFORE BORROWING NEW BOOKS");
			return "home";
		}
		
		int response = bookissuerepo.IssueBook(bookissue);
		
		if(response!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG. CHECK THE VALUES PROPERLY AND TRY AGAIN");
			return "home";
		}
		
		total_books++;
		student.setBooksissued(total_books);
		studentrepo.updateStudent(student);
		
		student = studentrepo.getStudentById(studentid);
		
		
		book.setIsissued(true);
		bookrepo.updateBook(book);
		book = bookrepo.getBookById(bookid);
		
		Stock stock = stockrepo.getStock(book.getTitle(), book.getAuthor(), book.getPublications());
		int booksavailable = stock.getBooksavailable();
		int booksissued = stock.getBooksissued();
		
		booksavailable--;
		booksissued++;
		
		stock.setBooksavailable(booksavailable);
		stock.setBooksissued(booksissued);
		
		stock = stockrepo.updateStock(stock);
		
		
		model.addAttribute("error","BOOK ISSUED SUCCESSFULLY");
		//return "BOOK ISSUED SUCCESSFULLY";
		return "home";
	}
	
	@PostMapping("/return/book")
	public String ReturnBook(@RequestParam("studentid") int studentid, @RequestParam("bookid") int bookid, Model model, HttpSession session)
	{
		if(!is_student(session) && !is_staff(session) && !is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE BOOKS DEPARTMENT BEFORE RETURNING");
			//return "LOGIN AS A MANAGER OF THE BOOKS DEPARTMENT BEFORE RETURNING";
			return "home";
		}
		
		if(is_student(session))
		{
			model.addAttribute("error","YOU CAN ONLY RETURN A BOOK UNDER THE SUPERVISION OF A MANAGER" );
			//return "YOU CAN ONLY RETURN A BOOK UNDER THE SUPERVISION OF A MANAGER";
			return "home";
		}
		
		if(is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO RETURN BOOKS ");
			//return "NOT AUTHORISED TO RETURN BOOKS ";
			return "home";
		}
		
		if(!is_manager(session))
		{
			model.addAttribute("error", "LOGIN AS A MANAGER BEFORE RETURNING BOOKS FROM STUDENTS");
			//return "LOGIN AS A MANAGER BEFORE RETURNING BOOKS FROM STUDENTS";
			return "LoginManager";
		}
		
		Manager manager = (Manager) session.getAttribute("manager");
		int dept = manager.getDeptid();
		
		if(dept!=101 && dept!=103)
		{
			model.addAttribute("error", "ONLY MANAGERS OF THE BOOKS OR STUDENTAFFAIRS DEPARTMENT CAN RETURN BOOKS");
			//return "ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN RETURN BOOKS";
			return "LoginManager";
		}
		
		int response = bookissuerepo.ReturnBook(studentid, bookid);
		
		if(response!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG. CHECK THE VALUES PROPERLY AND TRY AGAIN");
			//return "SOMETHING WENT WRONG. CHECK THE VALUES PROPERLY AND TRY AGAIN";
			return "home";
		}
		
		Book book = bookrepo.getBookById(bookid);
		book.setIsissued(false);
		bookrepo.updateBook(book);
		book = bookrepo.getBookById(bookid);
		
		Stock stock = stockrepo.getStock(book.getTitle(), book.getAuthor(), book.getPublications());
		int booksavailable = stock.getBooksavailable();
		int booksissued = stock.getBooksissued();
		
		booksavailable++;
		booksissued--;
		
		stock.setBooksavailable(booksavailable);
		stock.setBooksissued(booksissued);
		
		stock = stockrepo.updateStock(stock);
		
		Student student = studentrepo.getStudentById(studentid);
		int total_books = student.getBooksissued();
		total_books--;
		student.setBooksissued(total_books);
		
		studentrepo.updateStudent(student);
		
		model.addAttribute("error","BOOK RETURNED SUCCESSFULLY");
		//return "BOOK RETURNED SUCCESSFULLY";
		return "home";
	}
}
