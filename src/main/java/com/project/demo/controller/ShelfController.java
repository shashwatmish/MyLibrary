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


import com.project.demo.dao.ShelfRepository;
import com.project.demo.models.Shelf;

@Controller
public class ShelfController {
	@Autowired
	ShelfRepository shelfrepo;
	
	@PostMapping("/registershelf")
	public @ResponseBody Shelf SaveShelf(@RequestBody Shelf shelf) {
		
		return shelfrepo.saveShelf(shelf);
	}
	
	@GetMapping("/getshelf/{id}")
	public @ResponseBody Shelf ShowShelfById(@PathVariable("id") int id)
	{
		return shelfrepo.getShelfById(id);
	}
	
	@GetMapping("/getshelves")
	public @ResponseBody List<Shelf> ShowShelves()
	{
		return shelfrepo.getAllShelves();
	}
	
	@DeleteMapping("/deleteshelf/{id}")
	public @ResponseBody String DeleteShelfById(@PathVariable("id") int id)
	{
		return shelfrepo.deleteShelfById(id);
	}
	
	@PutMapping("/updateshelf")
	public @ResponseBody Shelf UpdateShelf(@RequestBody Shelf shelf)
	{
		return shelfrepo.updateShelf(shelf);
	}
}
