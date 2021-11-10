package com.project.demo.models;


public class StudentContact {
	private int studentid;
	private long contact;
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "StudentContact [studentid=" + studentid + ", contact=" + contact + "]";
	}
	
	
}
