package edu.monmouth.library;

public class Book implements LibraryItem{
private String title;
private String author; 
private int pages;
private StatusType status;

public Book(String author,String title, int pages, StatusType status) throws BookException {
	setAuthor(author);
	setTitle(title);
	setPages(pages);
	setStatus(status);
}
public String getAuthor() {
	return author;
}
public String getTitle() {
	return title;
}
public int getPages() {
	return pages;
}
public StatusType getStatus() {
	return status;
}
public void setAuthor(String author) throws BookException{
	if(author==null||author.length()==BookConstants.MIN) {
		throw new BookException("Author incorrect: musnt be empty");
	}
	this.author = author;
}
public void setTitle(String title) throws BookException {
	if(title==null||title.length()==BookConstants.MIN) {
		throw new BookException("Title incorrect: musnt be empty");
	}
	this.title = title;
}

public void setPages(int pages) throws BookException {
	if(pages<=BookConstants.MIN) {
		throw new BookException("Pages incorrect: must be more than 1");
	}
	this.pages = pages;
}
public void setStatus(StatusType status) {
	this.status = status;
}
public boolean isAvaliable() {
	 return status == StatusType.ONSHELF;
}
public void borrowItem() {
	status = StatusType.BORROWED;
}
public void returnItem() {
	status = StatusType.ONSHELF;
}
//
@Override 
public boolean equals(Object obj ) {
	if(this == obj) return true;
	if(obj == null || !(obj instanceof Book)) return false;
	
	Book temp = (Book) obj;
	
	boolean authorsEqual = (this.author == null)
			? temp.author == null
			: this.author.equals(temp.author);
	
	boolean titlesEqual = (this.title == null)
			? temp.title == null
			: this.title.equals(temp.title);
	
	return authorsEqual && titlesEqual;
	
}

@Override
public String toString() {
	return "BOOK: Author: " + author+"| Title: "+title +"| Status: "+status+"| Pages: "+pages+" |";
}
@Override
public String getTile() {
	return title;
}
@Override
public boolean isAvailable() {
	return status == StatusType.ONSHELF;
}
}
