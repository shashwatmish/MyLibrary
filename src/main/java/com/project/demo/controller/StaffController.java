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
import com.project.demo.models.Student;

@Controller
public class StaffController {
	
	@Autowired
	StaffRepository staffrepo;
	
	public void login(HttpSession session,Staff staff) {
		session.setAttribute("staff", staff);
		return ;		
	}
	
	public void logout(HttpSession session) {
		session.removeAttribute("staff");
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
	
	
	@PostMapping("/registerstaff")
	public String SaveStaff(@ModelAttribute("Staff") Staff staff, Model model, HttpSession session) {
		if(is_manager(session) || is_staff(session) || is_student(session)) {
			model.addAttribute("error","ALREADY LOGGED IN. LOGOUT BEFORE REGISTERING");
			return "home";		
		}
		
		int flag =  staffrepo.saveStaff(staff);
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		model.addAttribute("Staffid",true);
		return "home";
	}
	
	@GetMapping("/getstaff")
	public String getStaffById(@RequestParam("id") int id, Model model) 
	{
		Staff staff = staffrepo.getStaffById(id);
		if(staff.getStaffid()==-1)
		{
			model.addAttribute("error","NO SUCH STAFF EXISTS");
			return "Staffs";
		}
		model.addAttribute("qq",staff);
		return "Staffs";
	}
	
	@GetMapping("/getstaffs")
	public String getAllStaff(Model model,HttpSession session)
	{
		if(!is_student(session) && !is_staff(session) && !is_manager(session))
		{
			model.addAttribute("error","KINDLY LOGIN BEFORE VISITING THIS PAGE");
			return "Login";
		}
		
		List <Staff> list =  staffrepo.getAllStaffs();
		model.addAttribute("qresult",list);
		return "Staffs";
	}
	
	@GetMapping("/updatestaff")
	public String UpdateStaff()
	{
		return "UpdateStaff";
	}
	
	@PostMapping("/updatestaff")
	public String UpdateStaff(@ModelAttribute("staff") Staff staff, Model model,HttpSession session)
	{
		if(is_staff(session) || is_student(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE STAFFAFFAIRS DEPARTMENT BEFORE UPDATING");
			//return "LOGIN AS A STAFF MEMBER OR A MANAGER OF THE STAFFAFFAIRS DEPARTMENT BEFORE UPDATING";
			return "home";
		}
		
		if(is_manager(session))
		{
			int dept = (int)session.getAttribute("Dept");
			if(dept!=104)
			{
				model.addAttribute("error","LOGIN AS A MANAGER OF THE STAFFAFFAIRS DEPARTMENT BEFORE UPDATING");
				//return "LOGIN AS A MANAGER OF THE STAFFAFFAIRS DEPARTMENT BEFORE UPDATING";
				return "home";
			}
		}
		
		int flag = staffrepo.updateStaffNew(staff);
		System.out.println(staff.getStaffid());
		if(flag!=1)
		{
			model.addAttribute("error", "SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			//return "SOMETHING WENT WRONG, PLEASE TRY AGAIN";
			return "home";
		}
		
		model.addAttribute("error","SUCCESSFUL UPDATION OF DETAILS");
		return "home";
	}
	
	@PostMapping("/deletestaff")
	public String Deletestaff(@RequestParam("id") int id,Model model,HttpSession session)
	{
		if(is_staff(session) || is_student(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE STAFFAFFAIRS DEPARTMENT BEFORE DELETING");
			//return "LOGIN AS A STAFF MEMBER OR A MANAGER OF THE STAFFAFFAIRS DEPARTMENT BEFORE DELETING";
			return "home";
		}
		
		if(is_manager(session))
		{
			int dept = (int)session.getAttribute("Dept");
			if(dept!=104)
			{
				model.addAttribute("error","LOGIN AS A MANAGER OF THE STAFFAFFAIRS DEPARTMENT BEFORE DELETING");
				//return "LOGIN AS A STAFF MEMBER OR MANAGER OF THE STAFFAFFAIRS DEPARTMENT BEFORE DELETING";
				return "home";
			}
		}
		
		int flag = staffrepo.deleteStaffById(id);
		if(flag!=1)
		{
			model.addAttribute("error", "SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			//return "SOMETHING WENT WRONG, PLEASE TRY AGAIN";
			return "home";
		}
		
		model.addAttribute("error","SUCCESSFUL DELETION OF DETAILS");
		// return "SUCCESSFUL DELETION OF DETAILS";
		return "home";
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
}
