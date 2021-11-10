package com.project.demo.models;

public class StaffEmail {
	private int staffid;
	private String email;
	
	public int getStaffid() {
		return staffid;
	}
	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "StaffEmail [staffid=" + staffid + ", email=" + email + "]";
	}
}
