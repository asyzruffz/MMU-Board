import java.io.*;

public class User implements Serializable
{
	public enum AccessLevel 
	{
		GUEST,
		STUDENT,
		LECTURER,
		ADMIN
	}
	
	private boolean pendingApproval = true;
	private String username = "Guest";
	private char[] password;
	private String nickname = "Guest";
	private AccessLevel clearance = AccessLevel.GUEST;
	
	public User() {}
	
	public User(String name){
		username = name;
	}
	
	public User(String name, char[] pass, String nick, AccessLevel lvl, boolean approv){
		username = name;
		password = pass;
		nickname = nick;
		clearance = lvl;
		pendingApproval = approv;
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
		return pendingApproval;
	}
	
	public void setApproved(){
		pendingApproval = false;
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
