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

import com.project.demo.dao.RetailerRepository;
import com.project.demo.models.Manager;
import com.project.demo.models.Retailer;

@Controller
public class RetailerController {
	@Autowired
	RetailerRepository retailerrepo;
	
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
	
	@GetMapping("/registerretailer")
	public String saveRetailer()
	{
		return "AddRetailer";
	}
	
	@GetMapping("/updateretailer")
	public String updateRetailer()
	{
		return "UpdateRetailer";
	}
	
	@PostMapping("/registerretailer")
	public String saveRetailer(@ModelAttribute("retailer") Retailer retailer,HttpSession session,Model model)
	{
		if(is_student(session) || is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO ADD RETAILER DETAILS");
			//return "NOT AUTHORISED TO ADD RETAILER DETAILS";
			return "home";
		}
		
		if(!is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE INSERTING");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE INSERTING";
			return "Login";		
		}
		
		Manager manager = (Manager)session.getAttribute("manager");
		int id = manager.getDeptid();
		
		if(id!=102)
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE INSERTING");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE INSERTING";
			return "Login";
		}
		
		int flag = retailerrepo.saveRetailer(retailer);
		
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN ");
			//return "SOMETHING WENT WRONG, PLEASE TRY AGAIN ";
			return "home";
		}
		
		model.addAttribute("error","SUCCESSFUL INSERTION OF THE RETAILER DETAILS");
		//return "SUCCESSFUL INSERTION OF THE RETAILER DETAILS";
		return "home";
	}
	
	@GetMapping("/getretailer")
	public String ShowRetailerById(@RequestParam("id") int id,HttpSession session,Model model)
	{
		if(is_student(session) || is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO SEE RETAILER DETAILS");
			//return "NOT AUTHORISED TO SEE RETAILER DETAILS";
			return "home";
		}
		
		if(!is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE";
			return "Login";		
		}
		
		Manager manager = (Manager)session.getAttribute("manager");
		int cur_id = manager.getDeptid();
		
		if(cur_id!=102)
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE";
			return "Login";
		}
		
		Retailer retailer = retailerrepo.getRetailerById(id);
		if(retailer.getRetailerid()==-1)
		{
			model.addAttribute("error","No Such Retailer");
			return "Retailers";
		}
		
		model.addAttribute("qq",retailer);
		return "Retailers";
	}
	
	@GetMapping("/getretailers")
	public String ShowRetailers(Model model, HttpSession session)
	{
		if(is_student(session) || is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO SEE RETAILER DETAILS");
			//return "NOT AUTHORISED TO SEE RETAILER DETAILS";
			return "home";
		}
		
		if(!is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE";
			return "Login";		
		}
		
		Manager manager = (Manager)session.getAttribute("manager");
		int cur_id = manager.getDeptid();
		
		if(cur_id!=102)
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE VIEWING THIS PAGE";
			return "Login";
		}
		
		List<Retailer> list = retailerrepo.getAllRetailers();
		model.addAttribute("qresult",list);
		return "Retailers";
	}
	
	@PostMapping("/deleteretailer")
	public String DeleteRetailerById(@RequestParam("id") int id,Model model,HttpSession session)
	{
		if(is_student(session) || is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO DELETE RETAILER DETAILS");
			//return "NOT AUTHORISED TO DELETE RETAILER DETAILS";
			return "home";
		}
		
		if(!is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE DELETING");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE DELETING";
			return "Login";		
		}
		
		Manager manager = (Manager)session.getAttribute("manager");
		int cur_id = manager.getDeptid();
		
		if(cur_id!=102)
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE DELETING");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE DELETING";
			return "Login";
		}
		
		int flag = retailerrepo.deleteRetailerById(id);
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		model.addAttribute("error","DELETION OF THE RETAILER SUCCESSFUL");
		return "home";
	}
	
	@PostMapping("/updateretailer")
	public String UpdateRetailer(@ModelAttribute("retailer") Retailer retailer,Model model,HttpSession session)
	{
		if(is_student(session) || is_staff(session))
		{
			model.addAttribute("error","NOT AUTHORISED TO UPDATE RETAILER DETAILS");
			return "NOT AUTHORISED TO UPDATE RETAILER DETAILS";
			//return "home";
		}
		
		if(!is_manager(session))
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE UPDATING");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE UPDATING";
			return "Login";		
		}
		
		Manager manager = (Manager)session.getAttribute("manager");
		int cur_id = manager.getDeptid();
		
		if(cur_id!=102)
		{
			model.addAttribute("error","LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE UPDATING");
			//return "LOGIN AS A MANAGER OF THE RETAILER DEPARTMENT BEFORE UPDATING";
			return "Login";
		}
		
		int flag = retailerrepo.updateRetailer(retailer);
		if(flag!=1)
		{
			model.addAttribute("error","SOMETHING WENT WRONG, PLEASE TRY AGAIN");
			return "home";
		}
		
		model.addAttribute("error","UPDATION OF THE RETAILER DETAILS SUCCESSFUL");
		return "home";
	}
	
}
