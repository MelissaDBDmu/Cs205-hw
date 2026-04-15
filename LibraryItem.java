package edu.monmouth.library;

public interface LibraryItem {
	public String getTile(); 
	public boolean isAvailable();
	public void borrowItem(); 
	public void returnItem(); 
		
	
}
