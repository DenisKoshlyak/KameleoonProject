package repository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Profile;
import model.Quote;

@Repository
public class QuoteRepository {
	private final JdbcTemplate jdbcTemplate;
	Logger logger = Logger.getLogger("QuoteRepository");

	public QuoteRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void addQuote(Quote quote) {
		String sql = "INSERT INTO quote VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, quote.getId(), quote.getText(), quote.getAuthor());
	}
	
	public List<Quote> getAllQuote(){
		String sql = "SELECT * FROM quote";
		return jdbcTemplate.query(sql, new QuoteRowMapper());
	}
	
	public Optional<Quote> getQuoteById(int id) {
		String sql = "SELECT * FROM quote WHERE id = ?";
		try {
			return Optional.of(jdbcTemplate.queryForObject(sql, new QuoteRowMapper(), Integer.valueOf(id)));
		} catch (EmptyResultDataAccessException e) {
			logger.info("Random quote not found");
			return Optional.empty();
		}
	}
	
	public void modifyQuote(Quote quote) {
		String sql = "UPDATE quote SET text = ? WHERE id = ?";
		jdbcTemplate.update(sql, quote.getText(), quote.getId());
	}
	
	public void deleteQuote(int id) {
		String sql = "DELETE FROM quote WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
}