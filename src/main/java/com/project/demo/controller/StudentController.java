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
	public String SaveStudent(@ModelAttribute("student") Student student,Model model,HttpSession session)
	{
		if(is_manager(session) || is_staff(session) || is_student(session)) {
			model.addAttribute("error","ALREADY LOGGED IN. LOGOUT BEFORE REGISTERING");
			return "home";		
		}
		
		student =  studentrepo.saveStudent(student);
		model.addAttribute("Studentid",true);
		return "home";
	}
	
	@GetMapping("/getstudent/{id}")
	public @ResponseBody Student ShowStudent(@PathVariable("id") int id)
	{
		return studentrepo.getStudentById(id);
	}
	
	@GetMapping("/getstudents")
	public @ResponseBody List<Student> ShowStudents()
	{
		return studentrepo.getAllStudents();
	}
	
	@PutMapping("/updatestudent")
	public @ResponseBody Student UpdateStudent(@RequestBody Student student)
	{
		return studentrepo.updateStudent(student);
	}
	
	@DeleteMapping("/deletestudent/{id}")
	public @ResponseBody String Deletestudent(@PathVariable("id") int id)
	{
		return studentrepo.deleteStudentById(id);
	}
	
	@PostMapping("/student/login")
	public String Loginprocess(@RequestParam("id") int id, 
			@RequestParam("password") String password,HttpSession session,RedirectAttributes r, Model model) {
		if(is_student(session) || is_manager(session) || is_staff(session)) {
			model.addAttribute("error","ALREADY LOGGED IN !!");
			return "LoginStudent";	
			
		} 
		
		Student student= new Student();
		student.setStudentId(id);
		student.setPassword(password);
		
		Student dummyLogin = new Student();
		dummyLogin = studentrepo.getStudentById(id);
		
		if(dummyLogin.getStudentId()==-1)
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
	
	@GetMapping("/student/logout")
	public String logout(HttpSession session,Model model) {

		session.removeAttribute("student");
		session.invalidate();
		model.addAttribute("error","LOGGED OUT SUCCESSFULLY !!");

		return "home";
	}
}