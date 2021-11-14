package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Shelf;

public interface ShelfRepository {
	int saveShelf(Shelf shelf);
	Shelf getShelfById(int id);
	List<Shelf> getAllShelves();
	int updateShelf(Shelf shelf);
	int deleteShelfById(int id);
}


