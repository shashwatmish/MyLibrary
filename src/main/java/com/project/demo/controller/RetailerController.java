package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.dao.RetailerRepository;
import com.project.demo.models.Retailer;

@Controller
public class RetailerController {
	@Autowired
	RetailerRepository retailerrepo;
	
	@PostMapping("/registerretailer")
	public @ResponseBody Retailer saveRetailer(@RequestBody Retailer retailer)
	{
		return retailerrepo.saveRetailer(retailer);
	}
	
	@GetMapping("/getretailer/{id}")
	public @ResponseBody Retailer ShowRetailerById(@PathVariable("id") int id)
	{
		return retailerrepo.getRetailerById(id);
	}
	
	@GetMapping("/getretailers")
	public @ResponseBody List<Retailer> ShowRetailers()
	{
		return retailerrepo.getAllRetailers();
	}
	
	@DeleteMapping("/deleteretailer/{id}")
	public @ResponseBody String DeleteRetailerById(@PathVariable("id") int id)
	{
		return retailerrepo.deleteRetailerById(id);
	}
	
	@PutMapping("/updateretailer")
	public @ResponseBody Retailer UpdateRetailer(@RequestBody Retailer retailer)
	{
		return retailerrepo.updateRetailer(retailer);
	}
	
}
