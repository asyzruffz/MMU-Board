import java.io.*;
import java.util.*;

public class Discussion implements Serializable 
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
	
	public int getID(){
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
		sortComments();
	}
	
	public void removeComment(int id){
		commentList.remove(id);
		sortComments();
	}
	
	public Vector<Comment> getAllComment(){
		return commentList;
	}
	
	public void sortComments(){
		Collections.sort(commentList);
		Collections.reverse(commentList);
	}
	
	public String toString() {
        return discTitle;
    }
}
