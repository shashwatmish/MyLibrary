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
	private static final String updateBook = "UPDATE BOOK SET shelfid=?,title=?,author=?,publications=?,isissued=?,language=? WHERE BOOKID=?";
	private static final String DeleteBookById = "DELETE FROM BOOK WHERE BOOKID=?" ;
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public Book saveBook(Book book) {
		jdbctemplate.update(AddBook,book.getShelfid(),book.getTitle(),book.getAuthor(),book.getPublications(),book.isIsissued(),book.getLanguage());
		return book;
	}

	@Override
	public Book getBookById(int id) {
		return jdbctemplate.queryForObject(getBookById,(rs,rowNum)->{
			Book book = new Book();
			book.setBookid(rs.getInt(1));
			book.setShelfId(rs.getInt(2));
			book.setTitle(rs.getString(3));
			book.setAuthor(rs.getString(4));
			book.setPublications(rs.getString(5));
			book.setIs_issued(rs.getBoolean(6));
			book.setLanguage(rs.getString(7));
			return book;
		},id);
	}

	@Override
	public List<Book> getAllBooks() {
		return jdbctemplate.query(GetAllBook,(rs,rowNum)->{
			Book book = new Book();
			book.setBookid(rs.getInt(1));
			book.setShelfId(rs.getInt(2));
			book.setTitle(rs.getString(3));
			book.setAuthor(rs.getString(4));
			book.setPublications(rs.getString(5));
			book.setIs_issued(rs.getBoolean(6));
			book.setLanguage(rs.getString(7));
			return book;
		});
	}

	@Override
	public String deleteBookById(int id) {
		int flag = jdbctemplate.update(DeleteBookById,id);
		if(flag==1)
			return "Book with id " + id +" deleted successfully";
		else
			return " 0 0 0 ";
	}

	@Override
	public Book updateBook(Book book) {
		jdbctemplate.update(updateBook,
				book.getShelfid(),
				book.getTitle(),
				book.getAuthor(),
				book.getPublications(),
				book.isIsissued(),
				book.getLanguage(),
				book.getBookid());
		
		return book;
	}

}
