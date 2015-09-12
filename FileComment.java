import java.io.*;

public class FileComment extends Comment
{
	private File file;
	
	public FileComment() {}
	
	public FileComment(String text){
		super(text);
	}
	
	public FileComment(String text, User author){
		super(text, author);
	}
	
	public FileComment(String text, User author, File file){
		super(text, author);
		this.file = file;
	}
	
	public File getFile(){
		return file;
	}
	
	public String getFileName(){
		return file.getName();
	}
}
