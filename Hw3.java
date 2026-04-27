package edu.monmouth.library;
import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Hw3 {
	public static void main(String[] args) {
	
if(args.length !=3) {
			
			System.err.println("Usage: java Hw3 <outputFile> <stringFile> <libraryFile>");
			System.exit(1);
			
		}
		
		String outputFileName = args[0];
		String stringFileName = args[1];
		String libraryFileName = args[2];
		
		// Redirect stdout and stderr to the output file
		PrintStream fileStream = isInOutput(outputFileName);
		
		// Build the two lists
		List<String> stringList = buildStringList(stringFileName);
		List<LibraryItem> libraryList = buildLibraryList(libraryFileName);
		
		// demonstrate all required List operations
		libraryListDemo(stringList);
		libraryListdemo(libraryList);
		
		fileStream.flush();//forces out any buffered output bytes to be written out 
		fileStream.close();//closing 
		
	}//main
	
	private static PrintStream isInOutput(String fileName) {
		
		PrintStream ps = null;
		
		try {
			
			ps = new PrintStream(new FileOutputStream(fileName));
			System.setOut(ps);
			System.setErr(ps);
			
		}//try block
		
		catch(FileNotFoundException e) {
			
			System.err.println("ERROR: output file cannot be open : "+fileName);
			System.err.println(e.getMessage());
			System.exit(1);
			
		}//catch block
		
		return ps;
		
	}//isInOutput
	
	private static List<String> buildStringList(String fileName) {
		
		List<String> list = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			
			String line;
			while((line = br.readLine()) !=null) {
				
				list.add(line);
				
			}
			
		}//try block
		
		catch(IOException e) {
			
			System.err.println("ERROR w/ reading string file: "+e.getMessage());
			
		}//catch block
		
		System.out.println("---- String List after loading ----");
		printList(list);
		return list;
		
	}//buildStringList
	
	private static List<LibraryItem> buildLibraryList(String fileName) {
		
		List<LibraryItem> list = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			
			String line;
			while((line = br.readLine()) !=null) {
				
				// skip blank lines w/ trim 
				if(line.trim().isEmpty()) continue;
				try {
					
					String[] fields = line.split(",");
					if(fields.length != LibraryItemConstants.EXPECTED_FIELD_COUNT) {
						
						throw new IllegalArgumentException("Wrong number of fields ("+fields.length+"): "+line);
						
					}
					
					String type = fields[LibraryItemConstants.TYPE_INDEX].trim();
					String title = fields[LibraryItemConstants.TITLE_INDEX].trim();
					String person = fields[LibraryItemConstants.AUTHOR_EDITOR_INDEX].trim();
					int num = Integer.parseInt(fields[LibraryItemConstants.NUM_INDEX].trim());
					StatusType status = StatusType.valueOf(fields[LibraryItemConstants.STATUS_INDEX].trim());
					if(type.equals(LibraryItemConstants.BOOK_TYPE)) {
						
						// Book Constructor: (title, author, pages, status)
						list.add(new Book(title, person, num, status));
						
					}
					
					else if(type.equals(LibraryItemConstants.JOURNAL_TYPE)) {
						
						// Journal Constructor: (title, editor, volume, status)
						list.add(new Journal(title, person, num, status));
						
					}
					
					else {
						
						throw new IllegalArgumentException("Unknown type '"+type+"': "+line);
						
					}
					
				}//2nd try block
				
				catch(BookException | JournalException | IllegalArgumentException e) {
					
					System.err.println("INVALID LINE skipped --> "+line);
					System.err.println(" Error: "+e.getMessage());
					
				}
				
			}//while loop
			
		}//1st try block
		
		catch(IOException e) {
			
			System.err.println("ERROR reading library file: "+e.getMessage());
			
		}
		
		System.out.println("\n----- Library List after loading ----");
		printList(list);
		return list;
		
	}// buldLibraryList
	
	private static void libraryListDemo(List<String> list) {
		
		System.out.println("\n--------------------------------");
		System.out.println(" STRING LIST OPERATIONS ");
		System.out.println("----------------------------------");
		
		// a. isEmpty
		System.out.println("\n--- a. isEmpty ---");
		System.out.println("String list is empty: "+list.isEmpty());
		
		// c. size
		System.out.println("\n--- c. size ---");
		System.out.println("String list size: "+list.size());
		
		// b. remove by index (index 0)
		System.out.println("\n--- b. remove(index 0) ---");
		String removed = list.remove(0);
		System.out.println("Removed: "+removed);
		System.out.println("List after remove:");
		printList(list);
		
		// d. add
		System.out.println("\n--- d. add ---");
		list.add("Artificial Intelligence");
		System.out.println("Added: Artificial Intelligence");
		System.out.println("List after add:");
		printList(list);
		
		// e. iterator
		System.out.println("\n--- e. iterator (forward) ---");
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			
			System.out.println(" "+it.next());
			
		}
		
		// f. listIterator forward then reverse
		System.out.println("\n--- f. listIterator (forward) ---");
		ListIterator<String> lit = list.listIterator();
		while(lit.hasNext()) {
			
			System.out.println(" "+lit.next());
			
		}
		
		System.out.println("\n--- f. listIterator (reverse) ---");
		while(lit.hasPrevious()) {
			
			System.out.println(" "+lit.previous());
			
		}
		
	}//demonstrateStringList
	
	private static void libraryListdemo(List<LibraryItem> list) {
		
		System.out.println("\n--------------------------------");
		System.out.println(" LIBRARY ITEM LIST OPERATIONS ");
		System.out.println("----------------------------------");
		
		// a. isEmpty
		System.out.println("\n--- a. isEmpty ---");
		System.out.println("Library list is empty: "+list.isEmpty());
		
		// c. size
		System.out.println("\n--- c. size ---");
		System.out.println("Library list size: "+list.size());
		
		// b. remove by index (index 0)
		System.out.println("\n--- b. remove(index 0) ---");
		LibraryItem removedItem = list.remove(0);
		System.out.println("Removed: "+removedItem);
		System.out.println("List after remove:");
		printList(list);
		
		// d. add - insert a new Book
		System.out.println("\n--- d. add ---");
		try {
			
			Book newBook = new Book("1984", "George Orwell", 328, StatusType.ONSHELF);
			list.add(newBook);
			System.out.println("Added: "+newBook);
			
		}//try block
		
		catch(BookException e) {
			
			System.err.println("ERROR creating book to add: "+e.getMessage());
			
		}//catch block
		
		System.out.println("List after add:");
		printList(list);
		
		// e. iterator
		System.out.println("\n--- e. iterator (forward) ---");
		Iterator<LibraryItem> it = list.iterator();
		while(it.hasNext()) {
			
			System.out.println(" "+it.next());
			
		}
		
		// f. listIterator forward then reverse
		System.out.println("\n--- f. listIterator (forward) ---");
		ListIterator<LibraryItem> lit = list.listIterator();
		while(lit.hasNext()) {
			
			System.out.println(" "+lit.next());
			
		}
		
		System.out.println("\n--- f. listIterator (reverse) ---");
		while(lit.hasPrevious()) {
			
			System.out.println(" "+lit.previous());
			
		}
		
		// g. contains - Book we KNOW is in the list
		System.out.println("\n--- g. contains (Book IN list) ---");
		try {
			
			Book inListB = new Book("1984", "George Orwell", 328, StatusType.ONSHELF);
			System.out.println("Contains 'To Kill a Mockingbird' by Harper Lee: "+list.contains(inListB));
			
		}//try block
		
		catch(BookException e) {
			
			System.err.println("ERROR: "+e.getMessage());
			
		}//catch block
		
		// g. contains - Book we KNOW is NOT in the list
		System.out.println("\n--- g. contains (Book NOT in list) ---");
		try {
			
			Book notInListB = new Book("Harry Potter", "J.K. Rowling", 500, StatusType.ONSHELF);
			System.out.println("Contains 'Harry Potter' by J.K. Rowling: "+list.contains(notInListB));
			
		}//try block
		
		catch(BookException e) {
			
			System.err.println("ERROR: "+e.getMessage());
			
		}//catch block
		
		// h. remove(Object) - Journal we KNOW is in the list
		System.out.println("\n--- h. remove(Object) - Journal IN list ---");
		try {
			
			Journal inListJ = new Journal("Science", "American Association for the Advancement of Science", 1, StatusType.ONSHELF);
			boolean removedJ = list.remove(inListJ);
			System.out.println("Removed Journal 'Science' vol 1: "+removedJ);
			System.out.println("List after remove:");
			printList(list);
			
		}//try block
		
		catch(JournalException e) {
			
			System.err.println("ERROR: "+e.getMessage());
			
		}//catch block
		
		// h. remove(Object) - Journal we KNOW  is NOT in the list
		System.out.println("\n--- h. remove(Object) - Journal NOT in list ---");
		try {
			
			Journal notInListJ = new Journal("Fake Journal", "Nobody", 999, StatusType.ONSHELF);
			boolean removedJ2 = list.remove(notInListJ);
			System.out.println("Removed Journal 'Fake Journal' vol 999: "+removedJ2);
			System.out.println("List after remove attempt:");
			printList(list);
			
		}//try block
		
		catch(JournalException e) {
			
			System.err.println("ERROR: "+e.getMessage());
			
		}//catch block
		
	}//libraryListDemo
	
	private static void printList(List<?> list) {
		
		if(list.isEmpty()) {
			
			System.out.println(" (empty)");
			
		}
		
		else {
			
			for(int i=0;i<list.size();i++) {
				
				System.out.println(" ["+i+"] "+list.get(i));
				
			}
			
		}
		
	}//printList				
		
		
		
		

}//class

