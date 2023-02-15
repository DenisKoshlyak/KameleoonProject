package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import model.Massage;
import model.Profile;
import model.Quote;
import model.Rating;
import repository.QuoteRepository;

@Service
public class QuoteService {
	private int quoteId;
	private QuoteRepository reposit;
	private final List<Rating> rating;
	
	public QuoteService(QuoteRepository reposit) {
		this.reposit = reposit;
		rating = new ArrayList<>();
		startedInsertion();
	}
	
	public void addQuote(Massage massage) {
		rating.add(new Rating(quoteId));
		Quote quote = new Quote(quoteId++, massage.getText(), massage.getAuthor());
		reposit.addQuote(quote);
	}
	
	public List<Quote> getQuotes(){
		return reposit.getAllQuote();
	}
	
	public Quote getQuoteById(int id) {
		return reposit.getQuoteById(id).orElse(new Quote(9999, "Nothing is perfect, but that's what I strive for every day.", "Koshlyak D."));
	}
	
	public List<Quote> getTop(){
		return findTop();
	}
	
	private List<Quote> findTop(){
		List<Quote> sublist = new ArrayList<>();
		Collections.sort(rating);
		return getSublist(rating);
	}
	
	private List<Quote> getSublist(List<Rating> rating){
		List<Quote> sublist = new ArrayList<>();
		if(rating.size() > 10) {
			for(int i = 0; i < 10; i++) {
				int id = rating.get(i).getId();
				sublist.add(getQuoteById(id));
			}
			return sublist;
		}
		else {
			for(int i = 0; i < rating.size(); i++) {
				int id = rating.get(i).getId();
				sublist.add(getQuoteById(id));
			}
			return sublist;
		}
	}
	
	public List<Quote> getLooser(){
		return findLoose();
	}
	
	private List<Quote> findLoose(){
		List<Quote> sublist = new ArrayList<>();
		Collections.sort(rating);
		return getEndSublist(rating);
	}
	
	private List<Quote> getEndSublist(List<Rating> rating){
		int size = rating.size();
		List<Quote> sublist = new ArrayList<>();
		if(size > 10) {
			for(int i = size - 1; i > size - 11; i--) {
				int id = rating.get(i).getId();
				sublist.add(getQuoteById(id));
			}
			return sublist;
		}
		else {
			for(int i = size - 1; i >= 0; i--) {
				int id = rating.get(i).getId();
				sublist.add(getQuoteById(id));
			}
			return sublist;
		}
	}
	
	public void likeThis(int id) {
		for(Rating r : rating) {
			if(r.getId() == id) {
				r.setLike();
				break;
			}
		}
	}
	
	public void dislikeThis(int id) {
		for(Rating r : rating) {
			r.setDislike();
			break;
		}
	}
	
	public List<Rating> getRating(){
		return rating;
	}
	
	public void modifyQuote(Quote quote) {
		reposit.modifyQuote(quote);
	}
	
	public void deleteQuote(int id) {
		reposit.deleteQuote(id);
	}
	
	public Quote getRandomQuote() {
		int id = (int)Math.round(Math.random() * quoteId);
		return reposit.getQuoteById(id).orElse(new Quote(9999, "Nothing is perfect, but that's what I strive for every day.", "Koshlyak D."));
	}
	
	public List<Quote> getLastQuote(){
		int size = rating.size();
		List<Quote> list = new ArrayList<Quote>();
		if(size >= 10) {
			for(int i = quoteId; i > quoteId - 10; i--) {
				list.add(getQuoteById(i));
			}
		}
		else {
			for(int i = quoteId; i >= 0; i--) {
				list.add(getQuoteById(i));
			}
		}
		return list;
	}
	
	private void startedInsertion() {
		rating.add(new Rating(quoteId));
		Quote quote = new Quote(quoteId++, "Wise men speak because they have something to say; fools because they have to say something." , "Platon");
		reposit.addQuote(quote);
		rating.add(new Rating(quoteId));
		quote = new Quote(quoteId++, "When you do something noble and beautiful and nobody noticed, do not be sad. For the sun every morning is a beautiful spectacle and yet most of the audience still sleeps." , "John Winston One Lennon");
		reposit.addQuote(quote);
		rating.add(new Rating(quoteId));
		quote = new Quote(quoteId++, "I don't like those cold, precise, perfect people, who, in order not to speak wrong, never speak at all, and in order not to do wrong never do anything.", "Henry Richar");
		reposit.addQuote(quote);
		rating.add(new Rating(quoteId));
		quote = new Quote(quoteId++, "It is not the strongest of the species that survives, nor the most intelligent, but the one most responsive to change.", "Charlse Darvine");
		reposit.addQuote(quote);
		rating.add(new Rating(quoteId));
		quote = new Quote(quoteId++, "Always dream and shoot higher than you know you can do. Do not bother just to be better than your contemporaries or predecessors. Try to be better than yourself.", "Wilem Folkner");
		reposit.addQuote(quote);
		rating.add(new Rating(quoteId));
		quote = new Quote(quoteId++, "In the End, we will remember not the words of our enemies, but the silence of our friends." , "Martin Luter King");
		reposit.addQuote(quote);
		rating.add(new Rating(quoteId));
		quote = new Quote(quoteId++, "There is no such thing as an accident. What we call by that name is the effect of some cause which we do not see." , "Wolter");
		reposit.addQuote(quote);
		rating.add(new Rating(quoteId));
		quote = new Quote(quoteId++, "The biggest risk is not taking any risk. In a world that's changing really quickly, the only strategy that is guaranteed to fail is not taking risks." , "Mark Tsukerberg");
		reposit.addQuote(quote);
		rating.add(new Rating(quoteId));
		quote = new Quote(quoteId++, "Love suffers long and is kind; love does not envy; love does not parade itself, is not puffed up; Does not behave rudely, does not seek its own, is not provoked, thinks no evil; Does not rejoice in iniquity, but rejoices in the truth." , "Saint Pavel");
		reposit.addQuote(quote);
		rating.add(new Rating(quoteId));
		quote = new Quote(quoteId++, "For true love is inexhaustible; the more you give, the more you have. And if you go to draw at the true fountainhead, the more water you draw, the more abundant is its flow. " , "Joseph Kampbell");
		reposit.addQuote(quote);
	}
}
