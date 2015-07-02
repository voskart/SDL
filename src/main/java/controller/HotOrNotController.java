package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.xquery.XQException;

import model.Rating;
import model.Stone;
import model.User;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import recommender.Recommender;
import service.DatabaseService;
import service.JsonService;

@Controller
@RequestMapping("/HotOrNot")
public class HotOrNotController {

	@Autowired
	private DatabaseService dbservice;
	private static Logger LOG = Logger.getLogger(HotOrNotController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(HttpServletRequest req, ModelMap model) {
		User currentUser = (User) req.getSession().getAttribute("user");
		fillModel(model, currentUser);

		// WikidataService wds = new WikidataService();
		// model.addAttribute("info", wds.getAbstract("Q744630") );
		return "HotOrNot";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String like(HttpServletRequest req, ModelMap model) {
		User currentUser = (User) req.getSession().getAttribute("user");
		String vote = req.getParameter("params[voting]");
		String stoneId = req.getParameter("params[id]");

		storeRating(vote, stoneId, currentUser);
		fillModel(model, currentUser);

		return "HotOrNot";
	}

	/**
	 * Schreibt die Bewertung des Nutzers in die Datenbank.
	 * 
	 * @param vote
	 * @param stoneId
	 * @param currentUser
	 */
	private void storeRating(String vote, String stoneId, User currentUser) {
		Integer voting = vote.toUpperCase().equals("HOT") ? 5 : 1;
		Rating rating = new Rating(currentUser.getId(),
				Integer.parseInt(stoneId), voting);
		try {
			dbservice.insertRating(rating);
		} catch (XQException | IOException e) {
			HotOrNotController.LOG.error(e);
		}
	}

	private static final String IMAGE_URL_PREFIX = "https://en.wikipedia.org/w/api.php?action=query&prop=imageinfo&format=json&iiprop=url&titles=File:";

	class JsonURLClass {
		String url;
	}

	private static String getURL(String imageName) throws ParseException {
		try {
			String jsonString = JsonService.readJsonFromUrl(IMAGE_URL_PREFIX
					+ imageName);
			JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
			JSONObject query = (JSONObject) json.get("query");
			JSONObject pages = (JSONObject) query.get("pages");
			JSONObject bla = (JSONObject) pages.get("-1");
			JSONArray imageInfo = (JSONArray) bla.get("imageinfo");

			if (imageInfo == null) {
				return "http://img2.wikia.nocookie.net/__cb20140118173446/wiisportsresortwalkthrough/images/6/60/No_Image_Available.png";
			}
			JSONObject urlString = (JSONObject) imageInfo.get(0);

			return urlString.get("url").toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void fillModel(ModelMap model, User currentUser) {
		List<Rating> ratings = null;
		Stone stone = null;
		try {
			ratings = dbservice.getAllRatings();
			Recommender rec = new Recommender(ratings);
			Integer newStone = rec.getNextStone(currentUser.getId());

			stone = dbservice.getStonebyId(newStone);
		} catch (XQException | IOException e) {
			HotOrNotController.LOG.error(
					"Fehler beim Holen der Ratings aus der Datenbank.", e);
		}

		model.addAttribute("id", stone.getId());
		model.addAttribute("info",
				"ID: " + stone.getId() + ") " + stone.getKommentar());
		model.addAttribute("coords", stone.getCoordinates());
		try {
			model.addAttribute("img", getURL(stone.getBild()));
		} catch (ParseException e) {
			HotOrNotController.LOG.error(e);
		}
	}
}