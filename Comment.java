import java.io.*;
import java.util.*;
import java.text.*;

public class Comment implements Serializable, Comparator<Comment>, Comparable<Comment>
{
	private String text = "";
	private User author = new User();
	private Discussion parent;
	private Date dateCreated;
	private Date dateEdited;
	private String createdOn = "";
	private String latestEdit = "";
	private int karma;
	
	public Comment() {}
	
	public Comment(String text){
		this.text = text;
		
		setTimeEdited(false);
	}
	
	public Comment(String text, User author){
		this.text = text;
		this.author = author;
		
		setTimeEdited(false);
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
	
	public void setTimeEdited(boolean edit){
		Date date = new Date();
		
		if(edit)
			dateEdited = date;
		else
			dateCreated = date;
		
		SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yyyy 'at' hh:mm a");
		latestEdit = ft.format(date);
	}
	
	public String getTimeEdited(){
		return latestEdit;
	}
	
	public void deleteThis(){
		parent.removeComment(this);
	}
	
	public int compareTo(Comment c){
		if((new Integer(karma)).compareTo(c.karma) == 0)
			return c.dateCreated.compareTo(dateCreated);
		else
			return (new Integer(karma)).compareTo(c.karma);
	}

	public int compare(Comment c1, Comment c2){
		if((new Integer(c1.karma)).compareTo(c2.karma) == 0)
			return c2.dateCreated.compareTo(c1.dateCreated);
		else
			return (new Integer(c1.karma)).compareTo(c2.karma);
	}
}
