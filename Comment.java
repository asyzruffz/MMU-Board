
public class Comment
{
	private static int commentID;
	private String text = "";
	private User author;
	
	public Comment() {}
	
	public Comment(String text){
		this.text = text;
	}
	
	public Comment(String text, User author){
		this.text = text;
		this.author = author;
	}
	
	public int getCommentID(){
		return commentID;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public User getAuthor(){
		return author;
	}
	
	public void setAuthor(User author){
		this.author = author;
	}
}
