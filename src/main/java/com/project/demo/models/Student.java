package com.project.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private int StudentId;
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
	private int BooksIssued;
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public int getBooksIssued() {
		return BooksIssued;
	}
	public void setBooksIssued(int booksIssued) {
		BooksIssued = booksIssued;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Student [StudentId=" + StudentId + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender
				+ ", age=" + age + ", contact=" + contact + ", email=" + email + ", street=" + street + ", area=" + area
				+ ", pincode=" + pincode + ", BooksIssued=" + BooksIssued + ", password=" + password + "]";
	}
	
	
}
