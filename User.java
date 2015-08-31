import java.io.*;

public class User implements Serializable
{
	private int userID;
	private String username = "Guest";
	private char[] password;
	
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
	
	public String toString() {
        return username;
    }
}
