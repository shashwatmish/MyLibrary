package com.project.demo.dao;

import java.util.List;
import com.project.demo.models.Retailer;

public interface RetailerRepository {
	int saveRetailer(Retailer retailer);
	Retailer getRetailerById(int id);
	List<Retailer> getAllRetailers();
	int updateRetailer(Retailer retailer);
	int deleteRetailerById(int id);
}
