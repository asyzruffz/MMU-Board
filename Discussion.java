import java.io.*;
import java.util.*;

public class Discussion implements Serializable 
{
	private String discTitle = "";
	private Vector<Comment> commentList = new Vector<Comment>();
	
	public Discussion() {}
	
	public Discussion(String title){
		discTitle = title;
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
		comment.setParent(this);
		commentList.add(comment);
		sortComments();
	}
	
	public void removeComment(Comment comment){
		commentList.remove(comment);
		sortComments();
	}
	
	public Vector<Comment> getAllComments(){
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
