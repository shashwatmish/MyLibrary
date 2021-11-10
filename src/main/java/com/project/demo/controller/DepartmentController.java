package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@GetMapping("/getdept/{id}")
	public @ResponseBody Department getDepartmentById(@PathVariable("id") int id)
	{
		return deptrepo.getDeptById(id);
	}
	
	@GetMapping("/getdepts")
	public @ResponseBody List<Department> getDepartmentById()
	{
		return deptrepo.getAllDepts();
	}
	
	@PutMapping("/updatedept")
	public @ResponseBody Department UpdateDepartment(@RequestBody Department dept)
	{
		return deptrepo.updateDept(dept);
	}

}
