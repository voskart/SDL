package model;

import interfaces.IRateable;

/**
 * 
 * @author benny
 * 
 */
public class RateableObject implements IRateable, Comparable<RateableObject> {
	private Integer id;
	private Double predictedRating;
	private String name;

	public RateableObject(Integer id, double total_ratings) {
		this.id = id;
		this.predictedRating = total_ratings;
	}

	public RateableObject(Integer id) {
		this.id = id;
	}

	public RateableObject() {
	};

	public double getRatings() {
		return predictedRating;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int compareTo(RateableObject o) {
		return Double.compare(o.getRatings(), this.predictedRating);
	}

	@Override
	public Double getRating() {
		return this.predictedRating;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

}
