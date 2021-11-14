package com.project.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Stock;

@Repository
public class StockRepositoryImpl implements StockRepository{

	private static final String AddStock = "INSERT INTO STOCK(title,author,publications,booksavailable,booksissued) VALUES(?,?,?,?,?)";
	private static final String GetAllStock = "SELECT * FROM STOCK";
	private static final String getStockById = "SELECT * FROM STOCK WHERE (TITLE,AUTHOR,PUBLICATIONS)=(?,?,?)";
	private static final String updateStock = "UPDATE STOCK SET booksavailable=?,booksissued=? WHERE (TITLE,AUTHOR,PUBLICATIONS)=(?,?,?)";
	private static final String updateStockNew = "UPDATE STOCK SET booksavailable=? WHERE (TITLE,AUTHOR,PUBLICATIONS)=(?,?,?)";
	private static final String DeleteStock = "DELETE FROM STOCK WHERE (TITLE,AUTHOR,PUBLICATIONS)=(?,?,?)" ;
	@Autowired
	JdbcTemplate jdbctemplate;
	
	@Override
	public int saveStock(Stock stock) {
		return jdbctemplate.update(AddStock,stock.getTitle(),stock.getAuthor(),stock.getPublications(),stock.getBooksavailable(),stock.getBooksissued());
		
	}

	@Override
	public List<Stock> getAllStocks() {
		return jdbctemplate.query(GetAllStock,(rs,rowNum)->{
			Stock stock = new Stock();
			stock.setTitle(rs.getString(1));
			stock.setAuthor(rs.getString(2));
			stock.setPublications(rs.getString(3));
			stock.setBooksavailable(rs.getInt(4));
			stock.setBooksissued(rs.getInt(5));
			return stock;
		});
	}
	
	@Override
	public Stock getStock(String title, String author, String publications) {
		try {
			return jdbctemplate.queryForObject(getStockById,(rs,rowNum)->{
				Stock stock = new Stock();
				stock.setTitle(rs.getString(1));
				stock.setAuthor(rs.getString(2));
				stock.setPublications(rs.getString(3));
				stock.setBooksavailable(rs.getInt(4));
				stock.setBooksissued(rs.getInt(5));
				return stock;
			},title,author,publications);
		}
		catch(Exception e) {
			Stock stock = new Stock();
			stock.setBooksavailable(-1);
			return stock;
		}
	}

	@Override
	public int deleteStockById(String title, String author, String publications) {
		return jdbctemplate.update(DeleteStock,title,author,publications);
	}

	@Override
	public Stock updateStock(Stock stock) {
		jdbctemplate.update(updateStock,
				stock.getBooksavailable(),
				stock.getBooksissued(),
				stock.getTitle(),
				stock.getAuthor(),
				stock.getPublications());
		return stock;
	}
	
	@Override
	public Stock updateStockNew(Stock stock) {
		jdbctemplate.update(updateStockNew,
				stock.getBooksavailable(),
				stock.getTitle(),
				stock.getAuthor(),
				stock.getPublications());
		return stock;
	}
}
