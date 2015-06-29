package model;


/**
 * Diese Klasse repräsentiert ein User-Rating.
 * 
 * @author benny
 * 
 */
public class Rating {

	private Integer userId;
	private Integer objectId;
	private int voting;

	public Rating(Integer user, Integer stone, int rating) {
		this.userId = user;
		this.objectId = stone;
		this.voting = rating;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer user) {
		this.userId = user;
	}

	public Integer getStoneId() {
		return objectId;
	}

	public void setStoneId(Integer object) {
		this.objectId = object;
	}

	public int getVoting() {
		return voting;
	}

	public void setVoting(int rating) {
		this.voting = rating;
	}
}
