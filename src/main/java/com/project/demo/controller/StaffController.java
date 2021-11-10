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

import com.project.demo.dao.StaffRepository;

import com.project.demo.models.Staff;

@Controller
public class StaffController {
	
	@Autowired
	StaffRepository staffrepo;
	
	public void login(HttpSession session,Staff staff) {
		session.setAttribute("staff", staff);
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
	
	
	@PostMapping("/registerstaff")
	public String SaveStaff(@ModelAttribute("staff") Staff staff, Model model, HttpSession session) {
		if(is_manager(session) || is_staff(session) || is_student(session)) {
			model.addAttribute("error","ALREADY LOGGED IN. LOGOUT BEFORE REGISTERING");
			return "home";		
		}
		
		staff =  staffrepo.saveStaff(staff);
		model.addAttribute("Staffid",true);
		return "home";
	}
	
	@GetMapping("/getstaff/{id}")
	public @ResponseBody Staff getStaffById(@PathVariable("id") int id)
	{
		return staffrepo.getStaffById(id);
	}
	
	@GetMapping("/getstaffs")
	public @ResponseBody List<Staff> getAllStaff()
	{
		return staffrepo.getAllStaffs();
	}
	
	@PutMapping("/updatestaff")
	public @ResponseBody Staff UpdateStaff(@RequestBody Staff staff)
	{
		return staffrepo.updateStaff(staff);
	}
	
	@DeleteMapping("/deletestaff/{id}")
	public @ResponseBody String Deletestaff(@PathVariable("id") int id)
	{
		return staffrepo.deleteStaffById(id);
	}
	
	@PostMapping("/staff/login")
	public String Loginprocess(@RequestParam("id") int id, 
			@RequestParam("password") String password,HttpSession session,RedirectAttributes r, Model model) {
		if(is_manager(session) || is_staff(session) || is_student(session)) {
			model.addAttribute("error","ALREADY LOGGED IN !!");
			return "LoginStaff";	
		}
		
		Staff staff= new Staff();
		staff.setStaffid(id);
		staff.setPassword(password);
		
		Staff dummyLogin = new Staff();
		dummyLogin = staffrepo.getStaffById(id);
		
		if(dummyLogin.getStaffid()==-1)
		{
			model.addAttribute("error","NO SUCH STAFF EXISTS !!");
			return "LoginStaff";
		}
		
		String cur_password = dummyLogin.getPassword();
		
		int l1 = password.length();
		int l2 = cur_password.length();
		
		if(l1!=l2)
		{
			model.addAttribute("error","WRONG CREDENTIALS !!");
			return "LoginStaff";
		}
		
		for(int i=0;i<l1;i++)
		{
			int a = (int)password.charAt(i);
			int b = (int)cur_password.charAt(i);
			
			if(a!=b)
			{
				model.addAttribute("error","WRONG CREDENTIALS !!");
				return "LoginStaff";
			}
		}
		
		staff = dummyLogin;
		login(session,staff);
		model.addAttribute("error","SUCCESS !!");

		return "home";
	}
	
	@GetMapping("/staff/logout")
	public String logout(HttpSession session,Model model) {

		session.removeAttribute("staff");
		session.invalidate();
		model.addAttribute("error","LOGGED OUT SUCCESSFULLY !!");

		return "home";
	}
}
