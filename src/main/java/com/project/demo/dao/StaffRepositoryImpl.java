package com.project.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Staff;

@Repository
public class StaffRepositoryImpl implements StaffRepository
{

	private static final String saveStaff="INSERT INTO STAFF (fname,lname,password,gender,contact,email,street,area,pincode,HandlesShelf) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String getStaffById ="SELECT * FROM STAFF WHERE STAFFID=? ";
	private static final String getAllStaff = "SELECT * FROM STAFF";
	private static final String updateStaff = "UPDATE STAFF SET fname=?,lname=?,password=?,gender=?,contact=?,email=?,street=?,area=?,pincode=?,HandlesShelf=? WHERE STAFFID=?";
	private static final String deleteStaffById = "DELETE FROM STAFF WHERE STAFFID=?";
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Override
	public Staff saveStaff(Staff staff) {
		jdbctemplate.update(saveStaff,staff.getFname(),staff.getLname(),staff.getPassword(),staff.getGender(),staff.getContact(),staff.getEmail(),staff.getStreet(),staff.getArea(),staff.getPincode(),staff.getHandlesShelf());
		return staff;
	}

	@Override
	public Staff getStaffById(int id) {
		try {
			return jdbctemplate.queryForObject(getStaffById,(rs,rowNum)->{
				Staff staff = new Staff();
				staff.setStaffid(rs.getInt(1));
				staff.setFname(rs.getString(2));
				staff.setLname(rs.getString(3));
				staff.setPassword(rs.getString(4));
				staff.setGender(rs.getString(5));
				staff.setContact(rs.getString(6));
				staff.setEmail(rs.getString(7));
				staff.setStreet(rs.getString(8));
				staff.setArea(rs.getString(9));
				staff.setPincode(rs.getLong(10));
				staff.setHandlesShelf(rs.getInt(11));
				return staff;
			},id);
		}
		catch(Exception e) {
			Staff staff = new Staff();
			staff.setStaffid(-1);
			return staff;
		}
	}

	@Override
	public List<Staff> getAllStaffs() {
		return jdbctemplate.query(getAllStaff,(rs,rowNum)->{
			Staff staff = new Staff();
			staff.setStaffid(rs.getInt(1));
			staff.setFname(rs.getString(2));
			staff.setLname(rs.getString(3));
			staff.setPassword("CAN'T DISPLAY, SORRY!!!");
			staff.setGender(rs.getString(5));
			staff.setContact(rs.getString(6));
			staff.setEmail(rs.getString(7));
			staff.setStreet(rs.getString(8));
			staff.setArea(rs.getString(9));
			staff.setPincode(rs.getLong(10));
			staff.setHandlesShelf(rs.getInt(11));
			return staff;
		});
	}

	@Override
	public int deleteStaffById(int id) {
		return jdbctemplate.update(deleteStaffById,id);
	}

	@Override
	public int updateStaff(Staff staff) {
		return jdbctemplate.update(updateStaff,staff.getFname(),
				staff.getLname(),
				staff.getPassword(),
				staff.getGender(),
				staff.getContact(),
				staff.getEmail(),
				staff.getStreet(),
				staff.getArea(),
				staff.getPincode(),
				staff.getHandlesShelf(),
				staff.getStaffid());
	}

}
