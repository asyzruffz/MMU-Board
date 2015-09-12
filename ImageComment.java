import java.io.*;

public class ImageComment extends Comment
{
	private File image;
	
	public ImageComment() {}
	
	public ImageComment(String text){
		super(text);
	}
	
	public ImageComment(String text, User author){
		super(text, author);
	}
	
	public ImageComment(String text, User author, File image){
		super(text, author);
		this.image = image;
	}
	
	public String getImagePath(){
		return "images/"+image.getName();
	}
}
