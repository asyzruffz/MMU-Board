import java.io.*;

public class User implements Serializable
{
	private int userID;
	private boolean pendingApproval = true;
	private String username = "Guest";
	private char[] password;
	private String nickname = "Guest";
	
	public User() {}
	
	public User(String name){
		username = name;
	}
	
	public int getUserID(){
		return userID;
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
	
	public String toString() {
        return username;
    }
}
