package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Quote;

public class QuoteRowMapper implements RowMapper<Quote>{
	@Override
	public Quote mapRow(ResultSet resultSet, int i) throws SQLException {
		Quote q = new Quote();
		q.setId(resultSet.getInt("id"));
		q.setText(resultSet.getString("text"));
		q.setAuthor(resultSet.getString("author"));
		return q;
	}
}
