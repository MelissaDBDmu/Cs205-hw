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
public String toString() {
	return "Journal: | Title: " + title + "| Editor: " + editor + "| Volume: " + volume + "| Status: " + status
			+ "| getTitle(): " + getTitle() + "| getEditor(): " + getEditor() + "| getVolume(): " + getVolume()
			+ "| getStatus(): " + getStatus() + " |";
}
@Override
public String getTile() {
	
	return null;
}
@Override
public boolean isAvailable() {
	
	return false;
}
@Override
public void borrowItem() {
	
	
}
@Override
public void returnItem() {
	
	
}



}
