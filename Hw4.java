package edu.monmouth.library;
import java.io.*;

import java.util.*;

public class Hw4 {
public static void main(String[] args) {
		
		// Verify exactly 2 command-line arguments
		if(args.length != HW4Constants.REQUIRED_ARGS) {
			
			System.err.println("Usage: java HW4 <outputFile> <libraryFile> error");
			System.exit(1);
			
		}
		
		String outputFileName = args[HW4Constants.OUTPUT_FILE_INDEX];
		String libraryFileName = args[HW4Constants.LIBRARY_FILE_INDEX];
		
		// Redirect stdout and stderr to args[0]
		try {
			
			PrintStream ps = new PrintStream(new FileOutputStream(outputFileName));
			System.setOut(ps);
			System.setErr(ps);
			
		}
		
		catch(FileNotFoundException e) {
			
			System.err.println("ERROR: Cannot open output file: "+outputFileName);
			System.exit(1);
			
		}
		
		// Verify equals() and hashCode() consistency
		System.out.println("=== Verifying equals() and hashCode() consistency ===\n");
		verifyBookConsistency();
		verifyJournalConsistency();
		
		// Declare HashSet (programming to the interface)
		Set<LibraryItem> hashSet = new HashSet<>();
		
		System.out.println("\n---- Adding items from file to HashSet ----\n");
		buildHashSet(hashSet, libraryFileName);
		
		System.out.println("\n---- Iterating over HashSet ----\n");
		Iterator<LibraryItem> it = hashSet.iterator();
		while (it.hasNext()) {
			
			System.out.println(it.next());
			
		}
		
		// Validate contains() and remove()
		System.out.println("\n---- Validating contains() and remove() ---\n");
		validateContainsAndRemove(hashSet);
		
		// Iterate after removes
		System.out.println("\n---- HashSet after removes (Book and Journal should be missing) ----\n");
		Iterator<LibraryItem> it2 = hashSet.iterator();
		while (it2.hasNext()) {
			
			System.out.println(it2.next());
			
		}
		
		
	}//main
	
	private static void verifyBookConsistency() {
		
		try {
			
			// Two equivalent Books (same author + title, different pages/status)
			Book b1 = new Book("LegendBorn", "Tracy Deonn ", 320, StatusType.ONSHELF);
			Book b2 = new Book("LegendBorn", "Tracy Deonn", 999, StatusType.BORROWED);
			
			// Two inequivalent Books
			Book b3 = new Book("1984", "George Orwell", 328, StatusType.ONSHELF);
			Book b4 = new Book("Brave New World", "Aldous Huxley", 311, StatusType.ONSHELF);
			
			System.out.println("-- Equivalent Books --");
			System.out.println("book1: "+b1);
			System.out.println("book2: "+b2);
			System.out.println("equals() returns: "+b1.equals(b2)+"(expected: true)");
			System.out.println("book1.hashCode() : "+b1.hashCode());
			System.out.println("book2.hashCode() : "+b2.hashCode());
			System.out.println("hashCodes match: "+(b1.hashCode() == b2.hashCode())+"(expected: true)");
			System.out.println("\n-- Inequivalent Books --");
			System.out.println("book3: "+b3);
			System.out.println("book4: "+b4);
			System.out.println("equals() returns: "+b3.equals(b4)+"(expected: false)");
			System.out.println("book3.hashCode() : "+b3.hashCode());
			System.out.println("book4.hashCode() : "+b4.hashCode());
			System.out.println("hashCodes differ: "+(b3.hashCode() != b4.hashCode())+"(expected: true)");
			
		}
		
		catch(BookException e) {
			
			System.err.println("BookException during consistency check: "+e.getMessage());
			
		}
		
	}
	
