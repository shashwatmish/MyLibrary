package com.project.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
	private int staffid;
	private String fname;
	private String lname;
	private String password;
	private String gender;
	private String contact;
	private String email;
	private String street;
	private String area;
	private long pincode;
	private int handlesshelf;
	public int getStaffid() {
		return staffid;
	}
	public void setStaffid(int staffid) {
		this.staffid = staffid;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getHandlesshelf() {
		return handlesshelf;
	}
	public void setHandlesshelf(int handlesshelf) {
		this.handlesshelf = handlesshelf;
	}
	@Override
	public String toString() {
		return "Staff [staffid=" + staffid + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender
				+ ", street=" + street + ", area=" + area + ", pincode=" + pincode + ", contact=" + contact + ", email="
				+ email + ", password=" + password + ", HandlesShelf=" + handlesshelf + "]";
	}
	
}
