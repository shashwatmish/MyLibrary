package com.project.demo.models;

public class ManagerEmail {
	private int managerid;
	private String email;
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "ManagerEmail [managerid=" + managerid + ", email=" + email + "]";
	}
}