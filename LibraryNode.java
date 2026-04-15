package edu.monmouth.library;

public class LibraryNode {
	  /* add a reference to a LibraryItem and a reference to a LibraryNode */
		 LibraryNode next;
		private LibraryItem info;
		
	  public LibraryNode(LibraryItem info) {
		 setInfo(info);
	  }
	  public void setInfo(LibraryItem info) {
		  this.info=info;
	  }
	  public LibraryItem getInfo() {
		  return info;
	  }
	  public void setNext(LibraryNode link) {
		  this.next = link;
	  }
	  public LibraryNode getNext() {
		  return next;
	  }
	  @Override 
	  public String toString() {
		  return "LibraryNode [Info: "+ info.toString(); 
	  }
	}