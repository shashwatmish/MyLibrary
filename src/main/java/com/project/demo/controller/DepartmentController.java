package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.demo.dao.DepartmentRepository;
import com.project.demo.models.Department;

@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentRepository deptrepo;
	
	@GetMapping("/getdepts")
	public String getDepartmentById(Model model)
	{
		List<Department> list = deptrepo.getAllDepts();
		model.addAttribute("qresult",list);
		return "Departments";
	}
}