	private static void verifyJournalConsistency() {
		
		try {
			
			// Two equivalent Journals (same title + editor + volume, different status)
			Journal j1 = new Journal("Science", "American Association for the Advancement of Science", 1, StatusType.ONSHELF);
			Journal j2 = new Journal("Science", "American Association for the Advancement of Science", 1, StatusType.BORROWED);
			
			// Two inequivalent Journals
			Journal j3 = new Journal("ACM Transactions on Graphics", "AcM", 451, StatusType.BORROWED);
			Journal j4 = new Journal("IEEE Proceedings", "IEEE", 21, StatusType.ONSHELF);
			
			System.out.println("-- Equivalent Journals --");
			System.out.println("journal 1: "+j1);
			System.out.println("journal 2: "+j2);
			System.out.println("equals() returns: "+j1.equals(j2)+"(expected: true)");
			System.out.println("journal1.hashCode() : "+j1.hashCode());
			System.out.println("journal2.hashCode() : "+j2.hashCode());
			System.out.println("hashCodes match: "+(j1.hashCode() == j2.hashCode())+"(expected: true)");
			System.out.println("\n-- Inequivalent Journals --");
			System.out.println("journal 3: "+j3);
			System.out.println("journal 4: "+j4);
			System.out.println("equals() returns: "+j3.equals(j4)+"(expected: false)");
			System.out.println("journal3.hashCode() : "+j3.hashCode());
			System.out.println("journal4.hashCode() : "+j4.hashCode());
			System.out.println("hashCodes differ: "+(j3.hashCode() != j4.hashCode())+"(expected: true)");
			
		}
		
		catch(JournalException e) {
			
			System.err.println("JournalException during consistency check: "+e.getMessage());
			
		}
		
	}
	
	public static void buildHashSet(Set<LibraryItem> hashSet, String fileName) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			
			String line;
			while((line = br.readLine()) != null) {
				
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
					
					LibraryItem item;
					if(type.equals(LibraryItemConstants.BOOK_TYPE)) {
						
						item = new Book(title, person, num, status);
						
					}
					
					else if(type.equals(LibraryItemConstants.JOURNAL_TYPE)) {
						
						item = new Journal(title, person, num, status);
						
					}
					
					else {
						
						throw new IllegalArgumentException("Unknown type '"+type+"': "+line);
						
					}
					
					boolean added = hashSet.add(item);
					System.out.println("add() returned: "+added+" --> "+item);
					
				}
				
				catch(BookException | JournalException | IllegalArgumentException e) {
					
					System.err.println("INVALID LINE skipped --> "+line);
					System.err.println(" Reason: "+e.getMessage());
					
				}
				
			}
			
		}//try block
		
		catch(IOException e) {
			
			System.err.println("ERROR reading library file: "+e.getMessage());
			
		}//catch block
		
	}
	
	private static void validateContainsAndRemove(Set<LibraryItem> hashSet) {
		
		try {
			
			// Objects we KNOW exist in the HashSet
			Book bookIn = new Book("To Kill a Mockingbird", "Harper Lee", 320, StatusType.ONSHELF);
			Journal journalIn = new Journal("Science", "American Association for the Advancement of Science", 1, StatusType.ONSHELF);
			
			// Objects we KNOW are NOT in the HashSet
			Book bookOut = new Book("Harry Potter", "J.K. Rowling", 500, StatusType.ONSHELF);
			Journal journalOut = new Journal("Fake Journal", "Nobody", 5, StatusType.ONSHELF);
			
			//  contains() 
			System.out.println("-- contains() --");
			System.out.println("contains(Book IN set): "+hashSet.contains(bookIn)+" (expected: true)");
			System.out.println("contains(Journal IN set): "+hashSet.contains(journalIn)+" (expected: true)");
			System.out.println("contains(Book NOT in set): "+hashSet.contains(bookOut)+" (expected: false)");
			System.out.println("contains(Journal NOT in set): "+hashSet.contains(journalOut)+" (expected: false)");
			
			//  remove() 
			System.out.println("-- remove() --");
			System.out.println("remove(Book IN set): "+hashSet.remove(bookIn)+" (expected: true)");
			System.out.println("remove(Journal IN set): "+hashSet.remove(journalIn)+" (expected: true)");
			System.out.println("remove(Book NOT in set): "+hashSet.remove(bookOut)+" (expected: false)");
			System.out.println("remove(Journal NOT in set): "+hashSet.remove(journalOut)+" (expected: false)");
			
		}//try block
		
		catch(BookException | JournalException e) {
			
			System.err.println("Exception during contains/remove validation: "+e.getMessage());
			
		}//catch block
		
	}
		}//class
