package com.project.demo.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookIssue {
	private int studentid;
	private int bookid;
	private Date issuedate;
	private Date returndate;
	private int daysgap;
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public Date getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	public int getDaysgap() {
		return daysgap;
	}
	public void setDaysgap(int daysgap) {
		this.daysgap = daysgap;
	}
	@Override
	public String toString() {
		return "BookIssue [studentid=" + studentid + ", bookid=" + bookid + ", issuedate=" + issuedate
				+ ", returndate=" + returndate + ", daysgap=" + daysgap + "]";
	}
	
	
}
