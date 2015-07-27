package entity;

public class People {
	private String useremail;
	private String username;
	private int userage;
	private String userpassword;

	public People() {
	}

	public People(String email, String name, int age, String password) {
		this.useremail = email;
		this.username = name;
		this.userage = age;
		this.userpassword = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserage() {
		return userage;
	}

	public void setUserage(int userage) {
		this.userage = userage;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
}
