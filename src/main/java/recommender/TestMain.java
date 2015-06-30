package recommender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Rating;

public class TestMain {

	public static void main(String[] args) {
		Recommender rec = new Recommender(getRatings());
		showRecommendations(rec);
	}

	/**
	 * Liest die Steine in eine HashMap ein.
	 * 
	 */
	private static Map<Integer, String> getStones() {
		String path = ".\\src\\main\\resources\\u.item";
		Map<Integer, String> stones = new HashMap<Integer, String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			String split_line[] = null;
			while ((line = br.readLine()) != null) {
				split_line = line.split("\\|");
				stones.put(Integer.parseInt(split_line[0]), split_line[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stones;
	}

	private static void showRecommendations(Recommender rec) {
//		int user = 1;
//		int neighbourhood_size = 12;// 525;
//		int recommendations = 4;// 10;
//
//		for (int k = 1; k < 4; k++) {
//			user = k;
//			System.out.println();
//			System.out.println("Unsere Empfehlungen fuer Benutzer " + user
//					+ ":");
//
//			List<Integer> recomms = rec.getRecommendations(user,
//					neighbourhood_size, recommendations);
//			for(Integer i : recomms){
//				System.out.println("Stein mit ID = " + i);
//			}
//		}
	}

	private static List<Rating> getRatings() {
		List<Rating> ratings = new ArrayList<Rating>();

		// user 1
		ratings.add(new Rating(1, 1, 1));
		ratings.add(new Rating(1, 2, 1));
		ratings.add(new Rating(1, 4, 1));

		// user 2
		ratings.add(new Rating(2, 1, 1));
		ratings.add(new Rating(2, 2, 5));
		ratings.add(new Rating(2, 3, 5));
		ratings.add(new Rating(2, 4, 5));
		ratings.add(new Rating(2, 5, 5));
		ratings.add(new Rating(2, 6, 5));

		// user 3
		ratings.add(new Rating(3, 1, 1));
		ratings.add(new Rating(3, 2, 1));
		ratings.add(new Rating(3, 3, 5));
		ratings.add(new Rating(3, 4, 1));
		ratings.add(new Rating(3, 5, 1));
		ratings.add(new Rating(3, 6, 1));

		return ratings;
	}

}
