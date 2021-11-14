package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Manager;

public interface ManagerRepository {
	int saveManager(Manager manager);
	Manager getManagerById(int id);
	List<Manager> getAllManagers();
	int updateManager(Manager manager);
	int deleteManagerById(int id);
}
