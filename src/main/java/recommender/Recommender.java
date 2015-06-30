package recommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Rating;

/**
 * Diese Klasse repräsentiert einen Recommender, die die Ähnlichkeit von
 * Benutzern berechnet und auf dieser Grundlage Vorschläge für Steine errechnet,
 * die einen Nutzer noch nicht bewertet hat.
 * 
 * @author benny
 * 
 */
public class Recommender {
	private static int ARRAYSIZE;

	// Matrix, welche die Ratings enthält (users_movies[userId][movieId] =
	// rating)
	private Integer usersStones[][] = null;
	// Ähnlichkeitsmatrix
	private double usersUsers[][] = null;
	private List<Rating> ratings;

	public Recommender(List<Rating> ratings) {
		if (ratings == null || ratings.isEmpty()) {
			return;
		}
		this.ratings = ratings;
		Recommender.ARRAYSIZE = ratings.size();
		initializeMatrixes();
		initializeRatingsMatrix();
		generateSimTable();

	}

	/**
	 * Initialisiert die Matrizen auf denen die Berechnungen stattfinden.
	 */
	private void initializeMatrixes() {
		usersStones = new Integer[Recommender.ARRAYSIZE][Recommender.ARRAYSIZE];
		usersUsers = new double[Recommender.ARRAYSIZE][Recommender.ARRAYSIZE];
	}

	/**
	 * Liest die Ratings der Nutzer eine Matrix ein.
	 * 
	 * @param ratings
	 *            Bewertungen des Nutzers
	 */
	private void initializeRatingsMatrix() {
		for (Rating r : ratings) {
			usersStones[r.getUserId()][r.getStoneId()] = r.getVoting();
		}
	}

	/**
	 * Berechnet das arithmetische Mittel aller abgegebenen Votings eines
	 * gegebenen Benutzers.
	 * 
	 * @param userId
	 * @return
	 */
	public double getMeanOfUser(int userId) {
		int numberOfVotes = 0;
		int absolut = 0;
		for (int i = 0; i < ARRAYSIZE; i++) {
			if (usersStones[userId][i] != null && usersStones[userId][i] > 0) {
				absolut = absolut + usersStones[userId][i];
				numberOfVotes++;
			}
		}
		// Falls User nicht existiert und somit keine Bewertungen existieren
		if (numberOfVotes == 0) {
			return 0;
		} else {
			return ((double) absolut / (double) numberOfVotes);
		}
	}

	/**
	 * Erzeugt eine Matrix, die die Ähnlickeiten der Nutzer untereinander
	 * enthält.
	 */
	private void generateSimTable() {
		for (int userA = 1; userA < ARRAYSIZE; userA++) {
			for (int userB = 1; userB < ARRAYSIZE; userB++) {
				// intersection enthält die Schnittmenge der Steine, für die
				// beide User ein Voting abgegeben haben.
				ArrayList<Integer> intersection = getIntersectionOf(userA,
						userB);
				double meanUserA = getMeanOfUser(userA);
				double meanUserB = getMeanOfUser(userB);
				double numerator = 0;
				double divisor1 = 0;
				double divisor2 = 0;
				if ((meanUserA != 0) && (meanUserB != 0)
						&& (intersection.size() > 0)) {
					int voteUserA = 0;
					int voteUserB = 0;

					for (int item = 0; item < intersection.size(); item++) {
						voteUserA = usersStones[userA][intersection.get(item)];
						voteUserB = usersStones[userB][intersection.get(item)];

						numerator = numerator
								+ ((voteUserA - meanUserA) * (voteUserB - meanUserB));
						divisor1 = divisor1
								+ Math.pow((voteUserA - meanUserA), 2);
						divisor2 = divisor2
								+ Math.pow((voteUserB - meanUserB), 2);
					}

					if ((numerator == 0) || (divisor1 == 0) || (divisor2 == 0)) {
						usersUsers[userA][userB] = 0;
					} else {
						usersUsers[userA][userB] = (numerator / ((Math
								.sqrt(divisor1) * Math.sqrt(divisor2))));
					}

				} else {
					usersUsers[userA][userB] = -99;
				}

			}

		}
	}

	/**
	 * Berechnet die Schnittmenge der der bewerteten Steine zweier User.
	 * 
	 * @param userA
	 * @param userB
	 * @return
	 */
	private ArrayList<Integer> getIntersectionOf(int userA, int userB) {
		ArrayList<Integer> intersection = new ArrayList<Integer>();
		for (int i = 0; i < ARRAYSIZE; i++) {
			if ((usersStones[userA][i] != null)
					&& (usersStones[userB][i] != null)) {
				intersection.add(i);
			}
		}
		return intersection;
	}

	public double getSimBetween(int userA, int userB) {
		return usersUsers[userA][userB];
	}

