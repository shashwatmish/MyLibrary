package com.project.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Manager;

@Repository
public class ManagerRepositoryImpl implements ManagerRepository
{
	// while writing the front end, mention the list of department id
	private static final String saveManager="INSERT INTO MANAGER (deptid,fname,lname,password,street,area,pincode,contact,email) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String getAllManagers = "SELECT * FROM MANAGER";
	private static final String getManagerById="SELECT * FROM MANAGER WHERE MANAGERID=?";
	private static final String updateManager = "UPDATE MANAGER SET deptid=?,fname=?,lname=?,password=?,street=?,area=?,pincode=?,contact=?,email=? WHERE MANAGERID=?";
	public static final String deleteManagerWithId = "DELETE FROM MANAGER WHERE MANAGERID=?";
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Override
	public Manager saveManager(Manager manager) {
		jdbctemplate.update(saveManager,manager.getDeptid(),manager.getFname(),manager.getLname(),manager.getPassword(),manager.getStreet(),manager.getArea(),manager.getPincode(),manager.getContact(),manager.getEmail());
		return manager;
	}


	@Override
	public List<Manager> getAllManagers() {
		return jdbctemplate.query(getAllManagers,(rs,rowNum)->{
			Manager manager = new Manager();
			manager.setManagerid(rs.getInt(1));
			manager.setDeptid(rs.getInt(2));
			manager.setFname(rs.getString(3));
			manager.setLname(rs.getString(4));
			manager.setPassword("CAN'T DISPLAY, SORRY!!!");
			manager.setStreet(rs.getString(6));
			manager.setArea(rs.getString(7));
			manager.setPincode(rs.getLong(8));
			manager.setContact(rs.getString(9));
			manager.setEmail(rs.getString(10));
			
			return manager;
		});
	}


	@Override
	public Manager getManagerById(int id) {
		try{
			return jdbctemplate.queryForObject(getManagerById,(rs,rowNum)->{
				Manager manager = new Manager();
				manager.setManagerid(rs.getInt(1));
				manager.setDeptid(rs.getInt(2));
				manager.setFname(rs.getString(3));
				manager.setLname(rs.getString(4));
				manager.setPassword(rs.getString(5));
				manager.setStreet(rs.getString(6));
				manager.setArea(rs.getString(7));
				manager.setPincode(rs.getLong(8));
				manager.setContact(rs.getString(9));
				manager.setEmail(rs.getString(10));
				
				return manager;
			},id);
		}
		catch(Exception e) {
			Manager manager = new Manager();
			manager.setManagerid(-1);
			return manager;
		}
	}


	@Override
	public String deleteManagerById(int id) {
		int flag = jdbctemplate.update(deleteManagerWithId,id);
		if(flag==1)
			return "Manager with id " + id + " deleted successfully";
		else
			return " 0 0 0 ";
	}


	@Override
	public Manager updateManager(Manager manager) {
		jdbctemplate.update(updateManager,
				manager.getDeptid(),
				manager.getFname(),
				manager.getLname(),
				manager.getPassword(),
				manager.getStreet(),
				manager.getArea(),
				manager.getPincode(),
				manager.getContact(),
				manager.getEmail(),
				manager.getManagerid());
		return manager;
	}
	
}
