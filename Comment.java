
public class Comment
{
	private static int count;
	private int commentID;
	private String text = "";
	private User author;
	private int karma;
	
	public Comment() {}
	
	public Comment(String text){
		count++;
		commentID = count;
		this.text = text;
	}
	
	public Comment(String text, User author){
		this.text = text;
		this.author = author;
	}
	
	public int getID(){
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
	
	public String toString(){
        return text;
    }
	
	public void upvote(){
		karma++;
	}
	
	public void downvote(){
		karma--;
	}
}
