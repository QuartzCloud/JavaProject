package bean;

public class User {
	// ��Ӧ�ֶ� uname
	String uname;
	// ��Ӧ�ֶ� password
	String password;

	public User(String uname, String password) {
		super();
		this.uname = uname;
		this.password = password;
	}

	public User() {
		super();
	}

	/**
	 * @return the uname
	 */
	public String getUsername() {
		return uname;
	}

	/**
	 * @param username the uname to set
	 */
	public void setUsername(String username) {
		this.uname = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}