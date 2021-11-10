package com.project.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
 
    //composite primary key
	private String title;
	private String author;
	private String publications;
    private int booksavailable;
    private int booksissued;
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
	public int getBooksavailable() {
		return booksavailable;
	}
	public void setBooksavailable(int booksavailable) {
		this.booksavailable = booksavailable;
	}
	public int getBooksissued() {
		return booksissued;
	}
	public void setBooksissued(int booksissued) {
		this.booksissued = booksissued;
	}
	@Override
	public String toString() {
		return "Stock [title=" + title + ", author=" + author + ", publications=" + publications + ", booksavailable="
				+ booksavailable + ", booksissued=" + booksissued + "]";
	}
	
}
