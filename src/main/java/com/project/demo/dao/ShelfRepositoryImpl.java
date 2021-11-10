package com.project.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Shelf;

@Repository
public class ShelfRepositoryImpl implements ShelfRepository{

	private static final String saveShelf="INSERT INTO SHELF (handler,floor,description) VALUES(?,?,?)";
	private static final String getAllShelves = "SELECT * FROM SHELF";
	private static final String getShelfById="SELECT * FROM SHELF WHERE SHELFID=?";
	private static final String updateShelf="UPDATE SHELF SET handler=?,floor=?,description=? WHERE SHELFID=?";
	public static final String deleteShelfWithId = "DELETE FROM SHELF WHERE SHELFID=?";
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Override
	public Shelf saveShelf(Shelf shelf) {
		jdbctemplate.update(saveShelf,shelf.getHandler(),shelf.getFloor(),shelf.getDescription());
		return shelf;
	}

	@Override
	public Shelf getShelfById(int id) {
		return jdbctemplate.queryForObject(getShelfById,(rs,rowNum)->{
			Shelf shelf = new Shelf();
			shelf.setShelfid(rs.getInt(1));
			shelf.setHandler(rs.getInt(2));
			shelf.setFloor(rs.getInt(3));
			shelf.setDescription(rs.getString(4));
			return shelf;
		},id);
	}

	@Override
	public List<Shelf> getAllShelves() {
		return jdbctemplate.query(getAllShelves,(rs,rowNum)->{
			Shelf shelf = new Shelf();
			shelf.setShelfid(rs.getInt(1));
			shelf.setHandler(rs.getInt(2));
			shelf.setFloor(rs.getInt(3));
			shelf.setDescription(rs.getString(4));
			return shelf;
		});
	}

	@Override
	public String deleteShelfById(int id) {
		int flag = jdbctemplate.update(deleteShelfWithId,id);
		if(flag==1)
			return "Shelf with id " + id + " deleted successfully";
		else
			return " 0 0 0 ";
	}

	@Override
	public Shelf updateShelf(Shelf shelf) {
		jdbctemplate.update(updateShelf,shelf.getHandler(),shelf.getFloor(),shelf.getDescription(),shelf.getShelfid());
		return shelf;
	}
	
}