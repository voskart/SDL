package recommender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.Rating;

/**
 * Diese Klasse repr�sentiert einen Recommender, die die �hnlicjkeit von
 * Benutzern ausrechnet und auf dieser Grundlage Vorschl�ge f�r Steinen
 * errechnet, die einen Nutzer interesiieren k�nnten.
 * 
 * @author benny
 * 
 */
public class Recommender {
	private static int ARRAYSIZE;

	// Matrix, welche die Ratings enth�lt (users_movies[userId][movieId] =
	// rating)
	private int users_stones[][] = null;
	private double users_users[][] = null;

	private Map<Integer, String> stoneNames;

	public Recommender(List<Rating> ratings, Map<Integer, String> stoneNames) {
		if (ratings == null || ratings.isEmpty()) {
			return;
		}
		this.stoneNames = stoneNames;
		Recommender.ARRAYSIZE = ratings.size();
		initializeMatrixes();
		importRatings(ratings);
		generateSimTable();

	}

	/**
	 * Initialisiert die Matrizen auf denen die Berechnungen stattfinden.
	 */
	private void initializeMatrixes() {
		users_stones = new int[Recommender.ARRAYSIZE][Recommender.ARRAYSIZE];
		users_users = new double[Recommender.ARRAYSIZE][Recommender.ARRAYSIZE];
	}

