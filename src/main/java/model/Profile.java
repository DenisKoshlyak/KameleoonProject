package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile {
	private String username;
	private String email;
	private int password;
	
	public Profile(String username, String email) {
		this.username = username;
		if(checkEmail(email))
			this.email = email;
		else
			this.email = "Enter correct email";
	}
	
	public Profile getInformation() {
		return this;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	
	private boolean checkEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public void setPassword(int password) {
		this.password = password;
	}
}
