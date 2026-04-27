package edu.monmouth.library;

public class Journal implements LibraryItem {
private String title; 
private String editor;
private int volume; 
private StatusType status; 
public Journal(String title, String editor,int volume,StatusType stauts) throws JournalException{
	setEditor(editor);
	setTitle(title);
	setVolume(volume);
	setStatus(status);
}
public String getTitle() {
	return title;
}
public String getEditor() {
	return editor;
}
public int getVolume() {
	return volume;
}
public StatusType getStatus() {
	return status;
}

public void setTitle(String title) throws JournalException{
	if(title==null|| title.length()==JournalConstants.MIN) {
		throw new JournalException("Title incorrect: musnt be empty");
	}
	this.title = title;
}
public void setEditor(String editor)throws JournalException {
	if(editor==null||editor.length()==JournalConstants.MIN) {
		throw new JournalException("Editor incorrect: musnt be empty");
	}
	this.editor = editor;
	
}
public void setVolume(int volume) throws JournalException {
	if(volume<=JournalConstants.MIN) {
		throw new JournalException("Volume incorrect must be more than 1");
	}
	this.volume = volume;

}
public void setStatus(StatusType status) {
	this.status = status;
}

@Override 
public boolean equals(Object obj) {
//	boolean authorsEqual =(Boolean) null;
//	boolean titlesEqual = (Boolean) null;
	if(this == obj) {return true;
	}
	if(obj == null || !(obj instanceof Book)) { return false;
	}
	
	Journal temp = (Journal) obj;
//	if(this.editor==null) {
//		boolean authorsEqual = temp.editor==null;
//	}else {
//		boolean authorsEqual = this.editor.equals(temp.editor);
//		return authorsEqual;
//	}
	
	//using Ternary conditional operator
	boolean authorsEqual = (this.editor == null)?
			 temp.editor == null
			: this.editor.equals(temp.editor);
//	if(this.title==null) {
//		boolean titlesEqual =  temp.title==null;
//	}else {
//		boolean titlesEqual = this.title.equals(temp.title);
//		return titlesEqual;
//	} 
	// using ternary conditional operator
	boolean titlesEqual = (this.title == null)?
		 temp.title == null
		 : this.title.equals(temp.title);
	
	return authorsEqual && titlesEqual;
	
}
@Override
public String toString() {
	return "Journal: | Title: " + title + "| Editor: " + editor + "| Volume: " + volume + "| Status: " + status
			+ "| getTitle(): " + getTitle() + "| getEditor(): " + getEditor() + "| getVolume(): " + getVolume()
			+ "| getStatus(): " + getStatus() + " |";
}
@Override
public String getTile() {
	
	return title;
}
@Override
public boolean isAvailable() {
	return status == StatusType.ONSHELF;
}
@Override
public void borrowItem() {
	status = StatusType.BORROWED;
	
}
@Override
public void returnItem() {
	status = StatusType.ONSHELF;
	
}



}
