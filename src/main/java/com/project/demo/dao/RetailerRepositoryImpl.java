package com.project.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Retailer;

@Repository
public class RetailerRepositoryImpl implements RetailerRepository
{
	@Autowired
	JdbcTemplate jdbctemplate;
	
	private static final String saveRetailer="INSERT INTO RETAILER (fname,lname,contact,email) VALUES(?,?,?,?)";
	private static final String getAllRetailers = "SELECT * FROM RETAILER";
	private static final String getRetailerById="SELECT * FROM RETAILER WHERE RETAILERID=?";
	private static final String updateRetailer = "UPDATE RETAILER SET fname=?,lname=?,contact=?,email=? WHERE RETAILERID=?";
	public static final String deleteRetailerWithId = "DELETE FROM RETAILER WHERE RETAILERID=?";

	@Override
	public int saveRetailer(Retailer retailer) {
		return jdbctemplate.update(saveRetailer,retailer.getFname(),retailer.getLname(),retailer.getContact(),retailer.getEmail());
	}

	@Override
	public Retailer getRetailerById(int id) {
		try {
			return jdbctemplate.queryForObject(getRetailerById,(rs,rowNum)->{
				Retailer retailer = new Retailer();
				retailer.setRetailerid(rs.getInt(1));
				retailer.setFname(rs.getString(2));
				retailer.setLname(rs.getString(3));
				retailer.setContact(rs.getString(4));
				retailer.setEmail(rs.getString(5));
				
				return retailer;
			},id);
		}
		catch(Exception e) {
			Retailer retailer = new Retailer();
			retailer.setRetailerid(-1);
			return retailer;
		}
	}

	@Override
	public List<Retailer> getAllRetailers() {
		return jdbctemplate.query(getAllRetailers,(rs,rowNum)->{
			Retailer retailer = new Retailer();
			retailer.setRetailerid(rs.getInt(1));
			retailer.setFname(rs.getString(2));
			retailer.setLname(rs.getString(3));
			retailer.setContact(rs.getString(4));
			retailer.setEmail(rs.getString(5));
			
			return retailer;
		});
	}

	@Override
	public int deleteRetailerById(int id) {
		return jdbctemplate.update(deleteRetailerWithId,id);
	}

	@Override
	public int  updateRetailer(Retailer retailer) {
		return jdbctemplate.update(updateRetailer,retailer.getFname(),retailer.getLname(),retailer.getContact(),retailer.getEmail(),retailer.getRetailerid());
	}

}
