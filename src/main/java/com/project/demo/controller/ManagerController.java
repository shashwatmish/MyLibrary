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
		session.setAttribute("Dept", manager.getDeptid());
		return ;		
	}
	
	public void logout(HttpSession session) {
		session.removeAttribute("manager");
		session.removeAttribute("Dept");
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
	
	@PostMapping("/registermanager")
	public String SaveManager(@ModelAttribute("Manager") Manager manager, Model model, HttpSession session) 
	{	
		if(is_manager(session) || is_staff(session) || is_student(session)) {
			model.addAttribute("error","ALREADY LOGGED IN. LOGOUT BEFORE REGISTERING");
			return "home";		
		}
		
		int flag = managerrepo.saveManager(manager);
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		model.addAttribute("Managerid",true);
		return "home";
	}
	
	@GetMapping("/getmanager")
	public String ShowManagerById(@RequestParam("id") int id,Model model)
	{
		Manager manager = managerrepo.getManagerById(id);
		if(manager.getManagerid()==-1)
		{
			model.addAttribute("error","MANAGER DOESN'T EXIST");
			return "Managers";
		}
		model.addAttribute("qq",manager);
		return "Managers";
	}
	
	@GetMapping("/getmanagers")
	public String ShowManagers(Model model,HttpSession session)
	{
		if(!is_student(session) && !is_staff(session) && !is_manager(session))
		{
			model.addAttribute("error","KINDLY LOGIN BEFORE VISITING THIS PAGE");
			return "Login";
		}
		
		List<Manager> list=  managerrepo.getAllManagers();
		model.addAttribute("qresult",list);
		return "Managers";
	}
	
	@PostMapping("/deletemanager")
	public String DeleteManagerById(@RequestParam("id") int id,Model model,HttpSession session)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER BEFORE DELETING");
			//return "LOGIN AS A MANAGER BEFORE UPDATING";
			return "home";
		}
		
		Manager cur_manager = (Manager)session.getAttribute("manager");
		int cur_id = cur_manager.getManagerid();
		
		if(id!=cur_id)
		{
			model.addAttribute("error","YOU CAN'T DELETE DETAILS OF OTHER MANAGERS");
			//return "YOU CAN'T DELETE OTHER'S DETAILS";
			return "home";
		}
		
		
		int flag = managerrepo.deleteManagerById(id);
		
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			//return "SOMETHING WENT WRONG, PLEASE TRY AGAIN";
			return "home";
		}
		
		logout(session);
		model.addAttribute("error","DETAILS DELETED SUCCESSFULLY AND YOU WERE LOGGED OUT");
		// return "DETAILS DELETED SUCCESSFULLY AND YOU WERE LOGGED OUT";
		return "home";
	}
	
	@GetMapping("/updatemanager")
	public String UpdateManager()
	{
		return "UpdateManager";
	}
	
	@PostMapping("/updatemanager")
	public String UpdateManager(@ModelAttribute("manager") Manager manager,Model model,HttpSession session)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER BEFORE UPDATING");
			// return "LOGIN AS A MANAGER BEFORE UPDATING";
			return "home";
		}
		
		int id = manager.getManagerid();
		Manager cur_manager = (Manager)session.getAttribute("manager");
		int cur_id = cur_manager.getManagerid();
		
		if(id!=cur_id)
		{
			model.addAttribute("error","YOU CAN'T UPDATE DETAILS OF OTHER MANAGERS");
			// return "YOU CAN'T UPDATE OTHER'S DETAILS";
			return "home";
		}
		
		managerrepo.updateManager(manager);
		logout(session);
		model.addAttribute("error","DETAILS UPDATED SUCCESSFULLY. PLEASE LOGIN AGAIN WITH NEW CREDENTIALS");
		// return "DETAILS UPDATED SUCCESSFULLY";
		return "home";
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
}
