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

import com.project.demo.dao.ManagerRepository;
import com.project.demo.models.Manager;

@Controller
public class ManagerController {
	
	@Autowired
	ManagerRepository managerrepo;
	
	public void login(HttpSession session,Manager manager) {
		session.setAttribute("manager", manager);
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
	
	@PostMapping("/registermanager")
	public String SaveManager(@ModelAttribute("manager") Manager manager, Model model, HttpSession session) 
	{	
		if(is_manager(session) || is_staff(session) || is_student(session)) {
			model.addAttribute("error","ALREADY LOGGED IN. LOGOUT BEFORE REGISTERING");
			return "home";		
		}
		
		manager =  managerrepo.saveManager(manager);
		model.addAttribute("Managerid",true);
		return "home";
	}
	
	@GetMapping("/getmanager/{id}")
	public @ResponseBody Manager ShowManagerById(@PathVariable("id") int id)
	{
		return managerrepo.getManagerById(id);
	}
	
	@GetMapping("/getmanagers")
	public @ResponseBody List<Manager> ShowManagers()
	{
		return managerrepo.getAllManagers();
	}
	
	@DeleteMapping("/deletemanager/{id}")
	public @ResponseBody String DeleteManagerById(@PathVariable("id") int id)
	{
		return managerrepo.deleteManagerById(id);
	}
	
	@PutMapping("/updatemanager")
	public @ResponseBody Manager UpdateManager(@RequestBody Manager manager)
	{
		return managerrepo.updateManager(manager);
	}
	
	@PostMapping("/manager/login")
	public String Loginprocess(@RequestParam("id") int id, 
			@RequestParam("password") String password,HttpSession session,RedirectAttributes r, Model model) {
		if(is_manager(session) || is_staff(session) || is_student(session)) {
			model.addAttribute("error","ALREADY LOGGED IN !!");
			return "LoginManager";		
		}

		Manager manager= new Manager();
		manager.setManagerid(id);
		manager.setPassword(password);
		
		Manager dummyLogin = new Manager();
		dummyLogin = managerrepo.getManagerById(id);
		
		if(dummyLogin.getManagerid()==-1)
		{
			model.addAttribute("error","NO SUCH MANAGER EXISTS!!");
			return "LoginManager";	
		}
		
		String cur_password = dummyLogin.getPassword();
		
		int l1 = password.length();
		int l2 = cur_password.length();
		
		if(l1!=l2)
		{
			model.addAttribute("error","WRONG CREDENTIALS !!");
			return "LoginManager";	
		}
		
		for(int i=0;i<l1;i++)
		{
			int a = (int)password.charAt(i);
			int b = (int)cur_password.charAt(i);
			
			if(a!=b)
			{
				model.addAttribute("error","WRONG CREDENTIALS !!");
				return "LoginManager";	
			}
		}
		
		manager = dummyLogin;
		
		login(session,manager);

		model.addAttribute("error","SUCCESS !!");
		return "home";	
	}
	
	@GetMapping("/manager/logout")
	public @ResponseBody String logout(HttpSession session,Model model) {

		session.removeAttribute("manager");
		session.invalidate();
		model.addAttribute("error","LOGGED OUT SUCCESSFULLY !!");

		return "home";
	}
}
