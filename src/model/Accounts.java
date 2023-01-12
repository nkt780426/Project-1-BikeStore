package model;

public class Accounts {
	private String username;
	private String password;
	private boolean position; // true = adminstrastor, false = customer
	private Integer staffId;

	public Accounts() {
		super();
	}

	public Accounts(String username, String password, boolean position, Integer staffId) {
		super();
		this.username = username;
		this.password = password;
		this.position = position;
		this.staffId = staffId;
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


	public boolean getPosition() {
		return position;
	}

	public void setPosition(boolean position) {
		this.position = position;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

}
