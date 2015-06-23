package recommender;

/**
 * Diese Klasse repräsentiert einen Nachbarn des Nutzers. Die Nachbarn basieren
 * auf der Ähnlichkeit der Nutzer untereinander.
 * 
 * @author benny
 * 
 */
public class Neighbour implements Comparable<Neighbour> {

	private int index;
	private double sim;
	private int intersection;

	Neighbour(int index, double sim, int intersection) {
		this.index = index;
		this.sim = sim;
		this.intersection = intersection;
	}

	public double getSim() {
		return sim;
	}

	public int getIndex() {
		return index;
	}

	public int getIntersection() {
		return intersection;
	}

	@Override
	public int compareTo(Neighbour o) {
		// Vergleich zunächst die Größe der Sim, anschließend die Anzahl der
		// Stone-Schnittmengen.

		if (this.sim == o.getSim()) {
			if (this.intersection < o.getIntersection()) {
				return 1;
			} else if (this.intersection == o.getIntersection()) {
				return 0;
			} else {
				return -1;
			}
		} else if (this.sim < o.getSim()) {
			return 1;
		} else {
			return -1;
		}
	}

}
