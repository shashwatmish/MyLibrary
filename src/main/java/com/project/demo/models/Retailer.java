package com.project.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Retailer {
	private int retailerid;
	private String fname;
	private String lname;
	private String contact;
	private String email;
	public int getRetailerid() {
		return retailerid;
	}
	public void setRetailerid(int retailerid) {
		this.retailerid = retailerid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Retailer [retailerid=" + retailerid + ", fname=" + fname + ", lname=" + lname + ", contact=" + contact
				+ ", email=" + email + "]";
	}
	
	
}
