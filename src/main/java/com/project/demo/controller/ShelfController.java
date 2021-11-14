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


import com.project.demo.dao.ShelfRepository;
import com.project.demo.dao.StaffRepository;
import com.project.demo.models.Shelf;
import com.project.demo.models.Staff;

@Controller
public class ShelfController {
	
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
	
	@Autowired
	ShelfRepository shelfrepo;
	
	@Autowired
	StaffRepository staffrepo;
	
	@GetMapping("/addshelf")
	public String SaveShelf()
	{
		return "AddShelf";
	}
	
	@GetMapping("/updateshelf")
	public String UpdateShelf()
	{
		return "UpdateShelf";
	}
	
	@PostMapping("/addshelf")
	public String SaveShelf(@ModelAttribute("shelf") Shelf shelf,Model model,HttpSession session) {
		
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			return "home";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN ADD A SHELF");
			return "home";
		}
		
		int flag = shelfrepo.saveShelf(shelf);
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		int staffid = shelf.getHandler();
		Staff staff = staffrepo.getStaffById(staffid);
		
		int handles = staff.getHandlesshelf();
		handles++;
		staff.setHandlesshelf(handles);
		staffrepo.updateStaff(staff);
		
		model.addAttribute("error","SHELF ADDED SUCCESSFULLY");
		return "home";
	}
	
	@GetMapping("/getshelf")
	public String ShowShelfById(@RequestParam("id") int id,Model model)
	{
		Shelf shelf = shelfrepo.getShelfById(id);
		if(shelf.getShelfid()==-1)
		{
			model.addAttribute("error","SHELF DOESN'T EXIST");
			return "Shelf"; 
		}
		model.addAttribute("qq",shelf);
		return "Shelf";
	}
	
	@GetMapping("/getshelves")
	public String ShowShelves(Model model,HttpSession session)
	{
		if(!is_student(session) && !is_staff(session) && !is_manager(session))
		{
			model.addAttribute("error","KINDLY LOGIN BEFORE VISITING THIS PAGE");
			return "Login";
		}
		
		List <Shelf> list = shelfrepo.getAllShelves();
		model.addAttribute("qresult",list);
		return "Shelf";
	}
	
	@PostMapping("/deleteshelf")
	public String DeleteShelfById(@RequestParam("id") int id, Model model,HttpSession session)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			return "home";
		}
		
		if(id==101)
		{
			model.addAttribute("error","THIS SHELF IS RESERVE AND CAN'T BE DELETED");
			return "home";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN UPDATE DETAILS OF A SHELF");
			return "home";
		}
		
		Shelf shelf = shelfrepo.getShelfById(id);
		int flag = shelfrepo.deleteShelfById(id);
		
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		int staffid = shelf.getHandler();
		Staff staff = staffrepo.getStaffById(staffid);
		
		int handles = staff.getHandlesshelf();
		handles--;
		staff.setHandlesshelf(handles);
		staffrepo.updateStaff(staff);
		
		model.addAttribute("error","SHELF DELETED SUCCESSFULLY");
		return "home";
	}
	
	@PostMapping("/updateshelf")
	public String UpdateShelf(@ModelAttribute("shelf") Shelf shelf,HttpSession session, Model model)
	{
		if(!is_manager(session))
		{
			model.addAttribute("error","YOU DON'T HAVE APPROPRIATE PERMISSIONS");
			return "home";
		}
		
		int dept = (int)session.getAttribute("Dept");
		if(dept!=101)
		{
			model.addAttribute("error","ONLY MANAGERS OF THE BOOKS DEPARTMENT CAN UPDATE DETAILS OF A SHELF");
			return "home";
		}
		
		int id = shelf.getShelfid();
		Shelf PrevShelf = shelfrepo.getShelfById(id);
		
		int flag = shelfrepo.updateShelf(shelf);
		
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		Shelf NewShelf = shelfrepo.getShelfById(id);
		
		int prev_handler_id = PrevShelf.getHandler();
		Staff PrevStaff = staffrepo.getStaffById(prev_handler_id);
		int handled = PrevStaff.getHandlesshelf();
		handled--;
		PrevStaff.setHandlesshelf(handled);
		staffrepo.updateStaff(PrevStaff);
		
		int cur_handler_id = NewShelf.getHandler();
		Staff CurStaff = staffrepo.getStaffById(cur_handler_id);
		int handles = CurStaff.getHandlesshelf();
		handles++;
		CurStaff.setHandlesshelf(handles);
		staffrepo.updateStaff(CurStaff);
		
		model.addAttribute("error","SHELF UPDATED SUCCESSFULLY");
		return "home";
	}
}
