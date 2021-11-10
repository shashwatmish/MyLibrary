package com.project.demo.models;

public class RetailerContact {
	private int retailerid;
	private String contact;
	public int getRetailerid() {
		return retailerid;
	}
	public void setRetailerid(int retailerid) {
		this.retailerid = retailerid;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "RetailerContact [retailerid=" + retailerid + ", contact=" + contact + "]";
	}
	
}
