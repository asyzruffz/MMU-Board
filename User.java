
public class User
{
	private int userID;
	private String username = "";
	private String password = "";
	
	public User() {}
	
	public User(String username){
		this.username = username;
	}
	
	public int getUserID(){
		return userID;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
