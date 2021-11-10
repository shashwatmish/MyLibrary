package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Staff;

public interface StaffRepository {
	Staff saveStaff(Staff staff);
	Staff getStaffById(int id);
	List<Staff> getAllStaffs();
	Staff updateStaff(Staff staff);
	String deleteStaffById(int id);
}
