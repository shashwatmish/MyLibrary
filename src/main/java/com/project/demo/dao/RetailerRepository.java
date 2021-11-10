package com.project.demo.dao;

import java.util.List;
import com.project.demo.models.Retailer;

public interface RetailerRepository {
	Retailer saveRetailer(Retailer retailer);
	Retailer getRetailerById(int id);
	List<Retailer> getAllRetailers();
	Retailer updateRetailer(Retailer retailer);
	String deleteRetailerById(int id);
}
