import java.io.*;

public class User implements Serializable
{
	public enum AccessLevel 
	{
		GUEST("Guest"),
		STUDENT("Student"),
		LECTURER("Lecturer"),
		ADMIN("Admin");
		
		private final String name;
			
		private AccessLevel(String s){
			name = s;
		}
		
		public String toString(){
			return name;
		}
	}
	
	private boolean approved = false;
	private String username = "Guest";
	private char[] password;
	private String nickname = "Guest";
	private AccessLevel clearance = AccessLevel.GUEST;
	
	public User() {}
	
	public User(String name){
		username = name;
	}
	
	public User(String name, char[] pass, String nick, AccessLevel lvl, boolean apprv){
		username = name;
		password = pass;
		nickname = nick;
		clearance = lvl;
		approved = apprv;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String name){
		username = name;
	}
	
	public char[] getPassword(){
		return password;
	}
	
	public void setPassword(char[] pass){
		password = pass;
	}
	
	public String getNickname(){
		return nickname;
	}
	
	public void setNickname(String name){
		nickname = name;
	}
	
	public boolean isPending(){
		return !approved;
	}
	
	public void setApproved(boolean approval){
		approved = approval;
	}
	
	public AccessLevel getAccessLevel(){
		return clearance;
	}
	
	public void setAccessLevel(AccessLevel level){
		clearance = level;
	}
	
	public boolean requireAccessLevel(AccessLevel level){
		return clearance.ordinal() >= level.ordinal();
	}
	
	public String toString() {
        return username;
    }
}
