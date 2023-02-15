package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Massage;
import model.Quote;
import model.Rating;
import model.Vote;
import services.QuoteService;

@Controller
public class QuoteController {
	private final QuoteService service;
	private List<Quote> topList;
	Logger logger = Logger.getLogger("QuoteController");

	public QuoteController(QuoteService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public String goHome(Model model) {
		Quote randomQuote = getRandomQuote();
		model.addAttribute("randomQuote", randomQuote);
		return "home.html";
	}
	
	@GetMapping("/top")
	public String getTop(Model model) {
		Quote randomQuote = getRandomQuote();
		model.addAttribute("randomQuote", randomQuote);
		topList = service.getTop();
		if(topList.size() >= 10) {
			for(int i = 0; i < 10; i++) {
				model.addAttribute("quote" + i, topList.get(i));
			}
		}
		else
			for(int i = 0; i < topList.size(); i++) {
				model.addAttribute("quote" + i, topList.get(i));
			}
		
		return "top.html";
	}
	
	@GetMapping("/loose")
	public String getLoose(Model model) {
		Quote randomQuote = getRandomQuote();
		model.addAttribute("randomQuote", randomQuote);
		topList = service.getLooser();
		if(topList.size() >= 10) {
			for(int i = 0; i < 10; i++) {
				model.addAttribute("quote" + i, topList.get(i));
			}
		}
		else
			for(int i = 0; i < topList.size(); i++) {
				model.addAttribute("quote" + i, topList.get(i));
			}
		
		return "loose.html";
	}
	
	@GetMapping("/getLike")
	public String getLike(@RequestParam int position) {
		service.likeThis(topList.get(position).getId());
		return "redirect:";
	}
	
	@GetMapping("/getDislike")
	public String getDislike(@RequestParam int position) {
		service.dislikeThis(topList.get(position).getId());
		return "redirect:";
	}
	
	@GetMapping("/last")
	public String getLast(Model model) {
		Quote randomQuote = getRandomQuote();
		model.addAttribute("randomQuote", randomQuote);
		topList = service.getLastQuote();
		if(topList.size() >= 10) {
			for(int i = 0; i < 10; i++) {
				model.addAttribute("quote" + i, topList.get(i));
			}
		}
		else
			for(int i = 0; i < topList.size(); i++) {
				model.addAttribute("quote" + i, topList.get(i));
			}
		
		return "last.html";
	}
	
	@PostMapping("/addQuote")
	public void addQuote(@RequestBody Massage massage) {
		service.addQuote(massage);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Quote>> getQuotes() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getQuotes());
	}
	
	@GetMapping("/getTop")
	public ResponseEntity<List<Quote>> getTopQuote(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getTop());
	}
	
	@GetMapping("/getLoose")
	public ResponseEntity<List<Quote>> getLooseQuote(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getLooser());
	}
	
	@PostMapping("/likeThis")
	public void likeThis(@RequestParam int id) {
		service.likeThis(id);
	}
	
	@PostMapping("/dislikeThis")
	public void dislikeThis(@RequestParam int id) {
		service.dislikeThis(id);
	}
	
	@GetMapping("/checkRating")
	public ResponseEntity<List<Rating>> getRating(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getRating());
	}
	
	@PostMapping("/modifyQuote")
	public void modifyQuote(@RequestBody Quote quote) {
		service.modifyQuote(quote);
	}
	
	@PostMapping("/deleteQuote")
	public void deleteQuote(@RequestParam int id) {
		service.deleteQuote(id);
	}
	
	public Quote getRandomQuote() {
		return service.getRandomQuote();
	}
	
	public List<Quote> getLastQuote() {
		return service.getLastQuote();
	}
}
