package com.project.demo.models;

public class RetailerEmail {
	private int retailerid;
	private String email;
	public int getRetailerid() {
		return retailerid;
	}
	public void setRetailerid(int retailerid) {
		this.retailerid = retailerid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "RetailerEmail [retailerid=" + retailerid + ", email=" + email + "]";
	}
	
}
