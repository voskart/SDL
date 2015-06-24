package model;

import interfaces.IId;
import interfaces.IRateable;

/**
 * Diese Klasse repräsentiert ein User-Rating.
 * 
 * @author benny
 * 
 */
public class Rating {

	private IId userId;
	private RateableObject objectId;
	private int voting;

	public Rating(IId user, RateableObject stone, int rating) {
		this.userId = user;
		this.objectId = stone;
		this.voting = rating;
	}

	public IId getUser() {
		return userId;
	}

	public void setUserId(IId user) {
		this.userId = user;
	}

	public RateableObject getObject() {
		return objectId;
	}

	public void setObeject(RateableObject object) {
		this.objectId = object;
	}

	public int getVoting() {
		return voting;
	}

	public void setVoting(int rating) {
		this.voting = rating;
	}
}
