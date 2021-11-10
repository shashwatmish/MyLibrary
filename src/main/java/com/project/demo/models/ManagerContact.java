package com.project.demo.models;

public class ManagerContact {
	private int managerid;
	private String contact;
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "ManagerContact [managerid=" + managerid + ", contact=" + contact + "]";
	}
	
}