import java.util.*;

public class Subject
{
	private String subjName = "";
	private Vector<Discussion> discussionList = new Vector<Discussion>();
	
	public Subject() {}
	
	public Subject(String name){
		subjName = name;
	}
	
	public String getSubjName(){
		return subjName;
	}
	
	public void setSubjName(String subjName){
		this.subjName = subjName;
	}
	
	public Discussion getDiscussion(int id){
		return discussionList.elementAt(id);
	}
	
	public void addDiscussion(Discussion discussion){
		discussionList.add(discussion);
	}
	
	public void removeDiscussion(int id){
		discussionList.remove(id);
	}
	
	public Vector<Discussion> getAllDiscussions(){
		return discussionList;
	}
	
	public String toString() {
        return subjName;
    }
}
