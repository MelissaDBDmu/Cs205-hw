package edu.monmouth.library;

import java.util.List;

public class ListLibraryNode {
	/* add a reference to a LibraryNode named head */
	ListLibraryNode Node;
	// data;
	private LibraryNode head;
	private LibraryNode tail;
	//private ListLibraryNode next;
	
	public ListLibraryNode() {
	}
	public LibraryNode removeFirst() {
		LibraryNode current = head;
		if(head==null) {
			return null;
		}
		head=current.getNext();
		return current;
	}
	public LibraryNode first() {
		return head;
	}
  	public LibraryNode last() {
  		//LibraryNode current= head;
  		if(tail==null) {
  			return null;
  		}
  		//return current;
  		return tail;
	}
  	
	public void insert(LibraryItem element) {
		LibraryNode newNode = new LibraryNode(element);
		newNode.setNext(head);
		head = newNode;
	}
	public void insertEnd(LibraryItem element) {
		LibraryNode newNode = new LibraryNode(element);
		LibraryNode current = head;
		if(head==null) {
			head=newNode;
		}
		while(current.getNext()!=null) {
			current=current.getNext();
		}
		current.setNext(newNode);
	}
	public boolean isEmpty() { 
		return head==null;
	}
	public int size(LibraryNode node) {
		if(Node==null) {
		return 0;
		}//LibraryNode size; 
	 return size(node.next)+1;
	}
	
	public void clear() {
		head = null;
	}
	@Override
  	public String toString() {
		StringBuilder result = new StringBuilder();
		// iterate thru list, append to result
		//List<LibraryNode> itemList ;
		LibraryNode current = head;
		if(head==null) {
			return null;	
		}
		while(current!=null) {
			result.append(current.getInfo());
			current = current.getNext();
		}
		return result.toString();
	}
}
