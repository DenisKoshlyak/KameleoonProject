package model;

import java.util.Comparator;
import java.util.logging.Logger;

public class Rating implements Comparable{
	private int id;
	private int like;
	private int dislike;
	Logger logger = Logger.getLogger("Rating");
	
	public Rating(int id) {
		this.id = id;
		like = 0;
		dislike = 0;
		show();
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLike() {
		return like;
	}
	
	public void setLike() {
		like++;
		logger.info("popular " + getPopular());
	}
	
	public int getDislike() {
		return dislike;
	}
	
	public void setDislike() {
		dislike++;
		logger.info("popular " + getPopular());
	}
	
	public int getPopular() {
		return like - dislike;
	}
	
	@Override
	public int compareTo(Object o) {
		Rating rating = (Rating) o;
		if(rating.getPopular() < this.getPopular())
			return -1;
		else if(rating.getPopular() == this.getPopular())
			return 0;
		else return 1;
	}
	
	public void show() {
		logger.info(" id = " + id + ", new rating");
	}
}
