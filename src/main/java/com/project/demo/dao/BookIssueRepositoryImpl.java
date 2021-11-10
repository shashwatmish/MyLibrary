package com.project.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.demo.models.BookIssue;

@Repository
public class BookIssueRepositoryImpl implements BookIssueRepository{
	
	private String issueBook = "INSERT INTO BOOKISSUE (studentid,bookid,issuedate,returndate,daysgap) VALUES(?,?,?,?,?)";
	private String returnBook = "DELETE FROM BOOKISSUE WHERE (studentid,bookid)=(?,?)";

	@Autowired
	private JdbcTemplate jdbctemplate;
	

	@Override
	public int ReturnBook(int studentid, int bookid) {
		int flag = jdbctemplate.update(returnBook,studentid,bookid);
		return flag;
	}

	@Override
	public int IssueBook(BookIssue bookissue) {
		int flag = jdbctemplate.update(issueBook,bookissue.getStudentid(),bookissue.getBookid(),bookissue.getIssuedate(),bookissue.getReturndate(),bookissue.getDaysgap());
		return flag;
	}

}
