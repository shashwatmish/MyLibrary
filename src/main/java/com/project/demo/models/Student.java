package com.project.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private int studentid;
	private String fname;
	private String lname;
	private String password;
	private int age;
	private String gender;
	private String contact;
	private String email;
	private String street;
	private String area;
	private long pincode;
	private int booksissued;
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public int getBooksissued() {
		return booksissued;
	}
	public void setBooksissued(int booksissued) {
		this.booksissued = booksissued;
	}
	@Override
	public String toString() {
		return "Student [studentid=" + studentid + ", fname=" + fname + ", lname=" + lname + ", password=" + password
				+ ", age=" + age + ", gender=" + gender + ", contact=" + contact + ", email=" + email + ", street="
				+ street + ", area=" + area + ", pincode=" + pincode + ", booksIssued=" + booksissued + "]";
	}
	
	
	
}