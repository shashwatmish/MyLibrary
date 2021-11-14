package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Staff;

public interface StaffRepository {
	int saveStaff(Staff staff);
	Staff getStaffById(int id);
	List<Staff> getAllStaffs();
	int updateStaff(Staff staff);
	int updateStaffNew(Staff staff);
	int deleteStaffById(int id);
}