	/**
	 * Liest die Ratings der Nutzer eine Matrix ein.
	 * 
	 * @param ratings
	 *            Bewertungen des Nutzers
	 */
	private void importRatings(List<Rating> ratings) {
		for (Rating r : ratings) {
			users_stones[r.getUserId()][r.getStoneId()] = r.getVoting();
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
			if (users_stones[userId][i] > 0) {
				absolut = absolut + users_stones[userId][i];
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
	 * Erzeugt eine Matrix, die die �hnlickeiten der Nutzer untereinander
	 * enth�lt.
	 */
	private void generateSimTable() {
		for (int userA = 1; userA < ARRAYSIZE; userA++) {
			for (int userB = 1; userB < ARRAYSIZE; userB++) {
				// intersection enthält die Schnittmenge der Filme, für die
				// beide Benutzer ein Voting abgegeben haben.
				ArrayList<Integer> intersection = getIntersectionOf(userA,
						userB);
				double meanUserA = getMeanOfUser(userA);
				double meanUserB = getMeanOfUser(userB);
				double zaehler = 0;
				double nenner1 = 0;
				double nenner2 = 0;
				// Da mit "0" nicht abgestimmt werden kann, wird überprüft, ob
				// eine der beiden Personen als Mittel der Votings 0 hat, was
				// gleich-
				// bedeutend damit ist, dass es den User nicht gibt bzw. er
				// keine
				// Votings abgegeben hat. In diesem Fall wird der Sim-Wert auf
				// -99
				// gesetzt.
				if ((meanUserA != 0) && (meanUserB != 0)
						&& (intersection.size() > 0)) {
					int voteUserA = 0;
					int voteUserB = 0;
					// Für jeden Film, den beide User bewertet haben, wird der
					// nach-
					// folgende Code ausgeführt und die Zähler und Nenner
					// berechnet.
					for (int item = 0; item < intersection.size(); item++) {
						voteUserA = users_stones[userA][intersection.get(item)];
						voteUserB = users_stones[userB][intersection.get(item)];

						zaehler = zaehler
								+ ((voteUserA - meanUserA) * (voteUserB - meanUserB));
						nenner1 = nenner1
								+ Math.pow((voteUserA - meanUserA), 2);
						nenner2 = nenner2
								+ Math.pow((voteUserB - meanUserB), 2);
					}
					// Damit der gesamte Nenner 0 werden kann, muss ein Nutzer
					// existieren, dessen
					// gesamten Votings genau seinem Durchschnitt entsprechen.
					// Problem: Nutzer
					// die genau dieselben Wertungen abgeben haben sim=0
					// TODO: Wie wird mit solchen Usern umgegangen?
					if ((zaehler == 0) || (nenner1 == 0) || (nenner2 == 0)) {
						users_users[userA][userB] = 0;
					} else {
						users_users[userA][userB] = (zaehler / ((Math
								.sqrt(nenner1) * Math.sqrt(nenner2))));
					}

				} else {
					users_users[userA][userB] = -99;
				}

			}

		}
	}

	// Berechnet die Schnittmenge an Filmen, für die beide Personen abgestimmt
	// haben
	
	private ArrayList<Integer> getIntersectionOf(int userA, int userB) {
		ArrayList<Integer> intersection = new ArrayList<Integer>();
		for (int i = 0; i < ARRAYSIZE; i++) {
			// Überprüft für jedes Voting eines Films i von UserA und UserB.
			// Wenn
			// beide dort ein Voting abgegeben haben, kommt der Film in die
			// Schnittmenge
			if ((users_stones[userA][i] != 0) && (users_stones[userB][i] != 0)) {
				intersection.add(i);
			}
		}
		return intersection;
	}

	public double getSimBetween(int userA, int userB) {
		return users_users[userA][userB];
	}

	// Diese Funktion Erzeugt eine Liste aller User, die einen Ähnlichkeitswert
	// zwischeneinander
	// haben und sortiert sie nach einem definierten
	// Algorithmus (siehe Neighbour-Klasse), z.B. nach Sim bezüglich eines
	// angegebenen Users.
	// Schließlich wird eine Liste angegebener Größe zurückgegeben.
	public List<Neighbour> getOrderedNeighbours(int user, int neighbourhood) {
		ArrayList<Neighbour> neighbours = new ArrayList<Neighbour>();
		for (int i = 0; i < ARRAYSIZE; i++) {
			// Überspringt den Eintrag, bei dem der User mit sich selbst
			// vergleichen wurde.
			if (i != user) {
				if (users_users[user][i] != -99) {
					neighbours.add(new Neighbour(i, users_users[user][i],
							getIntersectionOf(user, i).size()));
				}
			}
		}
		Collections.sort(neighbours);
		// Falls weniger Nachbarn gefunden werden konnten, als gewünscht
		if (neighbours.size() < neighbourhood) {
			neighbourhood = neighbours.size();
		}
		// Info Sublist: FromIndex is inklusiv, ToIndex ist exclusiv.
		return neighbours.subList(0, neighbourhood);
	}

	// Addiert die Bewertungen je Film aus einer Nachbarschaft auf, erzeugt eine
	// Film-
	// Liste und sortiert diese nach den addierten Bewertungen und gibt dann die
	// Anzahl
	// angegebener Empfehlungen zurück.
	public List<StoneWrapper> getRecommendations(int userId, int neighbourSize,
			int recommendations) {
		List<Neighbour> neighbours = getOrderedNeighbours(userId, neighbourSize);

		// Falls die Liste in getOrderedNeighbours ggf. kleiner durch einen zu
		// großen
		// neighbourhood Parameter geworden ist. Andernfalls ist neighbourhood =
		// neighbours.size(),
		// da getOrderedNeighbours bereits dafür sorgt.
		neighbourSize = neighbours.size();

		// Berechne zu jedem Stone die voraussichtliche Bewertung des Benutzers.
		// Nehme diese Liste, um eine
		// Empfehlung auszusprechen.
		ArrayList<StoneWrapper> stones = new ArrayList<StoneWrapper>();
		for (int stoneCounter = 0; stoneCounter < ARRAYSIZE; stoneCounter++) {
			if (users_stones[userId][stoneCounter] == 0) {
				stones.add(new StoneWrapper(stoneCounter, getPrediction(userId, stoneCounter,
						neighbours)));
			}
		}

		// Das Movie Array muss entsprechend sortiert werden, ohne die Zuordung
		// auf den Index zu verlieren
		Collections.sort(stones);

		List<StoneWrapper> retList = new ArrayList<StoneWrapper>();
		StoneWrapper tempStone = null;
		for (int i = 0; i < recommendations; i++) {
			tempStone = stones.get(i);
			tempStone.setName(stoneNames.get(tempStone.getId()));
			retList.add(tempStone);
		}
		return retList;
	}

	/**
	 * 
	 * Gibt eine Vorhersage �ber das User-Rating eines bestimmten Steines.
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
			if ((users_stones[neighbour_index][stoneId] > 0)) {
				zaehler = zaehler
						+ (users_stones[neighbour_index][stoneId] * users_users[userId][neighbour_index]);
				nenner = nenner + (users_users[userId][neighbour_index]);
			}
		}

		// Stein wurde in der Nachbarschaft nicht bewertet
		if ((zaehler == 0) || (nenner == 0)) {
			return 0;
		} else {
			return Math.round(zaehler / nenner);
		}
	}

}
