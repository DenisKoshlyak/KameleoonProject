package controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Profile;
import model.Quote;
import services.ProfileService;

@Controller
public class ProfileController {
	private final ProfileService service;
	private static boolean authenticationComplete;
	private final QuoteController quoteController;
	private Profile profile;
	
	public ProfileController(ProfileService service, QuoteController quoteController) {
		this.service = service;
		authenticationComplete = false;
		this.quoteController = quoteController;
	}
	
	@GetMapping("/profile")
	public String showProfile(Model model) {
		Quote randomQuote = quoteController.getRandomQuote();
		Profile pro = new Profile("username", "emailgmail.com");
		model.addAttribute("randomQuote", randomQuote);
		if(profile != null) {
			model.addAttribute("profile", profile);
		}
		else
			model.addAttribute("profile", new Profile("You dont authentication.", "-"));
		return "profile.html";
	}
	
	@PostMapping("/login")
	public String logIn(@RequestParam String username, @RequestParam String password) {
		if(service.checkUsername(username)) {
			profile = service.getProfile(username);
			authenticationComplete = true;
			return "top.html";
		}
		else {
			return "createProfile.html";
		}
	}
	
	@GetMapping("/createProfile")
	public String createProfile() {
		return "createProfile.html";
	}
	
	@PostMapping("/setUsername")
	public String setUsername(@RequestParam String username) {
		return service.setUsername(username);
	}
	
	@PostMapping("/setPassword")
	public String setPassword(@RequestParam String password) {
		return service.setPassword(password);
	}
	
	@PostMapping("/setEmail")
	public String setEmail(@RequestParam String email){
		return service.setEmail(email);
	}
}
