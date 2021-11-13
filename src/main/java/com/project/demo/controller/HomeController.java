package com.project.demo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	/*@RequestMapping("/")
	public ModelAndView home(@RequestParam("name") String myName)
	{
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("Name",myName);
		mv.setViewName("home.jsp");
		return mv;
	}*/
	
	public void logout(HttpSession session) {
		// session.removeAttribute("manager");
		// session.removeAttribute("Dept");
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
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/login")
	public String LoginMain()
	{
		return "Login";
	}
	
	@GetMapping("/register")
	public String RegisterMain()
	{
		return "Register";
	}
	
	@GetMapping("/staff/register")
	public String RegisterStaff()
	{
		return "RegisterStaff";
	}
	
	@GetMapping("/student/register")
	public String RegisterStudent()
	{
		return "RegisterStudent";
	}
	
	@GetMapping("/manager/register")
	public String RegisterManager()
	{
		return "RegisterManager";
	}
	
	
	@GetMapping("/student/login")
	public String LoginStudent()
	{
		return "LoginStudent";
	}
	
	@GetMapping("/staff/login")
	public String LoginStaff()
	{
		return "LoginStaff";
	}
	
	@GetMapping("/manager/login")
	public String LoginManager()
	{
		return "LoginManager";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,Model model) {
		
		if(!is_manager(session) && !is_student(session) && !is_staff(session))
		{
			model.addAttribute("error","YOU ARE ALREADY LOGGED OUT");
			return "home";
		}

		logout(session);
		model.addAttribute("error","LOGGED OUT SUCCESSFULLY !!");

		return "home";
	}
}
