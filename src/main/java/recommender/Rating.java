package recommender;

/**
 * Diese Klasse repräsentiert ein User-Rating.
 * 
 * @author benny
 * 
 */
public class Rating {

	private int userId;
	private int stoneId;
	private int voting;

	public Rating(int userId, int stoneId, int rating) {
		this.userId = userId;
		this.stoneId = stoneId;
		this.voting = rating;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStoneId() {
		return stoneId;
	}

	public void setStoneId(int stoneId) {
		this.stoneId = stoneId;
	}

	public int getVoting() {
		return voting;
	}

	public void setVoting(int rating) {
		this.voting = rating;
	}
}
