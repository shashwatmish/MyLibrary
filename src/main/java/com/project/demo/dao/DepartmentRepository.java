package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Department;

public interface DepartmentRepository 
{
	Department getDeptById(int id);
	List<Department> getAllDepts();
	Department updateDept(Department department);
}
