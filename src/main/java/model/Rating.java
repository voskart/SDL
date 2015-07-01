package model;

import org.jsefa.xml.annotation.XmlDataType;
import org.jsefa.xml.annotation.XmlElement;

/**
 * Diese Klasse repräsentiert ein User-Rating.
 * 
 * @author benny
 * 
 */
@XmlDataType(defaultElementName = "rating")
public class Rating {

	@XmlElement(name = "userid", pos = 1)
	private Integer userId;

	@XmlElement(name = "stoneid", pos = 2)
	private Integer stoneId;

	@XmlElement(name = "voting", pos = 3)
	private Integer voting;
	
	public Rating(){};

	public Rating(Integer user, Integer stone, Integer rating) {
		this.userId = user;
		this.stoneId = stone;
		this.voting = rating;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer user) {
		this.userId = user;
	}

	public Integer getStoneId() {
		return stoneId;
	}

	public void setStoneId(Integer object) {
		this.stoneId = object;
	}

	public Integer getVoting() {
		return voting;
	}

	public void setVoting(int rating) {
		this.voting = rating;
	}

	@Override
	public String toString() {
		return "Rating [userId=" + userId + ", stoneId=" + stoneId
				+ ", voting=" + voting + "]";
	}
	
	
}
