package com.project.demo.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Department;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository
{
	private final String getDepartmentById = "SELECT * FROM DEPARTMENT WHERE DEPTID=?";
	private final String getAllDepartments = "SELECT * FROM DEPARTMENT";
	private final String updateDepartment = "UPDATE DEPARTMENT SET deptname=?,description=?,establishedYear=? WHERE DEPTID=?";
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Override
	public Department getDeptById(int id) {
		return jdbctemplate.queryForObject(getDepartmentById,(rs,rowNum)->{
			Department department = new Department();
			department.setDeptid(rs.getInt(1));
			department.setDeptname(rs.getString(2));
			department.setDescription(rs.getString(3));
			department.setEstablishedyear(rs.getString(4));
			return department;
		},id);
	}

	@Override
	public List<Department> getAllDepts() {
		return jdbctemplate.query(getAllDepartments,(rs,rowNum)->{
			Department department = new Department();
			department.setDeptid(rs.getInt(1));
			department.setDeptname(rs.getString(2));
			department.setDescription(rs.getString(3));
			department.setEstablishedyear(rs.getString(4));
			return department;
		});
	}

	@Override
	public Department updateDept(Department department) {
		jdbctemplate.update(updateDepartment,
				department.getDeptname(),
				department.getDescription(),
				department.getEstablishedyear(),
				department.getDeptid());
		return department;
	}
	
	
}
