package services;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import model.Profile;

@Service
public class ProfileService {
	private Map<String, Profile> profiles;
	private String newUsername;
	
	
	public ProfileService() {
		profiles = new HashMap<>();
	}
	
	public Profile getProfile(String username) {
		return profiles.get(username);
	}
	
	public String setUsername(String username) {
		if(!checkUsername(username)) {
			profiles.put(username, new Profile(username));
			newUsername = username;
			return "setPassword.html";
		}
		else
			return "redirect:/";
	}
	
	public boolean checkUsername(String username) {
		return profiles.containsKey(username);
	}
	
	public String setPassword(String password) {
		if(checkPassword(password)) {
			profiles.get(newUsername).setPassword(password.hashCode());
			return "setEmail.html";
		}
		else
			return "redirect:/";
	}
	
	private boolean checkPassword(String password) {
		return password.length() >= 8;
	}
	
	public String setEmail(String email) {
		if(checkEmail(email)) {
			profiles.get(newUsername).setEmail(email);
			return "successCreate.html";
		}
		else 
			return "redirect:/";
	}
	
	private boolean checkEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
