package com.project.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shelf {
	private int shelfid;
	private int handler;
	private int floor;
	private String description;
	public int getShelfid() {
		return shelfid;
	}
	public void setShelfid(int shelfid) {
		this.shelfid = shelfid;
	}
	public int getHandler() {
		return handler;
	}
	public void setHandler(int handler) {
		this.handler = handler;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Shelf [shelfid=" + shelfid + ", handler=" + handler + ", floor=" + floor + ", description="
				+ description + "]";
	}
	
	
}
