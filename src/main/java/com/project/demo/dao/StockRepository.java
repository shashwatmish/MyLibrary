package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Stock;

public interface StockRepository {
	int saveStock(Stock stock);
	Stock getStock(String title, String author, String publications);
	List<Stock> getAllStocks();
	Stock updateStock(Stock stock);
	Stock updateStockNew(Stock stock);
	int deleteStockById(String title, String author, String publications);
}
