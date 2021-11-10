package com.project.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
