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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.demo.dao.StudentRepository;
import com.project.demo.models.Student;

@Controller
public class StudentController {
	
	@Autowired
	StudentRepository studentrepo;
	
	public void login(HttpSession session,Student student) {
		session.setAttribute("student", student);
		return ;		
	}
	
	public void logout(HttpSession session) {
		session.removeAttribute("student");
		session.invalidate();
	}
	
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
	
	
	@PostMapping("/registerstudent")
	public String SaveStudent(@ModelAttribute("Student") Student student,Model model,HttpSession session)
	{
		if(is_manager(session) || is_staff(session) || is_student(session)) {
			model.addAttribute("error","ALREADY LOGGED IN. LOGOUT BEFORE REGISTERING");
			return "home";		
		}
		
		int flag =  studentrepo.saveStudent(student);
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		model.addAttribute("Studentid",true);
		return "home";
	}
	
	@GetMapping("/getstudent")
	public String ShowStudent(@RequestParam("id") int id,Model model)
	{
		Student student = studentrepo.getStudentById(id);
		if(student.getStudentid()==-1)
		{
			model.addAttribute("error","STUDENT DOESN'T EXISTS");
			return "Students";
		}
		model.addAttribute("qq",student);
		return "Students";
	}
	
	@GetMapping("/getstudents")
	public String ShowStudents(Model model,HttpSession session)
	{
		if(!is_student(session) && !is_staff(session) && !is_manager(session))
		{
			model.addAttribute("error","KINDLY LOGIN BEFORE VISITING THIS PAGE");
			return "Login";
		}
		
		List<Student> list = studentrepo.getAllStudents();
		model.addAttribute("qresult",list);
		return "Students";
	}
	
	@GetMapping("/updatestudent")
	public String UpdateStudent()
	{
		return "UpdateStudent";
	}
	
	@PostMapping("/updatestudent")
	public String UpdateStudent(@ModelAttribute("student") Student student,Model model, HttpSession session)
	{
		if(is_student(session) || is_staff(session))
		{
			model.addAttribute("error","LOGIN AS A STUDENT OR A MANAGER OF THE STUDENTAFFAIRS DEPARTMENT BEFORE UPDATING");
			return "LOGIN AS A STUDENT OR A MANAGER OF THE STUDENTAFFAIRS DEPARTMENT BEFORE UPDATING";
			// return "home";
		}
		
		if(is_manager(session))
		{
			int dept = (int)session.getAttribute("Dept");
			if(dept!=103)
			{
				model.addAttribute("error","LOGIN AS A STUDENT OR MANAGER OF THE STUDENTAFFAIRS DEPARTMENT BEFORE UPDATING");
				//return "LOGIN AS A STUDENT OR MANAGER OF THE STUDENTAFFAIRS DEPARTMENT BEFORE UPDATING";
				return "home";
			}
		}
		
		int flag = studentrepo.updateStudentNew(student);
		if(flag!=1)
		{
			model.addAttribute("error", "SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			//return "SOMETHING WENT WRONG, PLEASE TRY AGAIN";
			return "home";
		}
		
		model.addAttribute("error","SUCCESS UPDATION OF DETAILS");
		//return "SUCCESSFUL UPDATION OF DETAILS";
		return "home";
	}
	
	@PostMapping("/deletestudent")
	public String Deletestudent(@RequestParam("id") int id,Model model,HttpSession session)
	{
		if(is_student(session) || is_staff(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE STUDENTAFFAIRS DEPARTMENT BEFORE DELETING");
			return "home";
		}
		
		if(is_manager(session))
		{
			int dept = (int)session.getAttribute("Dept");
			if(dept!=103)
			{
				model.addAttribute("error","LOGIN AS A MANAGER OF THE STUDENTAFFAIRS DEPARTMENT BEFORE DELETING");
				return "home";
			}
		}
		
		Student student = studentrepo.getStudentById(id);
		
		int books = student.getBooksissued();
		
		if(books>0)
		{
			model.addAttribute("error","THIS STUDENT HAS ISSUED SOME BOOKS. ASK HIM/HER TO RETURN THE BOOKS FIRST");
			return "home";
		}
		
		int flag = studentrepo.deleteStudentById(id);
		if(flag!=1)
		{
			model.addAttribute("error", "SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
			
		model.addAttribute("error","SUCCESSFUL DELETION OF DETAILS");
		//"SUCCESSFUL DELETION OF DETAILS";
		return "home";
	}
	
	@PostMapping("/student/login")
	public String Loginprocess(@RequestParam("id") int id, 
			@RequestParam("password") String password,HttpSession session,RedirectAttributes r, Model model) {
		if(is_student(session) || is_manager(session) || is_staff(session)) {
			model.addAttribute("error","ALREADY LOGGED IN !!");
			return "LoginStudent";	
			
		} 
		
		Student student= new Student();
		student.setStudentid(id);
		student.setPassword(password);
		
		Student dummyLogin = new Student();
		dummyLogin = studentrepo.getStudentById(id);
		
		if(dummyLogin.getStudentid()==-1)
		{
			model.addAttribute("error","NO SUCH STUDENT EXISTS !!");
			return "LoginStudent";
		}
		
		String cur_password = dummyLogin.getPassword();
		
		int l1 = password.length();
		int l2 = cur_password.length();
		
		if(l1!=l2)
		{
			model.addAttribute("error","WRONG CREDENTIALS !!!");
			return "LoginStudent";
		}
		
		for(int i=0;i<l1;i++)
		{
			int a = (int)password.charAt(i);
			int b = (int)cur_password.charAt(i);
			
			if(a!=b)
			{
				model.addAttribute("error","WRONG CREDENTIALS !!!");
				return "LoginStudent";
			}
		}
		
		student = dummyLogin;
		login(session,student);

		model.addAttribute("error","SUCCESS !!!");
		return "home";
	}
}