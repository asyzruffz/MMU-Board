import java.util.*;

public class Subject
{
	private String subjName = "";
	private ArrayList<Discussion> discussionList = new ArrayList<Discussion>();
	
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
		return discussionList.get(id);
	}
	
	public void addDiscussion(Discussion discussion){
		discussionList.add(discussion);
	}
	
	public void removeDiscussion(int id){
		discussionList.remove(id);
	}
	
	public String toString() {
        return subjName;
    }
}
