package com.project.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book 
{
	private int bookid;
	private int shelfid;
	private String title;
	private String author;
	private String publications;
	private boolean isissued;
	private String language;
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getShelfid() {
		return shelfid;
	}
	public void setShelfid(int shelfid) {
		this.shelfid = shelfid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublications() {
		return publications;
	}
	public void setPublications(String publications) {
		this.publications = publications;
	}
	public boolean getIsissued() {
		return isissued;
	}
	public void setIsissued(boolean isissued) {
		this.isissued = isissued;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", shelfid=" + shelfid + ", title=" + title + ", author=" + author
				+ ", publications=" + publications + ", isissued=" + isissued + ", language=" + language + "]";
	}
	
}