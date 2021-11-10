package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Manager;

public interface ManagerRepository {
	Manager saveManager(Manager manager);
	Manager getManagerById(int id);
	List<Manager> getAllManagers();
	Manager updateManager(Manager manager);
	String deleteManagerById(int id);
}