	/**
	 * Generiert eine Liste von ähnlichen Nutzern und gibt diese - sortiert nach
	 * dem Grad der Ähnlichkeit - zurück.
	 * 
	 * @param user
	 * @param neighbourhood
	 * @return
	 */
	public List<Neighbour> getOrderedNeighbours(int user, int neighbourhood) {
		ArrayList<Neighbour> neighbours = new ArrayList<Neighbour>();
		for (int i = 0; i < ARRAYSIZE; i++) {
			if (i != user) {
				if (usersUsers[user][i] != -99) {
					neighbours.add(new Neighbour(i, usersUsers[user][i],
							getIntersectionOf(user, i).size()));
				}
			}
		}
		Collections.sort(neighbours);
		// Falls weniger Nachbarn gefunden werden konnten, als gewünscht
		if (neighbours.size() < neighbourhood) {
			neighbourhood = neighbours.size();
		}
		return neighbours.subList(0, neighbourhood);
	}

	// Addiert die Bewertungen je Film aus einer Nachbarschaft auf, erzeugt eine
	// Film-
	// Liste und sortiert diese nach den addierten Bewertungen und gibt dann die
	// Anzahl
	// angegebener Empfehlungen zurÃ¼ck.

	/**
	 * Gibt eine Liste von Steinen zurück, die der Butzer mir userID = userId
	 * hat noch nicht gesehen hat. Diese Liste beruht auf der Ähnlichkeit des
	 * Nutzers zu anderen.
	 * 
	 * @param userId
	 * @param neighbourSize
	 * @param recommendations
	 * @return
	 */
	private List<Integer> getRecommendations(int userId, int neighbourSize,
			int recommendations) {
		List<Neighbour> neighbours = getOrderedNeighbours(userId, neighbourSize);
		neighbourSize = neighbours.size();

		// Berechne zu jedem Stone die voraussichtliche Bewertung des Benutzers.
		// Nehme diese Liste, um eine
		// Empfehlung auszusprechen.

		// stoneCounter = 1; da kleineste stoneId = 1
		ArrayList<Prediction> stones = new ArrayList<Prediction>();
		for (int stoneCounter = 1; stoneCounter < ARRAYSIZE; stoneCounter++) {
			if (usersStones[userId][stoneCounter] == null) {
				stones.add(new Prediction(stoneCounter, getPrediction(userId,
						stoneCounter, neighbours)));
			}
		}

		// Das Movie Array muss entsprechend sortiert werden, ohne die Zuordung
		// auf den Index zu verlieren
		Collections.sort(stones);

		List<Integer> retList = new ArrayList<Integer>();
		for (int i = 0; i < recommendations; i++) {
			retList.add(stones.get(i).stoneId);
		}
		return retList;
	}

	/**
	 * 
	 * Gibt eine Vorhersage über das User-Rating eines bestimmten Steines.
	 * 
	 * @param userId
	 *            Id des User
	 * @param stoneId
	 *            Id des Steins
	 * @param neighbours
	 *            Nachbarn des momentan betrachteten Users
	 * @return
	 */
	public double getPrediction(int userId, int stoneId,
			List<Neighbour> neighbours) {
		double zaehler = 0;
		double nenner = 0;
		int neighbour_index;
		for (int i = 0; i < neighbours.size(); i++) {
			neighbour_index = neighbours.get(i).getIndex();
			if (usersStones[neighbour_index][stoneId] != null
					&& usersStones[neighbour_index][stoneId] > 0) {
				zaehler = zaehler
						+ (usersStones[neighbour_index][stoneId] * usersUsers[userId][neighbour_index]);
				nenner = nenner + (usersUsers[userId][neighbour_index]);
			}
		}

		// Stein wurde in der Nachbarschaft nicht bewertet
		if ((zaehler == 0) || (nenner == 0)) {
			return 0;
		} else {
			return Math.round(zaehler / nenner);
		}
	}

	private class Prediction implements Comparable<Prediction> {
		private Integer stoneId;
		private double predictionRate;

		public Prediction(Integer stoneId, double d) {
			this.stoneId = stoneId;
			this.predictionRate = d;
		}

		@Override
		public int compareTo(Prediction o) {
			return Double.compare(o.predictionRate, this.predictionRate);
		}
	}

	/**
	 * Gibt die ID des Steins zurück, die der Nutzer als nächstes bewerten muss.
	 * 
	 * @param userId
	 * @return
	 */
	public Integer getNext(Integer userId) {
		Integer initialized = getInitializationStone(userId);
		if (initialized != null) {
			return initialized;
		}

		int neighbourhood_size = 12;
		int recommendations = 1;

		List<Integer> recomms = getRecommendations(userId, neighbourhood_size,
				recommendations);
		Integer nextStoneId = recomms.get(0);
		return nextStoneId;
	}

	/**
	 * Prüft, ob der User mit userId bereits die Steine 1 bi 10 bewertet hat.
	 * Wenn nein, wird die ID des ersten Steins zurückgegeben, der nicht
	 * bewertet wurde. Andernfalls null.
	 * 
	 * @param userId
	 * @return
	 */
	private Integer getInitializationStone(Integer userId) {
		Set<Integer> rated = new HashSet<Integer>();
		for (Rating r : ratings) {
			if (r.getUserId() == userId) {
				rated.add(r.getStoneId());
			}
		}
		for (int i = 1; i < 11; i++) {
			if (!rated.contains(new Integer(i))) {
				return i;
			}
		}

		return null;
	}

}
