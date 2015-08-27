import java.util.*;

public class Discussion
{
	private static int count;
	private int discID;
	private String discTitle = "";
	private Vector<Comment> commentList = new Vector<Comment>();
	
	public Discussion() {}
	
	public Discussion(String title){
		count++;
		discID = count;
		discTitle = title;
	}
	
	public int getDiscID(){
		return discID;
	}
	
	public String getTitle(){
		return discTitle;
	}
	
	public void setTitle(String title){
		discTitle = title;
	}
	
	public Comment getComment(int id){
		return commentList.elementAt(id);
	}
	
	public void addComment(Comment comment){
		commentList.add(comment);
	}
	
	public void removeComment(int id){
		commentList.remove(id);
	}
	
	public String toString() {
        return discTitle;
    }
}
