import java.io.*;
import java.util.*;

public class Comment implements Serializable, Comparator<Comment>, Comparable<Comment>
{
	private String text = "";
	private User author = new User();
	private int karma;
	private Discussion parent;
	
	public Comment() {}
	
	public Comment(String text){
		this.text = text;
	}
	
	public Comment(String text, User author){
		this.text = text;
		this.author = author;
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
	
	public void setParent(Discussion parent){
		this.parent = parent;
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
	
	public int getKarma(){
		return karma;
	}
	
	public void deleteThis(){
		parent.removeComment(this);
	}
	
	public int compareTo(Comment c){
		return (new Integer(karma)).compareTo(c.karma);
	}

	public int compare(Comment c1, Comment c2){
		return c1.karma - c2.karma;
	}
}
