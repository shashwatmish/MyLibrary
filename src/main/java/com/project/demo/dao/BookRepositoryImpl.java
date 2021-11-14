package com.project.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Book;

@Repository
public class BookRepositoryImpl implements BookRepository{
	
	private static final String AddBook = "INSERT INTO BOOK (shelfid,title,author,publications,isissued,language) VALUES(?,?,?,?,?,?)";
	private static final String GetAllBook = "SELECT * FROM BOOK";
	private static final String getBookById = "SELECT * FROM BOOK WHERE BOOKID=?";
	private static final String updateBook = "UPDATE BOOK SET shelfid=?,language=?, isissued=? WHERE BOOKID=?";
	private static final String updateBookNew = "UPDATE BOOK SET shelfid=?,language=? WHERE BOOKID=?";
	private static final String DeleteBookById = "DELETE FROM BOOK WHERE BOOKID=?" ;
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public int saveBook(Book book) {
		return jdbctemplate.update(AddBook,book.getShelfid(),book.getTitle(),book.getAuthor(),book.getPublications(),book.getIsissued(),book.getLanguage());
	}

	@Override
	public Book getBookById(int id) {
		try {
			return jdbctemplate.queryForObject(getBookById,(rs,rowNum)->{
				Book book = new Book();
				book.setBookid(rs.getInt(1));
				book.setShelfid(rs.getInt(2));
				book.setTitle(rs.getString(3));
				book.setAuthor(rs.getString(4));
				book.setPublications(rs.getString(5));
				book.setIsissued(rs.getBoolean(6));
				book.setLanguage(rs.getString(7));
				return book;
			},id);
		}
		catch(Exception e) {
			Book book = new Book();
			book.setBookid(-1);
			return book;
		}
	}

	@Override
	public List<Book> getAllBooks() {
		return jdbctemplate.query(GetAllBook,(rs,rowNum)->{
			Book book = new Book();
			book.setBookid(rs.getInt(1));
			book.setShelfid(rs.getInt(2));
			book.setTitle(rs.getString(3));
			book.setAuthor(rs.getString(4));
			book.setPublications(rs.getString(5));
			book.setIsissued(rs.getBoolean(6));
			book.setLanguage(rs.getString(7));
			return book;
		});
	}

	@Override
	public int deleteBookById(int id) {
		return jdbctemplate.update(DeleteBookById,id);
	}

	@Override
	public int updateBook(Book book) {
		return jdbctemplate.update(updateBook,book.getShelfid(),book.getLanguage(),book.getIsissued(),book.getBookid());
	}

	@Override
	public int updateBookNew(Book book) {
		return jdbctemplate.update(updateBookNew,book.getShelfid(),book.getLanguage(),book.getBookid());
	}
}
