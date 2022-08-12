package minkyu;

public class UserInfo {
	private String user_name;
	private String id;
	private String password;
	
	public UserInfo(String id, String password, String name) {
		setId(id);
		setPassword(password);
		setUser_name(name);
	}
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
