package view.model;

public class RememberMeModel {

	private String username;
	private String password;
	private boolean rememberMe;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public RememberMeModel() {
	}

	/**
	 * 
	 * @param username
	 * @param rememberMe
	 * @param password
	 */
	public RememberMeModel(String username, String password, boolean rememberMe) {
		super();
		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
