import java.util.*;

public class Discussion
{
	private static int discID;
	private String discTitle = "";
	private ArrayList<Comment> commentList = new ArrayList<Comment>();
	
	public Discussion() {}
	
	public Discussion(String title){
		discTitle = title;
	}
	
	public int getDiscID(){
		return discID;
	}
	
	public getTitle(){
		return discTitle;
	}
	
	public setTitle(String title){
		discTitle = title;
	}
	
	public Comment getComment(int id){
		return commentList.get(id);
	}
	
	public void addComment(Comment comment){
		commentList.add(comment);
	}
	
	public void removeComment(int id){
		commentList.remove(id);
	}

}
