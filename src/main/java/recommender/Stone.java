package recommender;

/**
 * 
 * @author benny
 *
 */
public class Stone implements Comparable<Stone> {

	private int id;
	private double predictedRating;
	private String name;

	Stone(int index, double total_ratings) {
		this.id = index;
		this.predictedRating = total_ratings;
	}

	public double getRatings() {
		return predictedRating;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int compareTo(Stone o) {
		return Double.compare(o.getRatings(), this.predictedRating);
	}

}
