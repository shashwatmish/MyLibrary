package com.project.demo.dao;

import com.project.demo.models.BookIssue;

public interface BookIssueRepository {
	int IssueBook(BookIssue bookissue);
	int ReturnBook(int studentid, int bookid);
}
