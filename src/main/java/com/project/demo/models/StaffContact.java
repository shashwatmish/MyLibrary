package com.project.demo.models;

public class StaffContact {
	private int staffid;
	private String contact;

	public int getStaffid() {
		return staffid;
	}
	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
		return "StaffContact [staffid=" + staffid + ", contact=" + contact + "]";
	}
}
